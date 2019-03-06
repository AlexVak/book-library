package com.alexvak.booklibrary.bootstrap;

import com.alexvak.booklibrary.config.SecurityUtility;
import com.alexvak.booklibrary.domain.User;
import com.alexvak.booklibrary.domain.security.Role;
import com.alexvak.booklibrary.domain.security.UserRole;
import com.alexvak.booklibrary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@Profile({"default", "dev"})
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final SecurityUtility securityUtility;

    public RecipeBootstrap(UserService userService, SecurityUtility securityUtility) {
        this.userService = userService;
        this.securityUtility = securityUtility;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Set<UserRole> userRoles = new HashSet<>();

        if (!userService.findByUsername("user1").isPresent()) {
            User user1 = new User();
            user1.setFirstName("User1 FirstName");
            user1.setLastName("User1 LastName");
            user1.setUsername("user1");
            user1.setPassword(securityUtility.passwordEncoder().encode("password1"));
            user1.setEmail("user1@gmail.com");
            Role role1 = new Role();
            role1.setName("ROLE_USER");
            userRoles.add(new UserRole(user1, role1));
            userService.createUser(user1, userRoles);
        }

        userRoles.clear();

        if (!userService.findByUsername("user2").isPresent()) {
            User user2 = new User();
            user2.setFirstName("User2 FirstName");
            user2.setLastName("User2 LastName");
            user2.setUsername("user2");
            user2.setPassword(securityUtility.passwordEncoder().encode("password2"));
            user2.setEmail("user2@gmail.com");
            Role role2 = new Role();
            role2.setName("ROLE_ADMIN");
            userRoles.add(new UserRole(user2, role2));
            userService.createUser(user2, userRoles);
        }
        userRoles.clear();
    }
}
