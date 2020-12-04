package king.law.trace;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.internal.samplers.RateLimitingSampler;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

import java.util.HashMap;
import java.util.Map;

public class SampleTrace {
    public static void main(String[] args) throws InterruptedException {
        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration()
//                .withType(RateLimitingSampler.TYPE)
//                .withParam(2);
                .withType(ConstSampler.TYPE)
                .withParam(1);

        Configuration.SenderConfiguration senderConfig = new Configuration.SenderConfiguration()
                .withAgentHost("10.117.4.11")
                .withAgentPort(6831);
        Configuration.ReporterConfiguration reporterConfig = new Configuration.ReporterConfiguration()
                .withLogSpans(true)
                .withFlushInterval(1000)
                .withMaxQueueSize(20)
                .withSender(senderConfig);
        Configuration configuration = new Configuration("Sample");
        Tracer tracer = configuration.withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
        GlobalTracer.register(tracer);
        Span test = null;
        for (int i = 0; i < 1; i++) {
//            if (test == null) {
//                test = GlobalTracer.get().buildSpan("test" + i).start();
//            } else {
//                test = GlobalTracer.get().buildSpan("test" + i).asChildOf(test).start();
//            }
            test = GlobalTracer.get().buildSpan("test" + i).start();
            test.setTag("test" + i, "123456");
            test.setBaggageItem("test" + i, "123456");
            test.finish();
            Span child = GlobalTracer.get().buildSpan("child").asChildOf(test).start();
            child.setBaggageItem("child", "123456");
            child.setBaggageItem("childx", "123456");
            child.log(System.currentTimeMillis(), "11111");
            child.log("child");
            Map<String, String> fields = new HashMap<>();
            fields.put("child", "123456");
            fields.put("childx", "123456");
            child.log(1, fields);
            child.finish();
        }
        Thread.sleep(2000);
    }
}
