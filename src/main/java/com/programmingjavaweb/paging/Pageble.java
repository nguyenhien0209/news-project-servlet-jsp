package com.programmingjavaweb.paging;

import com.programmingjavaweb.sort.Sorter;

public interface Pageble {
    Integer getPage(); //lay page hien tai cua trang
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
