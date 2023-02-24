package br.com.onofrestore.domain.dto.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchDTO {
    private int page;
    private int size;
    private String search;
}
