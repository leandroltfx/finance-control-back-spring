package br.com.ltfx.finance_control_back_spring.application.port.in;

import br.com.ltfx.finance_control_back_spring.domain.model.User;

public interface CreateUserUseCase {

    User createUser(User user);

}
