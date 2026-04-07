package io.github.devmeeple.minilog.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {

    @NonNull
    private String username;

    @NonNull
    private String password;
}
