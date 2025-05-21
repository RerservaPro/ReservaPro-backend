package br.com.reservapro.domain.pagination;

import java.util.Set;

public record PageResponseDTO<T>(
        long totalElement,
        int pageSize,
        int totalPage,
        int currentPage,
        int nextPage,
        int previousPage,
        Set<T> content
) {
}
