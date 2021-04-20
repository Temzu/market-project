package com.temzu.market_project.mscore.configs;

import com.temzu.market_project.mscore.model.User;
import com.temzu.market_project.mscore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}
