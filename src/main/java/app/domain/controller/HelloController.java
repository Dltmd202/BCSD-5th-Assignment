package app.domain.controller;

import app.domain.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final HelloService helloService;

    //생성자 주입
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    //1초 대기하고 값을 반환
    @RequestMapping(value = "/helloBody", method = RequestMethod.GET)
    @ResponseBody
    public String helloBody() {
        helloService.helloBody();
        return "helloBody";
    }

    //정상 비즈니스 로직
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String hello(){
        return helloService.hello();
    }

    //service에서 exception 을 터뜨리는 호출
    @RequestMapping(value = "/helloEx" ,method = RequestMethod.GET)
    public String helloEx(){
        try {
            helloService.helloEx();
        } catch (Exception e){

        }
        return "hello";
    }
}
