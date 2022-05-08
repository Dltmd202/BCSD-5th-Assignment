package app.trace;

/**
 * traceId와 실행 시작 정보, 호출된 메시지 시그니처가 저장된다
 */
public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message){
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public String getMessage() {
        return message;
    }
}
