package kr.hhplus.be.server.user.usecase.query;

import kr.hhplus.be.server.user.domain.model.User;

public interface GetUserUseCase {
    User getById(Long id);
}
