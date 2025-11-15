package org.tiradaus.infrastructure.web.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record EventPageResponse<T> (
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public static <T> EventPageResponse<T> from(Page<T> page) {
        return new EventPageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
