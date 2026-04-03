package io.github.devmeeple.todo.util;

import io.github.devmeeple.todo.dto.TodoRequestDto;
import io.github.devmeeple.todo.dto.TodoResponseDto;
import io.github.devmeeple.todo.entity.Todo;

public class EntityDtoMapper {

    public static Todo toEntity(TodoRequestDto dto) {
        return new Todo(
                null, // 자동 생성 필드
                dto.getTitle(),
                dto.getDescription(),
                dto.isCompleted(),
                null // 자동 생성 필드
        );
    }

    public static TodoResponseDto toDto(Todo entity) {
        return new TodoResponseDto(
                entity.getId(), entity.getTitle(), entity.getDescription(), entity.isCompleted()
        );
    }
}
