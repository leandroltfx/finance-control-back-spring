package com.ltf.financecontrol.modules.account.service;

import com.ltf.financecontrol.exceptions.UserFoundException;
import com.ltf.financecontrol.modules.account.adapter.AccountAdapter;
import com.ltf.financecontrol.modules.account.model.dto.AccountDto;
import com.ltf.financecontrol.modules.account.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountAdapter accountAdapter;

    public AccountDto createAccount(
            AccountDto accountDto,
            UUID userId
    ) {

        this.accountRepository
                .findByName(accountDto.getName())
                .ifPresent(accountEntityFound -> {
                    throw new UserFoundException("Você já tem uma conta chamada " + accountEntityFound.getName());
                });

        return this.accountAdapter.parseToDto(
                this.accountRepository.save(
                        this.accountAdapter.parseToEntity(accountDto, userId)
                )
        );
    }

    public List<AccountDto> getAccounts(
            Pageable pageable
    ) {
        return this.accountRepository.findAll(pageable)
                .stream()
                .map(accountEntity -> this.accountAdapter.parseToDto(accountEntity))
                .collect(Collectors.toList());
    }

    public List<AccountDto> filterAccounts(
            Pageable pageable,
            String name,
            String description,
            BigDecimal amount
    ) {
        return this.accountRepository.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndAmountGreaterThanEqual(
                    pageable,
                    name,
                    description,
                    amount
                )
                .stream()
                .map(accountEntity -> this.accountAdapter.parseToDto(accountEntity))
                .collect(Collectors.toList());
    }

}
