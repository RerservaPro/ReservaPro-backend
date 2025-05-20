package br.com.reservapro.domain;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private long totalElement;
    private int pageSize;
    private int totalPage;
    private int currentPage;
    private int nextPage;
    private int previousPage;
    private Set<T> content;
}
