package app.trace;

import java.util.UUID;

public class TraceId {

    private String id;
    private int level;

    public TraceId(){
        this.id = createId();
        this.level = 0;
    }

    public TraceId(String id, int level){
        this.id = id;
        this.level = level;
    }

    //하나의 요청에 대한 식별자
    //UUID 8자리로 자른 값
    private String createId(){
        return UUID.randomUUID().toString().substring(0, 8);
    }

    //다음 레벨의 TraceId는
    //식별자를 계승하고 level을 +1
    public TraceId createNextId(){
        return new TraceId(id, level + 1);
    }

    //하위 레벨의 TraceId 생성은
    //식별자를 계승하고 level을 -1
    public TraceId createPreviousId(){
        return new TraceId(id, level - 1);
    }

    //첫 번째 호출인지 검사용
    public boolean isFirstLevel(){
        return level == 0;
    }

    //getter
    public String getId(){
        return id;
    }

    public int getLevel(){
        return level;
    }
}
