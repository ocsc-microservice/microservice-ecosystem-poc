package com.ocsc.poc.controller;

import com.ocsc.poc.feign.IUserService;
import com.ocsc.poc.model.ApplicationUser;
import com.ocsc.poc.model.AuthenticationResponse;
import com.ocsc.poc.model.UserDetails;
import com.ocsc.poc.util.BuisnessException;
import com.ocsc.poc.util.JWTTokenHelper;
import com.ocsc.poc.util.RecordNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    private IUserService userService;


    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody ApplicationUser user) {
      try{
          UserDetails userDetails = userService.getUserbyId(user.getUserName(), user.getPassword());
          if(userDetails.getUserId() != null){
              return new AuthenticationResponse(JWTTokenHelper.createToken(userDetails.getEmailId()));
          }else{
              throw new BuisnessException("Username and password is invalid. Please check and try again");
          }

      }catch(RecordNotFoundException ex) {
          throw new BuisnessException("Username and password is invalid. Please check and try again");
      }
    }
}
