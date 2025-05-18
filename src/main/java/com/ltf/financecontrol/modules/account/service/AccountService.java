package com.ltf.financecontrol.modules.account.service;

import com.ltf.financecontrol.exceptions.UserFoundException;
import com.ltf.financecontrol.exceptions.UserNotFoundException;
import com.ltf.financecontrol.modules.account.adapter.AccountAdapter;
import com.ltf.financecontrol.modules.account.model.dto.AccountDto;
import com.ltf.financecontrol.modules.account.model.dto.AccountPatchDto;
import com.ltf.financecontrol.modules.account.model.entities.AccountEntity;
import com.ltf.financecontrol.modules.account.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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

    @Cacheable(value = "accountsCache", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
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

    public AccountDto updateAccount(
            AccountDto accountDto,
            UUID userId
    ) {
        if (accountDto.getId() == null) {
            throw new UserNotFoundException("Não foi possível identificar a conta bancária");
        }

        AccountEntity accountEntityExists = this.accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new UserNotFoundException("Conta inexistente"));

        if (!accountEntityExists.getUserId().equals(userId)) {
            throw new UserNotFoundException("Você está tentando alterar a conta bancária de outro usuário");
        }

        return this.accountAdapter.parseToDto(
                this.accountRepository.save(
                        this.accountAdapter.parseToEntity(accountDto, userId)
                )
        );
    }

    public AccountDto updatePartialAccount(
            AccountPatchDto accountPatchDto,
            UUID userId
    ) {
        if (accountPatchDto.getId() == null) {
            throw new UserNotFoundException("Não foi possível identificar a conta bancária");
        }

        AccountEntity accountEntity = this.accountRepository.findById(accountPatchDto.getId())
                .orElseThrow(() -> new UserNotFoundException("Conta inexistente"));

        if (!accountEntity.getUserId().equals(userId)) {
            throw new UserNotFoundException("Você está tentando alterar a conta bancária de outro usuário");
        }

        if (accountPatchDto.getName() != null) {
            accountEntity.setName(accountPatchDto.getName());
        }
        if (accountPatchDto.getDescription() != null) {
            accountEntity.setDescription(accountPatchDto.getDescription());
        }
        if (accountPatchDto.getAmount() != null) {
            accountEntity.setAmount(accountPatchDto.getAmount());
        }

        AccountEntity updatedAccount = this.accountRepository.save(accountEntity);
        return this.accountAdapter.parseToDto(updatedAccount);
    }

}
