package com.nhn.nhnsoft.jobray.userservice.domain.user.application.impl;

import com.nhn.nhnsoft.jobray.userservice.domain.user.application.UserJoinService;
import com.nhn.nhnsoft.jobray.userservice.domain.user.application.exception.UserAlreadyExistException;
import com.nhn.nhnsoft.jobray.userservice.domain.user.domain.User;
import com.nhn.nhnsoft.jobray.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoinServiceImpl implements UserJoinService {

    private final UserRepository userRepository;

    @Override
    public User join(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }
}
