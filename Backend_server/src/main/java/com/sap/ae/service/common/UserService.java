package com.sap.ae.service.common;


import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sap.ae.dto.common.UserDto;
import com.sap.ae.model.common.Role;
import com.sap.ae.model.common.User;
import com.sap.ae.repository.common.RoleRepository;
import com.sap.ae.repository.common.UserRepository;
import com.sap.ae.service.common.iUserService;
import com.sap.ae.util.Message.Message;

@Service
public class UserService implements iUserService , UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public Message<String> createUser(@Valid UserDto userDto) throws Exception {
        try {
            User user = new User();
            String password = userDto.getPassword();
            String hash = new BCryptPasswordEncoder().encode(password);
            String userEmail = userDto.getEmail();

            Long userId;
            user.setPassword(hash);
            user.setEmail(userEmail);
            user.setName(userDto.getName());

            User callUser = userRepository.findOneByEmail(userEmail);
            log.info("User not found");

            if (callUser == null) {
                Role role = roleRepository.findOneById(2L);
                user.setRole(role);
                userRepository.saveAndFlush(user);
                Message<String> messages = new Message<>();
                messages.setData(user.getName())
                        .setMessage("user created successfully")
                        .setStatus(200)
                        .setCode("successful");
                return messages;
            }


            else {

                Message<String> messages = new Message<>();
                messages.setStatus(400).setMessage("This email  already exist."
                ).setCode("unsuccessful");
                return messages;

            }
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("This email  already exist."
                 ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }






    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("Login request from user " + s + new Date());
        User user =  userRepository.findOneByEmail(s);
        if(user == null)
        {
            log.info("UserName not found ");
            return new User();
        }
        log.info("UserName found "+user.getEmail());
        return user;
    }







}
