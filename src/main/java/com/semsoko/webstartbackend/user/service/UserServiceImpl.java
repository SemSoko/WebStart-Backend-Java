package com.semsoko.webstartbackend.user.service;

import com.semsoko.webstartbackend.user.model.UserEntity;
import com.semsoko.webstartbackend.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
