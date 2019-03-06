package com.alexvak.booklibrary.repository;

import com.alexvak.booklibrary.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
