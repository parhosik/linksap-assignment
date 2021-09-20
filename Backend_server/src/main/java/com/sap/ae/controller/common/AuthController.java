package com.sap.ae.controller.common;


import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ae.dto.common.UserDto;
import com.sap.ae.repository.common.UserRepository;
import com.sap.ae.service.common.UserService;
import com.sap.ae.util.Message.Message;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/register")
    public ResponseEntity<Message<String>> registeruser(@RequestBody @Valid UserDto user) {


        try {

            ObjectMapper objectMapper = new ObjectMapper();

            log.info("New User Registeration Request" + new Date()+"");
            log.info("User===> "+ user.getEmail());
            Message<String> res = userService.createUser(user);
            log.info("Registeration Result ===> "+ res.getStatus()+" "+res.getMessage());
            return ResponseEntity.status(res.getStatus()).body(res);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


    }




}