package app.domain.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {

    //문자열 반환
    public String findHello(){
        return "Hello";
    }

    //문자열 반환
    public String findHelloBody() {
        return "HelloBody";
    }
}
