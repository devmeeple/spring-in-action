package io.github.devmeeple.ch09.controllers;

import io.github.devmeeple.ch09.model.LoginProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoginControllerUnitTest {

    @Mock
    private Model model;

    @Mock
    private LoginProcessor loginProcessor;

    @InjectMocks
    private LoginController loginController;

    @DisplayName("로그인이 성공하면, 성공 메시지를 모델에 담고 로그인 페이지 뷰를 반환한다.")
    @Test
    void loginPostLoginSucceedsTest() {
        given(loginProcessor.login()).willReturn(true);

        String result = loginController.loginPost("username", "password", model);

        assertThat(result).isEqualTo("/ch09/login.html");
        verify(model).addAttribute("message", "You are now logged in.");
    }

    @DisplayName("로그인이 실패하면, 실패 메시지를 모델에 담고 로그인 페이지 뷰를 반환한다.")
    @Test
    void loginPostLoginFailsTest() {
        given(loginProcessor.login()).willReturn(false);

        String result = loginController.loginPost("username", "password", model);

        assertThat(result).isEqualTo("/ch09/login.html");
        verify(model).addAttribute("message", "Login failed!");
    }
}
