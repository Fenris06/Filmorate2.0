package ru.fenris06.service.user.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.fenris06.dto.UserDto;
import ru.fenris06.mapper.user.UserMapper;

import ru.fenris06.repository.user.UserRepository;
import ru.fenris06.service.user.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        return UserMapper.toDto(userRepository.save(UserMapper.fromDto(userDto)));
    }
}
