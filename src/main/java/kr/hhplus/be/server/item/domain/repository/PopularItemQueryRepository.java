package kr.hhplus.be.server.item.domain.repository;

import kr.hhplus.be.server.item.usecase.dto.PopularItemResponse;

import java.util.List;

public interface PopularItemQueryRepository {
    List<PopularItemResponse> findTop5PopularItemsWithinDays(int recentDays);
}
