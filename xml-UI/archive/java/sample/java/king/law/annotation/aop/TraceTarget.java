package king.law.annotation.aop;

import org.aspectj.lang.JoinPoint;

public class TraceTarget {
    @Trace(value = Trace.Kind.PARENT)
    private void doSomething() {
        System.out.println("let's do something here...");
    }

    @Trace(log = true)
    private void seeSomething() {
        System.out.println("let's see something here...");
    }

    @Trace(value = Trace.Kind.SUB_PARENT)
    private void hearSomething(String content) {
        System.out.println(String.format("let's hear %s here...", content));
    }

    @Trace(visit = Singer.class)
    private void singSomething(String content, int round) {
        System.out.println(String.format("let's sing %s by %s rounds here...", content, round));
    }

    static class Singer extends Trace.Visitor {

        @Override
        public void visit(JoinPoint joinPoint) {
            int round = (int) joinPoint.getArgs()[1];
            System.out.println(String.format("Are %d rounds of singing enough?", round));
        }
    }

    public static void main(String[] args) {
        TraceTarget target = new TraceTarget();
        target.doSomething();
        target.seeSomething();
        target.hearSomething("a beautiful song");
        target.singSomething("anthem", 3);
    }
}
