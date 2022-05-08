package app.domain.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {

    public String findHello(){
        return "Hello";
    }

    public String findHelloBody() {
        return "HelloBody";
    }
}
