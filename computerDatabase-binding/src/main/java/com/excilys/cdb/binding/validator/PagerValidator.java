package com.excilys.cdb.binding.validator;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Pager;

@Component
public class PagerValidator {

    private static int DEFAULT_CURRENT_PAGE = 1;
    private static int DEFAULT_LIMIT = 10;
    private static final String DEFAULT_COLUMN_ORDER = "name";

    public <T> void valid(Pager<T> pager) {

        if (pager.getCurrentPage() < 1) {
            pager.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }

        if (pager.getLimit() <= 0) {
            pager.setLimit(DEFAULT_LIMIT);
        }

        String order = pager.getOrder();
        if (!order.equals("ASC") && !order.equals("DESC")) {
            pager.setOrder("ASC");
        }

        pager.setColumn(DEFAULT_COLUMN_ORDER);

        pager.setOffset((pager.getCurrentPage() - 1) * pager.getLimit());

    }

}
