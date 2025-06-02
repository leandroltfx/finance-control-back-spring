package br.com.ltfx.finance_control_back_spring.infrastructure.repository;

import br.com.ltfx.finance_control_back_spring.infrastructure.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

}
