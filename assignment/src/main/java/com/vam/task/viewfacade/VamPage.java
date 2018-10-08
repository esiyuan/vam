package com.vam.task.viewfacade;

import com.github.pagehelper.Page;
import com.google.common.primitives.Ints;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class VamPage {

    public static VamPage START;

    static {
        START = new VamPage();
        START.start = 1;
    }

    /**
     * 开始页面
     */
    private Integer start;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 记录总数
     */
    private Long totalCount;
    /**
     * 总页数
     */
    private Integer pages;


    public static <T> VamPage from(Page<T> page) {
        VamPage vamPage = new VamPage();
        vamPage.setStart(page.getPageNum());
        vamPage.setTotalCount(page.getTotal());
        vamPage.setPageSize(page.getPageSize());
        vamPage.setPages(page.getPages());
        return vamPage;
    }
}
