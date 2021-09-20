package com.sap.ae.repository.common;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.ae.model.common.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findOneById(Long id);



}
