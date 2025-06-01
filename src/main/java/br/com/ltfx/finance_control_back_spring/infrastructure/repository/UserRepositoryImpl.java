package br.com.ltfx.finance_control_back_spring.infrastructure.repository;

import br.com.ltfx.finance_control_back_spring.domain.model.User;
import br.com.ltfx.finance_control_back_spring.application.port.out.UserRepository;
import br.com.ltfx.finance_control_back_spring.infrastructure.repository.entity.UserEntity;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SpringDataUserRepository springDataUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity saved = springDataUserRepository.save(entity);
        return toDomain(saved);
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
