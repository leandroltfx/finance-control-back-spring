package com.ltf.financecontrol.modules.account.repository;

import com.ltf.financecontrol.modules.account.model.entities.AccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByName(String name);
}
