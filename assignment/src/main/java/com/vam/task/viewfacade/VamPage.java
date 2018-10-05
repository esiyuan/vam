package com.vam.task.viewfacade;

import com.github.pagehelper.Page;
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
        vamPage.setIndexStart(page.getStartRow() + 1);
//        vamPage.setIndexEnd(page.getEndRow());
        vamPage.setIndexEnd((page.getPageNum() - 1) * page.getPageSize() + page.size());
        return vamPage;
    }
}
