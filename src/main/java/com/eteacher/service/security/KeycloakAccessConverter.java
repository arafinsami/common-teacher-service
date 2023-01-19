package com.eteacher.service.security;

import com.eteacher.service.entity.*;
import com.eteacher.service.entity.Module;
import com.eteacher.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class KeycloakAccessConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final UserRepository userRepository;

    public Collection<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPermissions(roles));
    }

    private List<String> getPermissions(Collection<Role> roles) {
        List<String> authorities = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();
        roles.forEach(role -> {
            permissions.addAll(role.getPermissions());
        });
        permissions.forEach(item -> {
            authorities.add(item.getAuthority());
        });
        return authorities;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        privileges.forEach(privilege -> {
            authorities.add(new SimpleGrantedAuthority(privilege));
        });
        return authorities;
    }

    @Override
    @Cacheable(key = "#jwt.toString()", value = "user")
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        User user = userRepository.findByUserName((String) jwt.getClaims().get("preferred_username"));
        if (user == null) {
            return new ArrayList<>();
        }
        Collection<Group> groups = getGroups(user);
        return getAuthorities(getRoles(groups));
    }

    private Collection<Group> getGroups(User user) {
        Set<Module> modules = user.getModules();
        Collection<Group> groups = new ArrayList<>();
        modules.forEach(module -> {
            groups.addAll(module.getGroups());
        });
        return groups;
    }

    private Collection<Role> getRoles(Collection<Group> groups) {
        Collection<Role> roles = new ArrayList<>();
        groups.forEach(group -> {
            roles.addAll(group.getRoles());
        });
        return roles;
    }
}