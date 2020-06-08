package com.ocsc.poc;


import com.ocsc.poc.util.FeignExceptionHandler;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FeignExceptionHandler feignErrorDecoder(){
        return new FeignExceptionHandler();
    }

}
