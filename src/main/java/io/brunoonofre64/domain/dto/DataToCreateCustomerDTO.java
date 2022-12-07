package io.brunoonofre64.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DataToCreateCustomerDTO {
    private String name;
    private String age;
}
