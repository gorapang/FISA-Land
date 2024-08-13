package com.fisa.land.fisaland.common.service;

import com.fisa.land.fisaland.common.dto.LoginDTO;
import com.fisa.land.fisaland.common.dto.UserDTO;

public interface UserService {

	void register(UserDTO user);

	UserDTO login(LoginDTO user);
	
}
