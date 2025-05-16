package com.ltf.financecontrol.modules.account.service;

import com.ltf.financecontrol.exceptions.UserFoundException;
import com.ltf.financecontrol.modules.account.adapter.AccountAdapter;
import com.ltf.financecontrol.modules.account.model.dto.AccountDto;
import com.ltf.financecontrol.modules.account.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

}
