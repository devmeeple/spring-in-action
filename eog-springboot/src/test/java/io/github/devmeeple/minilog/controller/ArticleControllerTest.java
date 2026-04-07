package io.github.devmeeple.minilog.controller;

import io.github.devmeeple.minilog.dto.ArticleRequestDto;
import io.github.devmeeple.minilog.dto.ArticleResponseDto;
import io.github.devmeeple.minilog.exception.ArticleNotFoundException;
import io.github.devmeeple.minilog.service.ArticleService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class) // 웹 레이어만 테스트
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @MockitoBean
    private ArticleService articleService;

    private ObjectMapper objectMapper = new ObjectMapper();

    LocalDateTime fixtureDateTime = LocalDateTime.of(2026, 1, 1, 0, 0, 0);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String formattedFixtureDateTime = fixtureDateTime.format(formatter);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateArticle() throws Exception {
        ArticleRequestDto requestDto = ArticleRequestDto.builder()
                .authorId(1L)
                .content("Test Content")
                .build();
        ArticleResponseDto responseDto = ArticleResponseDto.builder()
                .articleId(1L)
                .content("Test Content")
                .authorId(1L)
                .authorName("Test User")
                .createdAt(fixtureDateTime)
                .build();
        when(articleService.createArticle(any(String.class), anyLong())).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articleId").value(1L))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.authorId").value(1L))
                .andExpect(jsonPath("$.authorName").value("Test User"))
                .andExpect(jsonPath("$.createdAt").value(formattedFixtureDateTime));
    }

    @Test
    void testGetArticle() throws Exception {
        ArticleResponseDto responseDto = ArticleResponseDto.builder()
                .articleId(1L)
                .content("Test Content")
                .authorId(1L)
                .authorName("Test User")
                .createdAt(fixtureDateTime)
                .build();
        when(articleService.getArticleById(anyLong())).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/article/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articleId").value(1L))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.authorId").value(1L))
                .andExpect(jsonPath("$.authorName").value("Test User"))
                .andExpect(jsonPath("$.createdAt").value(formattedFixtureDateTime));
    }

    @Test
    void testUpdateArticle() throws Exception {
        ArticleRequestDto requestDto = ArticleRequestDto.builder()
                .authorId(1L)
                .content("Test Content")
                .build();
        ArticleResponseDto responseDto = ArticleResponseDto.builder()
                .articleId(1L)
                .content("Updated Content")
                .authorId(1L)
                .authorName("Test User")
                .createdAt(fixtureDateTime)
                .build();
        when(articleService.updateArticle(anyLong(), any(String.class))).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/article/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articleId").value(1L))
                .andExpect(jsonPath("$.content").value("Updated Content"))
                .andExpect(jsonPath("$.authorId").value(1L))
                .andExpect(jsonPath("$.authorName").value("Test User"))
                .andExpect(jsonPath("$.createdAt").value(formattedFixtureDateTime));
    }

    @Test
    void testDeleteArticle() throws Exception {
        mockMvc.perform(delete("/api/v1/article/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetArticleByUserId() throws Exception {
        ArticleResponseDto responseDto = ArticleResponseDto.builder()
                .articleId(1L)
                .content("Test Content")
                .authorId(1L)
                .authorName("Test User")
                .createdAt(fixtureDateTime)
                .build();
        List<ArticleResponseDto> responseList = Collections.singletonList(responseDto);
        when(articleService.getArticleListByUserId(anyLong())).thenReturn(responseList);

        mockMvc.perform(get("/api/v1/article").param("authorId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].articleId").value(1L))
                .andExpect(jsonPath("$[0].content").value("Test Content"))
                .andExpect(jsonPath("$[0].authorId").value(1L))
                .andExpect(jsonPath("$[0].authorName").value("Test User"))
                .andExpect(jsonPath("$[0].createdAt").value(formattedFixtureDateTime));
    }

    @Test
    void testGlobalExceptionHandler() throws Exception {
        when(articleService.getArticleById(anyLong()))
                .thenThrow(new ArticleNotFoundException("Article Not Found"));

        mockMvc.perform(get("/api/v1/article/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Article Not Found"));
    }
}
