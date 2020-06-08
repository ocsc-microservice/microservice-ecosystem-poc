package com.ocsc.poc.feign;


import com.ocsc.poc.model.UserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface IUserService {

    @GetMapping("/user/validate")
    public UserDetails getUserbyId(@RequestHeader("emailId") String emailId,
                                   @RequestHeader("password") String password);
}
