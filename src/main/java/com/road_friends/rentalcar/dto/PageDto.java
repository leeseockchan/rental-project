package com.road_friends.rentalcar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {
    private int page;               // 현재 페이지 번호
    private int size;               // 페이지당 항목 수
    private int totalPages;         // 총 페이지 수
    private int totalElements;      // 총 항목 개수
    private int pageGroupSize = 10; // 한 번에 보여줄 페이지 번호 개수
    private int start;              // 시작 페이지 번호
    private int end;                // 끝 페이지 번호
    private boolean prev;           // 이전 페이지 그룹 존재 여부
    private boolean next;           // 이후 페이지 그룹 존재 여부
    private List<T> content;        // 현재 페이지의 실제 데이터 목록

    /* size : 한 페이지에서 보여줄 항목(데이터 관련)
	     pageGroupSize : 한 번에 표시할 페이지 번호 개수를 결정(UI 관련) */

    public PageDto(int page, int size, int totalElements, List<T> content) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;

        // 총 페이지 수 계산
        this.totalPages = (int) Math.ceil((double) totalElements / size);

        // 시작 페이지 계산 (현재 페이지가 5일 경우 1, 15일 경우 11)
        // 페이지 그룹의 첫 번째 페이지 번호 계산
        this.start = ((page - 1) / pageGroupSize) * pageGroupSize + 1;

        // 끝 페이지 계산
        // 현재 페이지 그룹의 마지막 페이지 계산
        this.end = Math.min(start + pageGroupSize - 1, totalPages);

        // 이전/다음 버튼 표시 여부
        this.prev = start > 1;
        this.next = end < totalPages;

        this.content = content;
    }
}