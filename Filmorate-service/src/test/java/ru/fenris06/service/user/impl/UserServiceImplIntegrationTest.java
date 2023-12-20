package ru.fenris06.service.user.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.fenris06.dto.UserDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.model.user.User;
import ru.fenris06.repository.user.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class UserServiceImplIntegrationTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    public User user1;
    public User user2;
    private User user3;
    private UserDto userDto1;
    private UserDto userDto2;
    private UserDto userDto3;

    @BeforeEach
    public void beforeTest() {
        user1 = new User();
        user1.setEmail("fenris06@rambler.ru");
        user1.setLogin("fenris06");
        user1.setName("Artem");
        user1.setBirthday(LocalDate.of(1988, 9, 25));

        userDto1 = new UserDto();
        userDto1.setEmail("fenris06@rambler.ru");
        userDto1.setLogin("fenris06");
        userDto1.setName("Artem");
        userDto1.setBirthday(LocalDate.of(1988, 9, 25));

        user2 = new User();
        user2.setEmail("ivan06@rambler.ru");
        user2.setLogin("ivan06");
        user2.setName("Artem");
        user2.setBirthday(LocalDate.of(1987, 4, 12));

        userDto2 = new UserDto();
        userDto2.setEmail("ivan06@rambler.ru");
        userDto2.setLogin("ivan06");
        userDto2.setName("Artem");
        userDto2.setBirthday(LocalDate.of(1987, 4, 12));

        user3 = new User();
        user3.setEmail("andrey06@rambler.ru");
        user3.setLogin("andrey06");
        user3.setName("Andrey");
        user3.setBirthday(LocalDate.of(1987, 10, 24));

        userDto3 = new UserDto();
        userDto3.setEmail("andrey06@rambler.ru");
        userDto3.setLogin("andrey06");
        userDto3.setName("Andrey");
        userDto3.setBirthday(LocalDate.of(1987, 10, 24));
    }

    @Test
    public void createUser_IfAllUserFieldsAreCorrect_ThanSaveUser() {
        final UserDto testDtoUser = userService.createUser(userDto1);


        assertEquals(testDtoUser.getEmail(), userDto1.getEmail());
        assertEquals(testDtoUser.getLogin(), userDto1.getLogin());
        assertEquals(testDtoUser.getName(), userDto1.getName());
        assertEquals(testDtoUser.getBirthday(), userDto1.getBirthday());
    }

    @Test
    public void updateUser_IfUserFound_ThenUpdateUser() {
        final User saveUser = userRepository.save(user1);

        userDto2.setId(saveUser.getId());
        final UserDto updateUser = userService.updateUser(userDto2);

        assertEquals(updateUser.getId(), saveUser.getId());
        assertEquals(updateUser.getEmail(), userDto2.getEmail());
        assertEquals(updateUser.getLogin(), userDto2.getLogin());
        assertEquals(updateUser.getName(), userDto2.getName());
        assertEquals(updateUser.getBirthday(), userDto2.getBirthday());
    }

    @Test
    public void getUsers_IfUserAdd_ThenReturnListOrUsersDto() {
        final User testUser1 = userRepository.save(user1);
        final User testUser2 = userRepository.save(user2);
        final User testUser3 = userRepository.save(user3);

        final List<UserDto> testUsers = userService.getUsers();

        assertEquals(testUsers.size(), 3);
        assertEquals(testUsers.get(0).getId(), testUser1.getId());
        assertEquals(testUsers.get(1).getId(), testUser2.getId());
        assertEquals(testUsers.get(2).getId(), testUser3.getId());
    }

    @Test
    public void getUser_IfUserFound_ThanReturnUserDto() {
        final User user = userRepository.save(user1);

        final UserDto testUser = userService.getUser(user.getId());

        assertEquals(testUser.getId(), user.getId());
        assertEquals(testUser.getEmail(), user.getEmail());
        assertEquals(testUser.getLogin(), user.getLogin());
        assertEquals(testUser.getName(), user.getName());
        assertEquals(testUser.getBirthday(), user.getBirthday());
    }

    @Test
    public void getUser_IfUserNotFound_ThanReturnNotFoundException() {
        Long id = 9999L;

        final Exception exception = assertThrows(NotFoundException.class, () -> userService.getUser(id));

        assertEquals(exception.getMessage(), "User with this id = " + id + " not found");
    }


//    @Test
//    void addFriend_IfFriendFound() {
//        final User testUser1 = userRepository.save(user1);
//        final User testUser2 = userRepository.save(user2);
//        final User testUser3 = userRepository.save(user3);
//
//        userService.addFriend(testUser1.getId(), testUser3.getId());
//
//        final User userWithFriend = userRepository.findById(testUser1.getId()).orElseThrow(()-> new NotFoundException("User not found"));
//
//        assertEquals(userWithFriend.getFriends().size(), 1);
//    }

    @Test
    void getFriends() {
    }

    @Test
    void deleteFriend() {
    }

    @Test
    void getCommon() {
    }
}