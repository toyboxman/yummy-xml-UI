package king.law.trace.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class JaegerExample {

    private final Tracer tracer;

    public JaegerExample(OpenTelemetry openTelemetry) {
        tracer = openTelemetry.getTracer("otel-jaeger");
    }

    private void myWonderfulUseCase() {
        // Generate a span
        Span span = this.tracer.spanBuilder(getSpanName()).startSpan();
        span.addEvent("event-1");
        span.setAttribute("Thread", Thread.currentThread().getName());
        // execute my use case - here we simulate a wait
        span.setAttribute("Context-origin", Context.current().toString());
        doWork(Context.current().with(span));
        span.setAttribute("Context", Context.current().toString());
        span.addEvent("event-2");
        span.setAttribute("scope", Context.current().makeCurrent().toString());
//        span.updateName(getSpanName());
        span.end();
        doAsyncWork(Context.current().with(span));
    }

    private void doWork(Context spanContext) {
        try {
            Span span = this.tracer.spanBuilder(getSpanName())
                    .setParent(spanContext).startSpan();
            span.setAttribute("Thread", Thread.currentThread().getName());
            span.setStatus(StatusCode.OK);
            span.setAttribute("TraceId", span.getSpanContext().getTraceId());
            span.setAttribute("SpanId", span.getSpanContext().getSpanId());
            span.setAttribute("SpanContext", span.getSpanContext().toString());
            span.setAttribute("Context", spanContext.toString());
            Thread.sleep(RandomKt.Random(1000).nextLong(5000));
            doAsyncWork(Context.current().with(span));
            span.end();
        } catch (InterruptedException e) {
            // do the right thing here
        }
    }

    private void doAsyncWork(Context spanContext) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                Span span = tracer.spanBuilder(getSpanName())
                        .setParent(spanContext).startSpan();
                span.setAttribute("Thread", Thread.currentThread().getName());
                span.setAttribute("TraceId", span.getSpanContext().getTraceId());
                span.setAttribute("SpanId", span.getSpanContext().getSpanId());
                span.setAttribute("Context", spanContext.toString());
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
        OpenTelemetry openTelemetry = TracerConfiguration.initOpenTelemetry(jaegerEndpoint);

        // Start the example
        JaegerExample example = new JaegerExample(openTelemetry);
        // generate a few sample spans
        for (int i = 0; i < 1; i++) {
            example.myWonderfulUseCase();
        }

        System.out.println("bye");
    }
}

