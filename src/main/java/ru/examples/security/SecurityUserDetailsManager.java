package ru.examples.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SecurityUserDetailsManager implements UserDetailsManager {
    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //тут должна быть реализована логика по вычитке пользователя из хранилища данных
        if (!userName.equals("user")){
            return null;
        }
        List<SecurityPermission> permissions = new ArrayList<>();

        permissions.add(new SecurityPermission("company.read"));
        permissions.add(new SecurityPermission("company.create"));
        permissions.add(new SecurityPermission("company.update"));
        permissions.add(new SecurityPermission("company.delete"));

        permissions.add(new SecurityPermission("employee.read"));
        permissions.add(new SecurityPermission("employee.create"));
        permissions.add(new SecurityPermission("employee.update"));
        permissions.add(new SecurityPermission("employee.delete"));

        return new SecurityUser("user", "{noop}pwd", permissions);
        //  return new SecurityUser("user", "pwd", permissions);
    }
}
