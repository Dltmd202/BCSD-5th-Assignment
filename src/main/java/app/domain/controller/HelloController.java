package app.domain.controller;

import app.domain.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(value = "/helloBody", method = RequestMethod.GET)
    @ResponseBody
    public String helloBody() {
        helloService.helloBody();
        return "helloBody";
    }

    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String hello(){
        return helloService.hello();
    }

    @RequestMapping(value = "/helloEx" ,method = RequestMethod.GET)
    public String helloEx(){
        return helloService.helloEx();
    }
}
