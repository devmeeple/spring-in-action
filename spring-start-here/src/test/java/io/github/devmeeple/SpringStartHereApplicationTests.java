package io.github.devmeeple;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class SpringStartHereApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("/home으로 요청이 성공한다.")
    @Test
    void testPageRequestAndContent() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }
}
