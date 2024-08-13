package com.fisa.land.fisaland.common.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.dto.LoginDTO;
import com.fisa.land.fisaland.common.dto.UserDTO;
import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
	
	@Override
	public void register(UserDTO userDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
	}

	@Override
	public UserDTO login(LoginDTO loginDTO) {
		 Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());
		
		 // 사용자 존재 여부 및 비밀번호 확인
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 비밀번호가 일치하는지 확인
            if (user.getPassword().equals(loginDTO.getPw())) {
                // User를 UserDTO로 변환하여 반환
            	 return mapper.map(user, UserDTO.class);
            } else {
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }
}

