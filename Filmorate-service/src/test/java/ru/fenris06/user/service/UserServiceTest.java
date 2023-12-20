package ru.fenris06.user.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.fenris06.dto.UserDto;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.model.user.User;
import ru.fenris06.repository.user.UserRepository;

import ru.fenris06.service.user.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    @Captor
    private ArgumentCaptor<List<User>> listArgumentCaptor;
    public User user1;
    public User user2;
    private User user3;
    private UserDto userDto1;
    private UserDto userDto2;
    private UserDto userDto3;

    @BeforeEach
    public void before() {
        user1 = new User();
        user1.setId(1L);
        user1.setEmail("fenris06@rambler.ru");
        user1.setLogin("fenris06");
        user1.setName("Artem");
        user1.setBirthday(LocalDate.of(1988, 9, 25));
        user1.setFriends(new HashSet<>());

        userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setEmail("fenris06@rambler.ru");
        userDto1.setLogin("fenris06");
        userDto1.setName("Artem");
        userDto1.setBirthday(LocalDate.of(1988, 9, 25));

        user2 = new User();
        user2.setId(2L);
        user2.setEmail("ivan06@rambler.ru");
        user2.setLogin("ivan06");
        user2.setName("Artem");
        user2.setBirthday(LocalDate.of(1987, 4, 12));
        user2.setFriends(new HashSet<>());

        userDto2 = new UserDto();
        userDto2.setId(2L);
        userDto2.setEmail("ivan06@rambler.ru");
        userDto2.setLogin("ivan06");
        userDto2.setName("Artem");
        userDto2.setBirthday(LocalDate.of(1987, 4, 12));

        user3 = new User();
        user3.setId(3L);
        user3.setEmail("andrey06@rambler.ru");
        user3.setLogin("andrey06");
        user3.setName("Andrey");
        user3.setBirthday(LocalDate.of(1987, 10, 24));
        user3.setFriends(new HashSet<>());

        userDto3 = new UserDto();
        userDto3.setId(3L);
        userDto3.setEmail("andrey06@rambler.ru");
        userDto3.setLogin("andrey06");
        userDto3.setName("Andrey");
        userDto3.setBirthday(LocalDate.of(1987, 10, 24));
    }

    @Test
    public void createUser_IfUserValid_ThenReturnUser() {
        Mockito.when(userRepository.save(any())).thenReturn(user1);
        UserDto createUser = userService.createUser(userDto1);
        assertEquals(createUser.getId(), userDto1.getId());
        verify(userRepository).save(any());
    }

    @Test
    public void updateUser_IfUserIsFound_ThenReturnUserWithUpdateFields() {
        UserDto updateUserDto = new UserDto();
        updateUserDto.setId(1L);
        updateUserDto.setEmail("fenris07@rambler.ru");
        updateUserDto.setLogin("fenris07");
        updateUserDto.setName("Ivan");
        updateUserDto.setBirthday(LocalDate.of(1912, 9, 25));

        User updateUSer = new User();
        updateUSer.setId(1L);
        updateUSer.setEmail("fenris07@rambler.ru");
        updateUSer.setLogin("fenris07");
        updateUSer.setName("Ivan");
        updateUSer.setBirthday(LocalDate.of(1912, 9, 25));

        Mockito.when(userRepository.findById(updateUserDto.getId())).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.save(any())).thenReturn(updateUSer);

        userService.updateUser(updateUserDto);

        verify(userRepository).save(userArgumentCaptor.capture());
        User saveUser = userArgumentCaptor.getValue();

        assertEquals(updateUSer.getId(), saveUser.getId());
        assertEquals(updateUSer.getEmail(), saveUser.getEmail());
        assertEquals(updateUSer.getLogin(), saveUser.getLogin());
        assertEquals(updateUSer.getName(), saveUser.getName());
        assertEquals(updateUSer.getBirthday(), saveUser.getBirthday());
    }

    @Test
    public void updateUser_IfUserNotFound_ThenThrowException() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        final NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.updateUser(userDto1));

        assertEquals(exception.getMessage(), "User with this id = " + id + " not found");
        verify(userRepository, never()).save(user1);
    }

    @Test
    public void getUser_IfUserNotFound_ThenReturnException() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        final NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getUser(id));

        assertEquals(exception.getMessage(), "User with this id = " + id + " not found");
        verify(userRepository, never()).save(new User());
    }

    @Test
    public void getUser_IfUserFound_ThenReturnException() {
        Long id = 1L;

        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(user1));

        final UserDto testUserDto = userService.getUser(id);

        assertEquals(testUserDto.getId(), userDto1.getId());
        assertEquals(testUserDto.getEmail(), userDto1.getEmail());
        assertEquals(testUserDto.getLogin(), userDto1.getLogin());
        assertEquals(testUserDto.getName(), userDto1.getName());
        assertEquals(testUserDto.getBirthday(), userDto1.getBirthday());
    }

    @Test
    public void getUsers_IfUsersFound_ThenReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user1, user2, user3));

        final List<UserDto> testUsersDto = userService.getUsers();

        assertEquals(testUsersDto.size(), 3);
        assertEquals(testUsersDto.get(1).getId(), userDto2.getId());
        assertEquals(testUsersDto.get(2).getId(), userDto3.getId());
    }

//    @Test
//    public void addFriend_IfUserAndFriendFound_FriendAdd() {
//        Long id = 1L;
//        Long fiendId = 2L;
//
//        when(userRepository.findById(id)).thenReturn(Optional.of(user1));
//        when(userRepository.findById(fiendId)).thenReturn(Optional.of(user2));
//
//        userService.addFriend(id, fiendId);
//
//        verify(userRepository).saveAll(listArgumentCaptor.capture());
//        final List<User> testUsers = listArgumentCaptor.getValue();
//
//        assertEquals(testUsers.get(0).getId(), user1.getId());
//        assertEquals(testUsers.get(1).getId(), user2.getId());
//
//        final List<User> testFriends = new ArrayList<>(testUsers.get(0).getFriends());
//
//        assertEquals(testFriends.size(), 1);
//        assertEquals(testFriends.get(0).getId(), user2.getId());
//
//        verify(userRepository, times(2)).findById(anyLong());
//        verify(userRepository, times(1)).saveAll(anyList());
//    }

    @Test
    public void getFriends_IfFriendsAdd_ReturnListUserDto() {
        Long id = 1L;
        user1.setFriends(Set.of(user2, user3));

        when(userRepository.findById(id)).thenReturn(Optional.of(user1));

        final List<UserDto> testFriends = userService.getFriends(id);

        assertEquals(testFriends.size(), 2);
        verify(userRepository, times(1)).findById(anyLong());
    }
}
