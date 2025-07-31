package kr.hhplus.be.server.user.usecase.command.impl;

import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import kr.hhplus.be.server.user.usecase.command.CreateUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository repository;

    public CreateUserUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(String name) {
        return repository.save(new User(name, 0));
    }
}
