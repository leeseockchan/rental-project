package com.road_friends.rentalcar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {
    private int page;
    private int size; // 페이지당 항목 수
    private int totalPages;
    private int totalElements;
    private int pageGroupSize = 5;
    private int start; // 시작 페이지 번호
    private int end; // 끝 페이지 번호
    private boolean prev;
    private boolean next;
    private List<T> items;

    public PageDto(int page, int size, int totalElements, List<T> items) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;

        // 끝 페이지 계산
        this.totalPages = (int) Math.ceil((double) totalElements / size);

        // 시작 페이지 계산
        this.start = ((page - 1) / pageGroupSize) * pageGroupSize + 1;

        // 끝 페이지 계산
        this.end = Math.min(start + pageGroupSize - 1, totalPages);

        // 이전/다음 버튼 표시 여부
        this.prev = start > 1;
        this.next = end < totalPages;

        this.items = items;
    }
}
