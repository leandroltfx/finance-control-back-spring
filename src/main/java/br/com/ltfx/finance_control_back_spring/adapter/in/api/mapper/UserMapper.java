package br.com.ltfx.finance_control_back_spring.adapter.in.api.mapper;

import br.com.ltfx.finance_control_back_spring.adapter.in.api.dto.UserRequestDto;
import br.com.ltfx.finance_control_back_spring.adapter.in.api.dto.UserResponseDto;
import br.com.ltfx.finance_control_back_spring.domain.model.User;

public class UserMapper {

    public static User toDomain(UserRequestDto request) {
        return new User(
                null, // ID será gerado na persistência
                request.username(),
                request.email(),
                request.password()
        );
    }

    public static UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

}
