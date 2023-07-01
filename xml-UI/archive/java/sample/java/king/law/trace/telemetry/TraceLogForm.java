package king.law.trace.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

import java.time.Instant;

import static king.law.trace.telemetry.TracerConfiguration.ExporterType.LoggingSpanExporter;

public class TraceLogForm {
    public static void main(String[] args) {
        OpenTelemetry openTelemetry = TracerConfiguration.initOpenTelemetry(
                LoggingSpanExporter, null);

        Tracer tracer = openTelemetry.getTracer(TracerConfiguration.OTEL_JAEGER);
        Span span = tracer.spanBuilder("log-trace").startSpan();
        span.end(Instant.now().plusSeconds(2));
        System.out.println(span.toString());

        System.out.println("bye");
    }
}
