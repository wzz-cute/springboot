package com.wzz.redis.util;
import java.util.List;
/**
 *
 * @packageName com.wzz.redis.user
 * @fileName Page.java
 * @author wzzws (wzz6b.com@gmail.com)
 * @createTime 2022/02/20 20:08:07
 * @lastModify 2022/02/20 20:08:07
 * @version 1.0.0
 */
public class Page<T> {


    private Integer pageNo = 1;
    private Integer pageSize = 5;
    private Integer totalCount;
    private Integer totalPageCount = 1;

    private List<T> data;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo <= 0){
            pageNo = 1;
        }
        if(totalPageCount >= 1 && pageNo > totalPageCount){
            pageNo = totalPageCount;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        this.totalPageCount = this.totalCount % this.pageSize == 0 ?
                this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int offset(){
        return (pageNo - 1) * pageSize;
    }
}
