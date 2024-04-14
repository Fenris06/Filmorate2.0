package ru.fenris06.mapper.user;

import ru.fenris06.dto.UserDto;
import ru.fenris06.model.user.User;

public class UserMapper {

    public static User fromDto(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        if (userDto.getName() == null || userDto.getName().isBlank()) {
            user.setName(userDto.getLogin());
        } else {
            user.setName(userDto.getName().trim());
        }
        user.setBirthday(userDto.getBirthday());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setName(user.getName());
        userDto.setBirthday(user.getBirthday());
        return userDto;
    }
}
