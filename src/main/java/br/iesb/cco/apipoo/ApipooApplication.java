package br.iesb.cco.apipoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ApipooApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApipooApplication.class, args);
    }

}
