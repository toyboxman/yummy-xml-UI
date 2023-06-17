package king.law.trace.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.internal.OtelEncodingUtils;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.Context;

import java.time.Instant;
import java.util.Random;

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

        Span span1 = tracer.spanBuilder("child-func-1").setParent(
                Context.current().with(
                        Span.wrap(span.getSpanContext())
                )
        ).startSpan();
        span1.end(Instant.now().plusSeconds(1));
        Span span1_1 = tracer.spanBuilder("child-func-1_1")
                .addLink(span1.getSpanContext()).startSpan();
        span1_1.end(Instant.now().plusSeconds(1));

        Random random = new Random();
        // if flags = 0 , trace will not be sent out
        byte flags = (byte) 1;
        final String parentTraceId = TraceId.fromLongs(
                random.nextLong(), random.nextLong());
        final String parentSpanId = SpanId.fromLong(random.nextLong());
        final TraceFlags parentTraceFlags = TraceFlags.fromByte(flags);
        final TraceState parentTraceState = TraceState.builder().build();
        SpanContext parent = SpanContext.create(
                parentTraceId,
                parentSpanId,
                parentTraceFlags,
                parentTraceState
        );
        Span span2 = tracer.spanBuilder("child-func-2")
                .setParent(
                        Context.current().with(
                                Span.wrap(parent)))
                .startSpan();
        span2.end(Instant.now().plusSeconds(1));

        Span span3 = tracer.spanBuilder("child-func-3")
                .setParent(
                        Context.current().with(
                                Span.getInvalid()))
                .startSpan();
        span3.end(Instant.now().plusSeconds(1));

        System.out.println("bye");
    }
}
