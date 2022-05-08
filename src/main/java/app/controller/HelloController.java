package app.controller;

import app.annotation.Trace;
import app.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final HelloService helloService;
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Trace
    @RequestMapping(value = "/helloBody", method = RequestMethod.GET)
    @ResponseBody
    public String helloBody() {
        logger.info("fuck");
        return helloService.helloBody();
    }

    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello");
        return helloService.hello();
    }
}
