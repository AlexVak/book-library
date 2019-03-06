package com.alexvak.booklibrary.service;

import com.alexvak.booklibrary.domain.User;
import com.alexvak.booklibrary.domain.security.UserRole;
import com.alexvak.booklibrary.exception.DuplicateUserFoundException;
import com.alexvak.booklibrary.repository.RoleRepository;
import com.alexvak.booklibrary.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(userName);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("User [%s] not found", userName));
        }
        return userOptional.get();
    }

    @Override
    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            throw new DuplicateUserFoundException(user.getUsername());
        }
        userRoles.forEach(ur -> roleRepository.save(ur.getRole()));
        user.getUserRole().addAll(userRoles);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
