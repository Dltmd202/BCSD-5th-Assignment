package app.domain.service;

import app.domain.repository.HelloRepository;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public String hello(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        return helloRepository.findHello();
    }

    public String helloBody(){
        return helloRepository.findHelloBody();
    }

    public String helloEx() {
        throw new IllegalArgumentException();
    }
}
