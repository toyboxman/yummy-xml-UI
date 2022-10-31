package king.law.trace.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

public final class JaegerExample {

    private final Tracer tracer;

    public JaegerExample(OpenTelemetry openTelemetry) {
        tracer = openTelemetry.getTracer("otel-jaeger");
    }

    private void myWonderfulUseCase() {
        // Generate a span
        Span span = this.tracer.spanBuilder(getSpanName()).startSpan();
        span.addEvent("event-1");
        // execute my use case - here we simulate a wait
        doWork(Context.current().with(span));
        span.addEvent("event-2");
//        span.updateName(getSpanName());
        span.end();
    }

    private void doWork(Context spanContext) {
        try {
            Span span = this.tracer.spanBuilder(getSpanName())
                    .setParent(spanContext).startSpan();
            Thread.sleep(RandomKt.Random(1000).nextLong(5000));
            span.end();
        } catch (InterruptedException e) {
            // do the right thing here
        }
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

