package io.github.devmeeple.repository;

import io.github.devmeeple.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
