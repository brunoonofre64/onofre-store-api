package io.brunoonofre64.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataToCreateCustomerDTO {

    private String name;

    private String age;
}
