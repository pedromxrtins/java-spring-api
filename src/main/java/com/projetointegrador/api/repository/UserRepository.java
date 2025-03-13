package com.projetointegrador.api.repository;

import com.projetointegrador.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
