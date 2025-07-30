package kr.hhplus.be.server.support;

import java.lang.reflect.Field;

public class TestReflectionUtils {
    // 테스트 전용 유틸리티 클래스

    public static void setId(Object entity, Long id) {
        try {
            Field field = entity.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(entity, id);
        } catch (Exception e) {
            throw new RuntimeException("ID 설정 실패: " + entity.getClass().getSimpleName(), e);
        }
    }
}