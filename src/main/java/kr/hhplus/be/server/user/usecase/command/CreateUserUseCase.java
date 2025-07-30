package kr.hhplus.be.server.user.usecase.command;

import kr.hhplus.be.server.user.domain.model.User;

public interface CreateUserUseCase {
    User create(String name);
}
