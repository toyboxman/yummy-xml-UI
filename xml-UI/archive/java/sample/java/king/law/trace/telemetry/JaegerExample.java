package king.law.trace.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static king.law.trace.telemetry.TracerConfiguration.ExporterType.*;

public final class JaegerExample {

    private final Tracer tracer;

    public JaegerExample(OpenTelemetry openTelemetry) {
        tracer = openTelemetry.getTracer(TracerConfiguration.OTEL_JAEGER);
    }

    private void myWonderfulUseCase() {
        // Generate a span
        Span span = this.tracer.spanBuilder(getSpanName())
                .setSpanKind(SpanKind.INTERNAL).startSpan();
        span.addEvent("event-1");
        span.setAttribute("Thread", Thread.currentThread().getName());
        // execute my use case - here we simulate a wait
        doWork(Context.current().with(span));
        span.setAttribute("Context", Context.current().toString());
        span.addEvent("event-2");
        span.recordException(new RuntimeException("this is a mocked exception"));
        span.end();
        // after span transaction ended, update span name is invalid
        span.updateName(getSpanName());

        // span.storeInContext(Context.current()) == Context.current().with(span)
        doAsyncWork(span.storeInContext(Context.current()));

        SpanContext spanContext = span.getSpanContext();
        SpanContext remoteParent = SpanContext.createFromRemoteParent(
                spanContext.getTraceId(),
                spanContext.getSpanId(),
                spanContext.getTraceFlags(),
                spanContext.getTraceState()
        );
        this.tracer.spanBuilder(getSpanName()).setParent(Context.current().with(Span.wrap(remoteParent)))
                .startSpan().end(System.currentTimeMillis() + 400, TimeUnit.MILLISECONDS);

        SpanContext parent = SpanContext.create(
                spanContext.getTraceId(),
                spanContext.getSpanId(),
                spanContext.getTraceFlags(),
                spanContext.getTraceState()
        );
        this.tracer.spanBuilder(getSpanName()).setParent(Context.current().with(Span.wrap(parent)))
                .startSpan().end(System.currentTimeMillis() + 200, TimeUnit.MILLISECONDS);
    }

    private void doWork(Context parentContext) {
        try {
            Span span = this.tracer.spanBuilder(getSpanName())
                    .setParent(parentContext).setSpanKind(SpanKind.CLIENT).startSpan();
            span.setAttribute("Thread", Thread.currentThread().getName());
            span.setStatus(StatusCode.OK);
            span.setAttribute("SpanContext", span.getSpanContext().toString());
            span.setAttribute("Context", parentContext.toString());
            Thread.sleep(RandomKt.Random(1000).nextLong(5000));

            doAsyncWork(Context.current().with(span));

            try (Scope ignored = Context.current().with(span).makeCurrent()) {
                span.setAttribute("temp-Context", Context.current().toString());
                doAsyncWork(Context.current());
            }

            doAsyncWork(parentContext);

            span.end();
        } catch (InterruptedException e) {
            // do the right thing here
        }
    }

    private void doAsyncWork(Context parentContext) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                Span span = tracer.spanBuilder(getSpanName())
                        .setParent(parentContext).setSpanKind(SpanKind.PRODUCER).startSpan();
                span.setAttribute("Thread", Thread.currentThread().getName());
                span.setAttribute("TraceId", span.getSpanContext().getTraceId());
                span.setAttribute("SpanId", span.getSpanContext().getSpanId());
                span.setAttribute("Context", parentContext.toString());
                span.setStatus(StatusCode.ERROR);
                Thread.sleep(RandomKt.Random(1000).nextLong(5000));
                span.end();
            } catch (InterruptedException e) {
                // do the right thing here
            }
        });
        executorService.shutdown();
    }

    private static volatile long id = 0;

    @NotNull
    private static String getSpanName() {
        return "function-" + (++id);
    }

    public static void main(String[] args) {
        // Parsing the input
        if (args.length < 1) {
            System.out.println("Missing [endpoint]");
            System.exit(1);
        }
        String jaegerEndpoint = args[0];

        // it is important to initialize your SDK as early as possible in your application's lifecycle
        OpenTelemetry openTelemetry;
        openTelemetry = TracerConfiguration.initOpenTelemetry(JaegerGrpcSpanExporter,
                String.format("http://%s:14250/", jaegerEndpoint));
//        openTelemetry = TracerConfiguration.initOpenTelemetry(LoggingSpanExporter, null);
//        openTelemetry = TracerConfiguration.initOpenTelemetry(JaegerThriftSpanExporter,
//                String.format("http://%s:14268/api/traces", jaegerEndpoint));
//        openTelemetry = TracerConfiguration.initOpenTelemetry(JaegerThriftSpanUdpExporter, jaegerEndpoint);
        // Start the example
        JaegerExample example = new JaegerExample(openTelemetry);
        // generate a few sample spans
        for (int i = 0; i < 1; i++) {
            example.myWonderfulUseCase();
        }

        System.out.println("bye");
    }
}

