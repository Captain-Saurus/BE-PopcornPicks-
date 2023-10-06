package com.example.popcornpicks.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Page {
    private int page;
    private int size;
    private int totalSize;

    public Page(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
