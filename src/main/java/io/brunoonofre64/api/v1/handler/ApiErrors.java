package io.brunoonofre64.api.v1.handler;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrors {
    private String title;
    private LocalDateTime timestamp;
    private int codeStatus;
    private String details;
}
