package com.sap.ae.repository.common;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sap.ae.model.common.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);


    @Query(value = "select u.name ,u.email from User u where u.enable= true")
    List<User> findAllByIsenabletrue();



}
