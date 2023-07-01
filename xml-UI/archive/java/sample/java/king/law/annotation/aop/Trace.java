package king.law.annotation.aop;

import org.aspectj.lang.JoinPoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Trace {
    Kind value() default Kind.CHILD;

    boolean log() default false;

    Class<? extends Visitor> visit() default Visitor.class;

    public static enum Kind {
        PARENT, CHILD, SUB_PARENT
    }

    public static abstract class Visitor {
        public Visitor() {
        }

        abstract void visit(JoinPoint joinPoint);
    }
}
