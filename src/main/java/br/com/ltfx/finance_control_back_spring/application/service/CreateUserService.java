package br.com.ltfx.finance_control_back_spring.application.service;

import br.com.ltfx.finance_control_back_spring.domain.model.User;
import br.com.ltfx.finance_control_back_spring.application.port.out.UserRepository;
import br.com.ltfx.finance_control_back_spring.application.port.in.CreateUserUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
