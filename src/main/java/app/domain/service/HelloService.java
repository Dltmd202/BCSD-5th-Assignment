package app.domain.service;

import app.domain.repository.HelloRepository;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    //1초 대기
    //레포지토리에 요청
    public String hello(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        return helloRepository.findHello();
    }

    //레포지토리에 요청
    public String helloBody(){
        return helloRepository.findHelloBody();
    }

    //exception throw
    public String helloEx() {
        throw new IllegalArgumentException();
    }
}
