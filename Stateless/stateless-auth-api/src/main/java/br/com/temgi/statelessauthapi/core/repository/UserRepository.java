package br.com.temgi.statelessauthapi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.temgi.statelessauthapi.core.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
