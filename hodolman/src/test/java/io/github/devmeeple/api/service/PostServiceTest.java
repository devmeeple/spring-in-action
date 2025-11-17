package io.github.devmeeple.api.service;

import io.github.devmeeple.api.domain.Post;
import io.github.devmeeple.api.repository.PostRepository;
import io.github.devmeeple.api.request.PostCreate;
import io.github.devmeeple.api.request.PostEdit;
import io.github.devmeeple.api.request.PostSearch;
import io.github.devmeeple.api.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @DisplayName("글 여러개 조회")
    @Test
    void test3() {
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("foo" + i)
                        .content("bar1" + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);
        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        List<PostResponse> results = postService.getList(postSearch);

        assertThat(results.size()).isEqualTo(10L);
        assertThat(results.get(0).getTitle()).isEqualTo("foo19");
    }

    @DisplayName("글 제목 수정")
    @Test
    void test4() {
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);
        PostEdit postEdit = PostEdit.builder()
                .title("호돌걸")
                .content("반포자이")
                .build();

        postService.edit(post.getId(), postEdit);

        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));
        assertThat(changedPost.getTitle()).isEqualTo("호돌걸");
    }

    @DisplayName("글 내용 수정")
    @Test
    void test5() {
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);
        PostEdit postEdit = PostEdit.builder()
                .title("호돌맨")
                .content("초가집")
                .build();

        postService.edit(post.getId(), postEdit);

        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));
        assertThat(changedPost.getContent()).isEqualTo("초가집");
    }
}
