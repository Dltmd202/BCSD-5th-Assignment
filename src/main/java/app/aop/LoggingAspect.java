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
    //로거 팩토리에서 생성
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //생성자 주입
    public LoggingAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     * 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행
     * 리턴 타입: * 이므로 모든 리턴 타입에 대해 적용
     * 클래스명: app.domain의 하위의 클래스
     * 메서드명: 모든 메서드 이름에 적용
     * 파라미터: .. 파라미터에 상관 없이 적용
     */
    @Around(value = "execution(* app.domain..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        TraceStatus status = null;

        try{
            //jp에서 getSignature 하면 호출 메서드 시그니처 객체를 얻을 수 있고 이를 간단한 문자열 정보로 반환받음
            String message = joinPoint.getSignature().toShortString();
            //trace 시작
            status = logTrace.begin(message);

            //메서드 실행
            Object res = joinPoint.proceed();

            //trace 종료
            logTrace.end(status);
            return  res;
        } catch (Exception e) {
            //exception trace
            logTrace.exception(status, e);
            throw e;
        }
    }
}
