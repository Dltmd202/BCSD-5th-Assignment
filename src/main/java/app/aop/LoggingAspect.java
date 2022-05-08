package app.aop;

import app.trace.TraceStatus;
import app.trace.logtrace.LogTrace;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private final LogTrace logTrace;

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Around(value = "execution(* app.domain..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        TraceStatus status = null;

        try{
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            Object res = joinPoint.proceed();

            logTrace.end(status);
            return  res;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
