package com.semsoko.webstartbackend.user.service;

import com.semsoko.webstartbackend.user.model.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);
}
