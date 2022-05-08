package app.trace;

import app.trace.logtrace.LogTrace;
import app.trace.logtrace.LogTraceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConf {

    @Bean
    public LogTrace logTrace(){
        return new LogTraceImpl();
    }
}
