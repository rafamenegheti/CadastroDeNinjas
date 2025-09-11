package com.estudo.CadastroDeNinjas.Ninjas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.CadastroDeNinjas.Ninjas.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
}


