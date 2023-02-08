package br.com.onofrestore.domain.enums;

import lombok.Getter;

@Getter
public enum Profiles {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER");

    private final String roles;

    Profiles(String roles) {
        this.roles = roles;
    }
}