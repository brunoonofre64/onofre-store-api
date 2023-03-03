package br.com.onofrestore.infrastructure.config.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    String ADMIN_MANAGER = "hasAnyAuthority('ADMIN', 'MANAGER')";
    String SCOPE_WRITE = "hasAuthority('SCOPE_WRITE')";
    String SCOPE_READ = "hasAuthority('SCOPE_READ')";
    String SCOPE_READ_WRITE = "hasAnyAuthority('SCOPE_READ', 'MANAGER')";
    String ADMIN_MANAGER_USER = "hasAnyAuthority('ADMIN', 'MANAGER', 'USER')";
    String AUTHENTICATED = "isAuthenticated()";
    String PERMIT_ALL = "permitAll()";
    String AND = " and ";

    @interface Permit {

        @PreAuthorize(ADMIN_MANAGER + AND + SCOPE_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanSave {
        }

        @PreAuthorize(ADMIN_MANAGER + AND + SCOPE_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanUpdate {
        }

        @PreAuthorize(ADMIN_MANAGER + AND + SCOPE_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanDelete {
        }

        @PreAuthorize(ADMIN_MANAGER + AND + SCOPE_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanDeleteAccount {
        }

        @PreAuthorize(ADMIN_MANAGER + AND + SCOPE_READ)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanSearchUser {
        }

        @PreAuthorize(AUTHENTICATED + AND + SCOPE_READ)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanAuthenticated {
        }

        @PreAuthorize(PERMIT_ALL + AND + SCOPE_READ_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanAll {
        }

        @PreAuthorize(ADMIN_MANAGER_USER + AND + SCOPE_READ_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanReadOrder {
        }

        @PreAuthorize(ADMIN_MANAGER_USER + AND + SCOPE_READ_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanSaveOrder {
        }

        @PreAuthorize(ADMIN_MANAGER_USER + AND + SCOPE_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanChangePassword {
        }

        @PreAuthorize(ADMIN_MANAGER_USER + AND + SCOPE_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanDeleteOrder {
        }

        @PreAuthorize(ADMIN_MANAGER_USER + AND + SCOPE_WRITE)
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanUpdateOrder {
        }
    }
}
