package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

    private List<T> listEntity = new ArrayList<>();

    private int currentPage;
    private int limit;
    private int offset;
    private String search = "";
    private String order = "";
    private String column = "";

    public Pager() {

    }

    public Pager(List<T> listEntity) {
        this.listEntity = listEntity;
    }

    public Pager(List<T> listEntity, int currentPage, int limit, String search, String order, String column) {
        super();
        this.listEntity = listEntity;
        this.currentPage = currentPage;
        this.limit = limit;
        this.search = search;
        this.order = order;
        this.column = column;
    }

    public List<T> getListEntity() {
        return listEntity;
    }

    public void setListEntity(List<T> listEntity) {
        this.listEntity = listEntity;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Pager [listEntity=" + listEntity + ", currentPage=" + currentPage + ", limit=" + limit + ", offset="
                + offset + ", search=" + search + ", order=" + order + ", column=" + column + "]";
    }

}
