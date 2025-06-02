package br.com.ltfx.finance_control_back_spring.infrastructure.repository;

import br.com.ltfx.finance_control_back_spring.domain.model.User;
import br.com.ltfx.finance_control_back_spring.application.port.out.UserRepository;
import br.com.ltfx.finance_control_back_spring.adapter.in.api.exception.UserFoundException;
import br.com.ltfx.finance_control_back_spring.infrastructure.repository.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public UserRepositoryImpl(
            SpringDataUserRepository springDataUserRepository
    ) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {

        springDataUserRepository
                .findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .ifPresent(userEntity -> {
                    throw new UserFoundException();
                });

        return toDomain(springDataUserRepository.save(toEntity(user)));
    }

    // Conversores
    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }

    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}
