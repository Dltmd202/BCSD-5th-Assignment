package app.trace.logtrace;

import app.trace.TraceId;
import app.trace.TraceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTraceImpl implements LogTrace{

    //logger 팩토리에서 logger를 생성, 주입
    private Logger logger = LoggerFactory.getLogger(LogTraceImpl.class);

    //싱글톤 특성상 동시성 문제가 발생하여 ThreadLocal을 사용하여
    //traceIdHolder 지정
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    //trace를 시작
    @Override
    public TraceStatus begin(String message) {
        // traceIdHolder 싱크
        syncTraceId();
        // 해당 traceIdHolder 가져오기
        TraceId traceId = traceIdHolder.get();
        // 메서드 호출 시작 시점
        Long startTimeMs = System.currentTimeMillis();
        // 시작 로깅
        logger.info("{}", message);

        //TraceStatus 반환
        return new TraceStatus(traceId, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus status) {
        //정상종료로 complete 호출
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        //exception과 complete 호출
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        // 종료 시간 저장
        Long stopTimeMs = System.currentTimeMillis();
        // 실행 시간 계산
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();

        if (e == null) {
            //exception이 없으면 정상종료 로그 출력
            logger.info("{} time={}ms", status.getMessage(), resultTimeMs);
        } else {
            //exception이 있으면 시간과 exception 출력
            logger.info("{} time={}ms ex={}", status.getMessage(), resultTimeMs, e.toString());
        }

        // trace 스택 가장 하단일 경우 삭제
        // 아닐 경우 하위 스택으로 스왑
        releaseTraceId();
    }

    private void syncTraceId() {
        // 스래드에 대한 traceId get
        TraceId traceId = traceIdHolder.get();
        if (traceId == null) {
            // traceId가 없을경우 첫 호출이기 때문에
            // 생성자를 통해 생성하여 set
            traceIdHolder.set(new TraceId());
        } else {
            // traceId에 대한 같은 아이디 값의 다음 레벨 traceId 생성, holder에 set
            traceIdHolder.set(traceId.createNextId());
        }
    }

    private void releaseTraceId() {
        // 스래드에 대한 traceId get
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()) {
            // traceId가 level이 1이면 첫 호출이기 때문에
            // traceIdHolder릴 비움
            traceIdHolder.remove();
        } else {
            // level이 1이 아니면 하위 level로 스왑
            traceIdHolder.set(traceId.createPreviousId());
        }
    }
}
