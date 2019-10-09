package com.kyrie.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kyrie
 * @date 2019/9/23 - 21:04
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;   // 显示上一页
    private boolean showFirstPage;  // 显示首页
    private boolean showNext;       // 显示下一页
    private boolean showEndPage;    // 显示尾页
    private Integer currentPage;    // 当前页
    private Integer previousPage;   // 上一页
    private Integer nextPage;       // 下一页
    private Integer totalPage;      // 尾页
    private List<Integer> pages = new ArrayList<>();    // 当前需要展示的所有页码

    /**
     * 设置分页相关的信息
     * @param totalCount
     * @param page
     * @param size
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        // 总的页数
        totalPage = (totalCount % size) == 0 ? (totalCount / size) : (totalCount / size + 1);

        // 如果请求页面小于或等于1，则设置当前页为1
        if (page < 1) {
            currentPage = 1;
        }
        // 如果请求页面大于或等于总页数，则设置当前页为最后一页
        if (page > totalPage) {
            currentPage = totalPage;
        }

        currentPage = page;
        if (currentPage - 1 > 0) {
            previousPage = currentPage - 1;
        }
        if (currentPage + 1 <= totalPage) {
            nextPage = currentPage + 1;
        }

        pages.add(currentPage);
        // 显示当前页的左边【0-3】页，显示当前页的右边【0-3】页
        for (int i = 1; i <= 3; i++) {
            if(currentPage - i > 0) {
                pages.add(0, currentPage - i);
            }
            if (currentPage + i <= totalPage) {
                pages.add(currentPage + i);
            }
        }

        // 是否显示上一页
        if (page != 1) {
            showPrevious = true;
        } else {
            showPrevious = false;
        }
        // 是否显示下一页
        if (page != totalPage) {
            showNext = true;
        } else {
            showNext = false;
        }

        // 是否显示首页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否显示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
