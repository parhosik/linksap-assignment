package com.sap.ae.controller.common;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ae.model.common.User;
import com.sap.ae.repository.common.RoleRepository;
import com.sap.ae.repository.common.UserRepository;

@RestController
@RequestMapping("/api/v2.0/users")
public class UserController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("/listapproveusers")
    public List<User> listusers()
    {
        List<User> u = userRepository.findAllByIsenabletrue();
        return u;
    }













}
