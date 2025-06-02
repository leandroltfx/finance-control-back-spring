package br.com.ltfx.finance_control_back_spring.adapter.in.api;

import br.com.ltfx.finance_control_back_spring.adapter.in.api.dto.HttpResponseDto;
import br.com.ltfx.finance_control_back_spring.adapter.in.api.dto.UserRequestDto;
import br.com.ltfx.finance_control_back_spring.adapter.in.api.mapper.UserMapper;
import br.com.ltfx.finance_control_back_spring.application.port.in.CreateUserUseCase;

import br.com.ltfx.finance_control_back_spring.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<HttpResponseDto> createUser(@RequestBody @Valid UserRequestDto request) {
        User user = UserMapper.toDomain(request);
        User createdUser = createUserUseCase.createUser(user);
        HttpResponseDto response = UserMapper.toResponse("Usuário cadastrado com sucesso!", createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
