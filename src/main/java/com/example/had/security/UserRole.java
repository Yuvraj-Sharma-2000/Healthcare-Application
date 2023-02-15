package com.example.had.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.had.security.UserPermission.*;

public enum UserRole {
    DOCTOR(Sets.newHashSet(ARTICLE_READ,
            CHAT_READ,
            QUESTION_READ,
            REPORT_READ,
            REPORT_WRITE)),
    USER(Sets.newHashSet(ARTICLE_READ,
            QUESTION_READ,
            REPORT_READ,
            CHAT_READ,
            DOCTOR_READ)),
    ADMIN(Sets.newHashSet(ARTICLE_READ,
            ARTICLE_WRITE,
            CHAT_READ,
            DOCTOR_READ,
            DOCTOR_WRITE,
            QUESTION_READ,
            QUESTION_WRITE,
            REPORT_READ,
            USER_READ,
            USER_WRITE));

    private final Set<UserPermission> permissions;
    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
    public Set<UserPermission> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
