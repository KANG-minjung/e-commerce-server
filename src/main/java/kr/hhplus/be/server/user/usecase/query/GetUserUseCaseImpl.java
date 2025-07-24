package kr.hhplus.be.server.user.usecase.query;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRepository repository;

    public GetUserUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_INVALID));
    }
}
