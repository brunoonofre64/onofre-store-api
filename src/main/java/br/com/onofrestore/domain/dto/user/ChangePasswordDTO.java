package br.com.onofrestore.domain.dto.user;

import lombok.Getter;

@Getter
public class ChangePasswordDTO {
    private String currentPassword;
    private String newPassword;
}
