package com.picpaysimplificado.picpaysimplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.picpaysimplificado.domain.user.User;

public interface UserRepository extends JpaRepository <User, Long>{
 
    /* Definindo um método que vai buscar Usuarios pelo documento */
    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long id);

    


}
