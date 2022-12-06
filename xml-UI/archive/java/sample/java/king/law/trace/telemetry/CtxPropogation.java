package king.law.trace.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.baggage.BaggageBuilder;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.ContextKey;
import io.opentelemetry.context.Scope;
import kotlin.random.RandomKt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static king.law.trace.telemetry.TracerConfiguration.ExporterType.JaegerGrpcSpanExporter;

public class CtxPropogation {

    private static OpenTelemetry openTelemetry;
    private static Tracer tracer;
    private ExecutorService threadPool;

    void sameThreadTrace() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            function1();
        });
        service.shutdown();
    }

    private static final ContextKey<Span> KEY = ContextKey.named("MyState");

    void function1() {
        Span span = tracer.spanBuilder("function1").startSpan();
        Context context = Context.current().with(span);
        // make context as current parent in this thread
        Scope scope = context.makeCurrent();
        System.out.println("function1");
        span.setAttribute("function1", Context.current().toString());
        span.setAttribute("function1-1", Span.fromContext(context).hashCode());
        span.setAttribute("function1-2", span.hashCode());
        timeCrunch();
        function2();
        span.end();
        // draw back parent context and restore
        context.makeCurrent().close();
//        scope.close();
    }

    private void function2() {
        Span span = tracer.spanBuilder("function2").startSpan();
        span.addEvent("Attributes", Attributes.builder().put("123", "456").build());
        Baggage baggage = Baggage.builder().put("123", "456").build();
        Context context = Context.current().with(span).with(baggage);
        context.makeCurrent();
        System.out.println("function2");
        timeCrunch();
        function3();
        Span.current().end();
        // same effect with the invocation of span.end();
        context.makeCurrent().close();
    }

    private void function3() {
        Span span = tracer.spanBuilder("function3").startSpan();
        System.out.println("function3");
        timeCrunch();
        span.end();
    }

    void crossThreadTrace() {
        threadPool = Executors.newFixedThreadPool(3);
        threadPool.submit(() -> {
            function4();
        });
    }

    private void function4() {
        Span span = tracer.spanBuilder("function4").startSpan();
        span.setAttribute("thread", Thread.currentThread().getName());
        Context context = Context.current().with(span);
        System.out.println("function4");
        timeCrunch();
        // context propagation between various threads
        context.wrap(threadPool).submit(() -> {
            function5();
        });
        span.end();
    }

    private void function5() {
        Span span = tracer.spanBuilder("function5").startSpan();
        span.setAttribute("thread", Thread.currentThread().getName());
        Context.current().wrap(threadPool).submit(() -> {
            function6();
        });
        Context context = Context.current().with(span);
        System.out.println("function5");
        timeCrunch();
        context.wrap(threadPool).submit(() -> {
            function6();
        });
        span.end();
    }

    private void function6() {
        Span span = tracer.spanBuilder("function6").startSpan();
        span.setAttribute("thread", Thread.currentThread().getName());
        Context context = Context.current().with(span);
        Scope scope = context.makeCurrent();
        System.out.println("function6");
        timeCrunch();
        span.end();
        scope.close();
        threadPool.shutdown();
    }

    void timeCrunch() {
        try {
            Thread.sleep(RandomKt.Random(1000).nextLong(5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        String jaegerEndpoint = "10.176.217.250";

        // it is important to initialize your SDK as early as possible in your application's lifecycle
        openTelemetry = TracerConfiguration.initOpenTelemetry(JaegerGrpcSpanExporter,
                String.format("http://%s:14250/", jaegerEndpoint));
//        openTelemetry = TracerConfiguration.initOpenTelemetry(LoggingSpanExporter, null);
//        openTelemetry = TracerConfiguration.initOpenTelemetry(JaegerThriftSpanExporter,
//                String.format("http://%s:14268/api/traces", jaegerEndpoint));
//        openTelemetry = TracerConfiguration.initOpenTelemetry(JaegerThriftSpanUdpExporter, jaegerEndpoint);

        tracer = openTelemetry.getTracer(TracerConfiguration.OTEL_JAEGER);
        CtxPropogation propogation = new CtxPropogation();
        propogation.sameThreadTrace();
        propogation.crossThreadTrace();

        System.out.println("bye");
    }
}
