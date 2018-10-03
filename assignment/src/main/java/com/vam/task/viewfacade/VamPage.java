package com.vam.task.viewfacade;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class VamPage {
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
     * 记录开始索引
     */
    private Integer indexStart;
    /**
     * 记录结束索引
     */
    private Integer indexEnd;


    public static <T> VamPage from(Page<T> page) {
        VamPage vamPage = new VamPage();
        vamPage.setStart(page.getPageNum());
        vamPage.setTotalCount(page.getTotal());
        vamPage.setPageSize(page.getPageSize());
        vamPage.setIndexStart(page.getStartRow());
        vamPage.setIndexEnd(page.getEndRow());
        return vamPage;
    }
}
