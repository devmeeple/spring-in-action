package io.github.devmeeple.minilog.controller;

import io.github.devmeeple.minilog.dto.UserRequestDto;
import io.github.devmeeple.minilog.dto.UserResponseDto;
import io.github.devmeeple.minilog.exception.UserNotFoundException;
import io.github.devmeeple.minilog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @MockitoBean
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() throws Exception {
        List<UserResponseDto> userResponseDtoList = List.of(
                UserResponseDto.builder().id(1L).username("Test User").build(),
                UserResponseDto.builder().id(2L).username("Test User 2").build()
        );
        UserResponseDto.builder().id(1L).username("Test User").build();
        when(userService.getUsers()).thenReturn(userResponseDtoList);

        mockMvc.perform(get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].username").value("Test User"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].username").value("Test User 2"));
    }

    @Test
    void testGetUserById() throws Exception {
        UserResponseDto userResponseDto = UserResponseDto.builder().id(1L).username("Test User").build();
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(userResponseDto));

        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("Test User"));
    }

    @Test
    void testCreateUser() throws Exception {
        UserRequestDto userRequestDto = UserRequestDto.builder().username("Test User").password("password").build();
        UserResponseDto userResponseDto = UserResponseDto.builder().id(1L).username("Test User").build();
        when(userService.createUser(any(UserRequestDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("Test User"));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserRequestDto userRequestDto = UserRequestDto.builder().username("Test User").password("password").build();
        UserResponseDto userResponseDto = UserResponseDto.builder().id(1L).username("Test User").build();
        when(userService.updateUser(anyLong(), any(UserRequestDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(put("/api/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("Test User"));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/user/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGlobalExceptionHandler() throws Exception {
        when(userService.getUserById(anyLong())).thenThrow(new UserNotFoundException("User Not Found"));

        mockMvc.perform(get("/api/v1/user/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User Not Found"));
    }
}
