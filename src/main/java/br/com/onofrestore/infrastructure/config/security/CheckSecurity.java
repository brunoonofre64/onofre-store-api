package br.com.onofrestore.infrastructure.config.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    String ADMIN_MANAGER = "hasAnyAuthority('ADMIN', 'MANAGER')";
    String ADMIN_MANAGER_USER = "hasAnyAuthority('ADMIN', 'MANAGER', 'USER')";
    String AUTHENTICATED = "isAuthenticated()";
    String PERMIT_ALL = "permitAll()";

    @interface Permit {

        @PreAuthorize(ADMIN_MANAGER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanSave {
        }

        @PreAuthorize(ADMIN_MANAGER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanUpdate {
        }

        @PreAuthorize(ADMIN_MANAGER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanDelete {
        }

        @PreAuthorize(ADMIN_MANAGER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanDeleteAccount {
        }

        @PreAuthorize(ADMIN_MANAGER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanSearchUser {
        }

        @PreAuthorize(AUTHENTICATED)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanAuthenticated {
        }

        @PreAuthorize(PERMIT_ALL)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanAll {
        }

        @PreAuthorize(ADMIN_MANAGER_USER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanReadOrder {
        }

        @PreAuthorize(ADMIN_MANAGER_USER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanSaveOrder {
        }

        @PreAuthorize(ADMIN_MANAGER_USER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanChangePassword {
        }

        @PreAuthorize(ADMIN_MANAGER_USER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanDeleteOrder {
        }

        @PreAuthorize(ADMIN_MANAGER_USER)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanUpdateOrder {
        }
    }
}
