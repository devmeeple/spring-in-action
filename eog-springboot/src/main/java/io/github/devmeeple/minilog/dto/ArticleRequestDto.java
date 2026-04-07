package io.github.devmeeple.minilog.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleRequestDto {

    @NonNull
    private Long authorId;

    @NonNull
    private String content;
}
