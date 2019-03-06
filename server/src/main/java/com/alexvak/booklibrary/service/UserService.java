package com.alexvak.booklibrary.service;

import com.alexvak.booklibrary.domain.User;
import com.alexvak.booklibrary.domain.security.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {

    User createUser(User user, Set<UserRole> userRoles);

    Optional<User> findByUsername(String userName);

}
