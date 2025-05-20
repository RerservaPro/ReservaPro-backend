package br.com.reservapro.controller.in;

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
