package com.excilys.computerDatabase.model;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

    private List<T> listEntity = new ArrayList<>();

    /**
     * @param builder the builder
     */
    private Pager(BuilderPage<T> builder) {
        this.listEntity = builder.list;
    }

    /**
     * @return the objects on the list
     */
    public List<T> getListEntity() {
        return listEntity;
    }

    public static class BuilderPage<T> {
        private List<T> list;

        /**
         * @param list the list
         */
        public BuilderPage(List<T> list) {
            this.list = list;

        }

        /**
         * @return the new pager
         */
        public Pager<T> build() {
            return new Pager<T>(this);
        }
    }
}
