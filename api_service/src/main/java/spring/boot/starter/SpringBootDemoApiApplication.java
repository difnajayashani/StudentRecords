package spring.boot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@ComponentScan({"controller","models","hsenid.com"})
public class SpringBootDemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApiApplication.class, args);
    }
}