package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

    private List<T> listEntity = new ArrayList<>();

    /**
     * @param builder builder
     */
    private Pager(BuilderPage<T> builder) {
        this.listEntity = builder.list;
    }

    public List<T> getListEntity() {
        return listEntity;
    }

    /**
     * @param <T> <t>
     */
    public static class BuilderPage<T> {
        private List<T> list;

        /**
         * @param list list
         */
        public BuilderPage(List<T> list) {
            this.list = list;

        }

        /**
         * @return the pager
         */
        public Pager<T> build() {
            return new Pager<T>(this);
        }
    }
}
