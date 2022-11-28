package king.law.trace.telemetry;

import io.jaegertracing.thrift.internal.senders.UdpSender;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.exporter.jaeger.thrift.JaegerThriftSpanExporter;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.apache.thrift.transport.TTransportException;

import java.util.concurrent.TimeUnit;

/**
 * All SDK management takes place here, away from the instrumentation code, which should only access
 * the OpenTelemetry APIs.
 */
class TracerConfiguration {

    public static final String OTEL_JAEGER = "otel-jaeger";

    public enum ExporterType {
        JaegerGrpcSpanExporter,
        JaegerThriftSpanExporter,
        JaegerThriftSpanUdpExporter,
        LoggingSpanExporter;
    }

    /**
     * Initialize an OpenTelemetry SDK with a Jaeger exporter and a SimpleSpanProcessor.
     *
     * @param jaegerEndpoint The endpoint of your Jaeger instance.
     * @return A ready-to-use {@link OpenTelemetry} instance.
     */
    static OpenTelemetry initOpenTelemetry(ExporterType type, String jaegerEndpoint) {
        SpanExporter spanExporter = null;
        switch (type) {
            case JaegerGrpcSpanExporter:
                // Export traces to Jaeger by gRPC
                spanExporter = JaegerGrpcSpanExporter.builder()
                        .setEndpoint(jaegerEndpoint)
                        .setTimeout(30, TimeUnit.SECONDS)
                        .build();
                break;
            case JaegerThriftSpanExporter:
                spanExporter = JaegerThriftSpanExporter.builder()
                                .setEndpoint(jaegerEndpoint)
                                .build();
                break;
            case JaegerThriftSpanUdpExporter:
                try {
                    UdpSender udpSender = new UdpSender(jaegerEndpoint, 6831, 0);
                    spanExporter = JaegerThriftSpanExporter.builder()
                            .setThriftSender(udpSender)
                            .build();
                } catch (TTransportException e) {
                    throw new RuntimeException(e);
                }
                break;
            case LoggingSpanExporter:
                spanExporter = LoggingSpanExporter.create();
                break;
            default:
        }

        Resource serviceNameResource =
                Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, OTEL_JAEGER));

        // Set to process the spans by the Jaeger Exporter
        SdkTracerProvider tracerProvider =
                SdkTracerProvider.builder()
                        .addSpanProcessor(SimpleSpanProcessor.create(spanExporter))
                        .setResource(Resource.getDefault().merge(serviceNameResource))
                        .build();
        OpenTelemetrySdk openTelemetry =
                OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).build();

        // it's always a good idea to shut down the SDK cleanly at JVM exit.
        Runtime.getRuntime().addShutdownHook(new Thread(tracerProvider::close));

        return openTelemetry;
    }
}

