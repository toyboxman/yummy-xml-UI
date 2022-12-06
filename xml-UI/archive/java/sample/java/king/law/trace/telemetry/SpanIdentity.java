package king.law.trace.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.internal.OtelEncodingUtils;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanId;
import io.opentelemetry.api.trace.TraceId;
import io.opentelemetry.api.trace.Tracer;

import java.time.Instant;

import static king.law.trace.telemetry.TracerConfiguration.ExporterType.JaegerGrpcSpanExporter;

public class SpanIdentity {
    public static void main(String[] args) {
        String jaegerEndpoint = "10.176.217.250";

        // it is important to initialize your SDK as early as possible in your application's lifecycle
        OpenTelemetry openTelemetry = TracerConfiguration.initOpenTelemetry(JaegerGrpcSpanExporter,
                String.format("http://%s:14250/", jaegerEndpoint));

        Tracer tracer = openTelemetry.getTracer(TracerConfiguration.OTEL_JAEGER);

        Span span = tracer.spanBuilder("span-func").startSpan();

        String traceId = span.getSpanContext().getTraceId();
        span.setAttribute("traceId", traceId);
        long highPos = OtelEncodingUtils.longFromBase16String(traceId, 0);
        long lowPos = OtelEncodingUtils.longFromBase16String(traceId, 16);
        span.setAttribute("highPos", highPos);
        span.setAttribute("lowPos", lowPos);
        span.setAttribute("generated-traceId", TraceId.fromLongs(highPos, lowPos));

        String spanId = span.getSpanContext().getSpanId();
        span.setAttribute("spanId", spanId);
        long long_spanId = OtelEncodingUtils.longFromBase16String(spanId, 0);
        span.setAttribute("long-spanId", long_spanId);
        span.setAttribute("generated-spanId", SpanId.fromLong(long_spanId));

        span.end(Instant.now().plusSeconds(2));

        System.out.println("bye");
    }
}
