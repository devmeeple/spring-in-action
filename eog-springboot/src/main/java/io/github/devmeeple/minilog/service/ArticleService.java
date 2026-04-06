package io.github.devmeeple.minilog.service;

import io.github.devmeeple.minilog.dto.ArticleResponseDto;
import io.github.devmeeple.minilog.entity.Article;
import io.github.devmeeple.minilog.entity.User;
import io.github.devmeeple.minilog.exception.ArticleNotFoundException;
import io.github.devmeeple.minilog.exception.UserNotFoundException;
import io.github.devmeeple.minilog.repository.ArticleRepository;
import io.github.devmeeple.minilog.repository.UserRepository;
import io.github.devmeeple.minilog.util.EntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ArticleResponseDto createArticle(String content, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("해당 아이디(%d)를 가진 사용자를 찾을 수 없습니다.", userId))
        );
        Article article = Article.builder().content(content).author(user).build();

        Article savedArticle = articleRepository.save(article);
        return EntityDtoMapper.toDto(savedArticle);
    }

    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(String.format("해당 아이디(%d)를 가진 게시글을 찾을 수 없습니다.", articleId))
        );

        articleRepository.deleteById(articleId);
    }

    public ArticleResponseDto updateArticle(Long articleId, String content) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(String.format("해당 아이디(%d)를 가진 게시글을 찾을 수 없습니다.", articleId))
        );
        article.setContent(content);
        Article updatedArticle = articleRepository.save(article);

        return EntityDtoMapper.toDto(updatedArticle);
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto getArticleById(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(String.format("해당 아이디(%d)를 가진 게시글을 찾을 수 없습니다.", articleId))
        );

        return EntityDtoMapper.toDto(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> getFeedListByFollowerId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("해당 아이디(%d)를 가진 사용자를 찾을 수 없습니다.", userId))
        );

        List<Article> feedList = articleRepository.findAllByFollowerId(user.getId());
        return feedList.stream().map(EntityDtoMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> getArticleListByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("해당 아이디(%d)를 가진 사용자를 찾을 수 없습니다.", userId))
        );

        List<Article> articleList = articleRepository.findAllByAuthorId(user.getId());
        return articleList.stream().map(EntityDtoMapper::toDto).toList();
    }
}
