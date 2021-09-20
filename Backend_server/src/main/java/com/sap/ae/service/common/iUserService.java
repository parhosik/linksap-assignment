package com.sap.ae.service.common;

import com.sap.ae.dto.common.UserDto;
import com.sap.ae.util.Message.Message;

public interface iUserService {
    Message<String> createUser(UserDto userDto) throws Exception;

}
