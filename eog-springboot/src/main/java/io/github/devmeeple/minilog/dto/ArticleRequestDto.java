package io.github.devmeeple.minilog.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ArticleRequestDto {

    @NonNull
    private Long authorId;

    @NonNull
    private String content;
}
