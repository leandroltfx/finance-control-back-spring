package com.ltf.financecontrol.modules.account.repository;

import com.ltf.financecontrol.modules.account.model.entities.AccountEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByName(String name);

    Page<AccountEntity> findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndAmountGreaterThanEqual(
            Pageable pageable,
            String name,
            String description,
            BigDecimal amount
    );
}
