package com.anhtuan.springmvc.CRMSpringMVC.service;

import com.anhtuan.springmvc.CRMSpringMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service("customUserDetailService")
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByLogin(username);
        if (user == null)
            throw new UsernameNotFoundException("Username not found");
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true,
                true, true,true, getGrantedAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
        return user.getRoleList().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
    }
}
