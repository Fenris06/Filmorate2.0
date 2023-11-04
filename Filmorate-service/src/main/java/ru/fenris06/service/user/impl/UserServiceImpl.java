package ru.fenris06.service.user.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.fenris06.dto.UserDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.mapper.user.UserMapper;

import ru.fenris06.model.user.User;
import ru.fenris06.repository.user.UserRepository;
import ru.fenris06.service.user.UserService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        return UserMapper.toDto(userRepository.save(UserMapper.fromDto(userDto)));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = findUserById(userDto.getId());
        updateUserFields(user, userDto);
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id) {
        return UserMapper.toDto(findUserById(id));
    }

    @Override
    public void addFriend(Long id, Long friendId) {
        User user = findUserById(id);
        User friend = findUserById(friendId);
        user.getFriends().add(friend);
        userRepository.saveAll(List.of(user, friend));
    }

    @Override
    public List<UserDto> getFriends(Long id) {
        User user = findUserById(id);
        return user.getFriends()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFriend(Long id, Long friendId) {
        User user = findUserById(id);
        User deleteUser = findUserById(friendId);
        user.getFriends().remove(deleteUser);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getCommon(Long id, Long otherId) {
        return userRepository.commonFriends(id, otherId)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    private void updateUserFields(User user, UserDto userDto) {
        Optional.ofNullable(userDto.getName()).ifPresent(user::setName);
        Optional.ofNullable(userDto.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userDto.getLogin()).ifPresent(user::setLogin);
        Optional.ofNullable(userDto.getBirthday()).ifPresent(user::setBirthday);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with this id = " + id + " not found"));
    }
}
