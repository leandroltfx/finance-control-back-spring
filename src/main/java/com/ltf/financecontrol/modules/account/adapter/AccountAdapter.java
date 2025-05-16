package com.ltf.financecontrol.modules.account.adapter;

import com.ltf.financecontrol.modules.account.model.dto.AccountDto;
import com.ltf.financecontrol.modules.account.model.entities.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountAdapter {

    public AccountDto parseToDto(AccountEntity accountEntity) {
        return AccountDto
                .builder()
                .name(accountEntity.getName())
                .description(accountEntity.getDescription())
                .amount(accountEntity.getAmount())
                .build();
    }

    public AccountEntity parseToEntity(AccountDto accountDto, UUID userid) {
        return AccountEntity
                .builder()
                .name(accountDto.getName())
                .description(accountDto.getDescription())
                .amount(accountDto.getAmount())
                .userId(userid)
                .build();
    }

}
