package io.github.devmeeple.service;

import io.github.devmeeple.domain.Post;
import io.github.devmeeple.repository.PostRepository;
import io.github.devmeeple.request.PostCreate;
import io.github.devmeeple.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @DisplayName("글 작성")
    @Test
    void test1() {
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postService.write(postCreate);

        assertThat(postRepository.count()).isEqualTo(1L);
        Post post = postRepository.findAll().get(0);
        assertThat(post.getTitle()).isEqualTo("제목입니다.");
        assertThat(post.getContent()).isEqualTo("내용입니다.");
    }

    @DisplayName("글 1개 조회")
    @Test
    void test2() {
        Post post = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(post);

        PostResponse result = postService.get(post.getId());

        assertThat(result).isNotNull();
        assertThat(post.getTitle()).isEqualTo("foo");
        assertThat(post.getContent()).isEqualTo("bar");
        assertThat(postRepository.count()).isEqualTo(1L);
    }
}
