package io.github.devmeeple.minilog.repository;

import io.github.devmeeple.minilog.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {}
