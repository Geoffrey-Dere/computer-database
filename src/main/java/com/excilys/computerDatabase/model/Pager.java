package com.excilys.computerDatabase.model;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

    private List<T> listEntity = new ArrayList<>();

    private Pager(BuilderPage<T> builder) {
        this.listEntity = builder.list;
    }

    public List<T> getListEntity() {
        return listEntity;
    }

    public static class BuilderPage<T> {
        private List<T> list;

        public BuilderPage(List<T> list) {
            this.list = list;

        }

        public Pager<T> build() {
            return new Pager<T>(this);
        }
    }
}
