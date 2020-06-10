package ru.examples.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityPermission implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
