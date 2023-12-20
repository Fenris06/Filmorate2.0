package ru.fenris06.comtroller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.fenris06.dto.UserDto;
import ru.fenris06.exception.ErrorResponse;
import ru.fenris06.exception.NotFoundException;
import ru.fenris06.service.user.UserService;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    private UserDto userDto1;
    private UserDto userDto2;
    private UserDto userDto3;

    @BeforeEach
    public void beforeSetup() {
        userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setEmail("fenris06@rambler.ru");
        userDto1.setLogin("fenris06");
        userDto1.setName("Artem");
        userDto1.setBirthday(LocalDate.of(1988, 9, 25));

        userDto2 = new UserDto();
        userDto2.setId(2L);
        userDto2.setEmail("ivan06@rambler.ru");
        userDto2.setLogin("ivan06");
        userDto2.setName("Artem");
        userDto2.setBirthday(LocalDate.of(1987, 4, 12));

        userDto3 = new UserDto();
        userDto3.setId(3L);
        userDto3.setEmail("andrey06@rambler.ru");
        userDto3.setLogin("andrey06");
        userDto3.setName("Andrey");
        userDto3.setBirthday(LocalDate.of(1987, 10, 24));
    }

    @SneakyThrows
    @Test
    void crateUser_IfUserFieldsCorrect_AndReturnUserDto() {
        when(userService.createUser(userDto1)).thenReturn(userDto1);

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(userDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userDto1.getId()), Long.class))
                .andExpect(jsonPath("$.email", is(userDto1.getEmail())))
                .andExpect(jsonPath("$.login", is(userDto1.getLogin())))
                .andExpect(jsonPath("$.name", is(userDto1.getName())))
                .andExpect(jsonPath("$.birthday", is(userDto1.getBirthday().toString())));
    }

    @SneakyThrows
    @Test
    void updateUser() {
        when(userService.updateUser(userDto1)).thenReturn(userDto1);

        mockMvc.perform(put("/users")
                        .content(objectMapper.writeValueAsString(userDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userDto1.getId()), Long.class))
                .andExpect(jsonPath("$.email", is(userDto1.getEmail())))
                .andExpect(jsonPath("$.login", is(userDto1.getLogin())))
                .andExpect(jsonPath("$.name", is(userDto1.getName())))
                .andExpect(jsonPath("$.birthday", is(userDto1.getBirthday().toString())));
    }

    @SneakyThrows
    @Test
    void getUsers_IfUserFound_ThenReturnLisOfUsers() {
        when(userService.getUsers()).thenReturn(List.of(userDto1, userDto2, userDto3));

        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(userDto1.getId()), Long.class))
                .andExpect(jsonPath("$[0].email", is(userDto1.getEmail())))
                .andExpect(jsonPath("$[0].login", is(userDto1.getLogin())))
                .andExpect(jsonPath("$[0].name", is(userDto1.getName())))
                .andExpect(jsonPath("$[0].birthday", is(userDto1.getBirthday().toString())));
    }

    @SneakyThrows
    @Test
    void getUser_IfUserFoundById_ThenReturnUser() {
        Long id = 1L;
        when(userService.getUser(id)).thenReturn(userDto1);

        mockMvc.perform(get("/users/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userDto1.getId()), Long.class))
                .andExpect(jsonPath("$.email", is(userDto1.getEmail())))
                .andExpect(jsonPath("$.login", is(userDto1.getLogin())))
                .andExpect(jsonPath("$.name", is(userDto1.getName())))
                .andExpect(jsonPath("$.birthday", is(userDto1.getBirthday().toString())));
    }

    @SneakyThrows
    @Test
    void getUser_IfUserNotFoundById_ThenTrowException() {
        Long id = 1L;
        when(userService.getUser(id)).thenThrow(new NotFoundException("User not found"));
        ErrorResponse response = new ErrorResponse("404", "Not Found", "User not found", "", LocalDateTime.now());
        mockMvc.perform(get("/users/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(response.getStatus())))
                .andExpect(jsonPath("$.response", is(response.getResponse())))
                .andExpect(jsonPath("$.message", is(response.getMessage())));
    }

    @SneakyThrows
    @Test
    void addFriend() {
        Long id = 1L;
        Long fiendId = 2L;
        mockMvc.perform(put("/users/{id}/friends/{friendId}", id, fiendId))
                .andExpect(status().isOk());

        verify(userService, times(1)).addFriend(id, fiendId);
    }

    @SneakyThrows
    @Test
    void getFriends_IfUsersWereFound_ThanReturnListOfUsers() {
        Long id = 1L;
        when(userService.getFriends(id)).thenReturn(List.of(userDto1, userDto2, userDto3));

        mockMvc.perform(get("/users/{id}/friends", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(userDto1.getId()), Long.class))
                .andExpect(jsonPath("$[1].id", is(userDto2.getId()), Long.class))
                .andExpect(jsonPath("$[2].id", is(userDto3.getId()), Long.class));

        verify(userService, times(1)).getFriends(id);
    }

    @SneakyThrows
    @Test
    void deleteFriend_IfFriendFound_TheDeleteFriend() {
        Long id = 1L;
        Long fiendId = 2L;

        mockMvc.perform(delete("/users/{id}/friends/{friendId}", id, fiendId))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteFriend(id, fiendId);
    }

    @SneakyThrows
    @Test
    void getCommon_IfFriendsFound_ThanReturnListOfCommonFriends() {
        Long id = 1L;
        Long fiendId = 2L;

        when(userService.getCommon(id, fiendId)).thenReturn(List.of(userDto3));

        mockMvc.perform(get("/users//{id}/friends/common/{otherId}", id, fiendId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(userDto3.getId()), Long.class));

        verify(userService, times(1)).getCommon(id, fiendId);
    }
}