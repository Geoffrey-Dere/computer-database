package com.excilys.cdb.webapp.controller;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaginationTag extends SimpleTagSupport {

    private static final int NUMBER_PAGE = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaginationTag.class);

    private int maxPages;

    private String uri;
    private int currentPage;

    private String uriLimit;
    private int limit;

    private String uriSearch;
    private String search;

    /**
     */
    public PaginationTag() {
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();

        if (currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > maxPages) {
            currentPage = maxPages;
        }

        int pageLeft = Math.min((int) Math.ceil(NUMBER_PAGE / 2), currentPage - 1);
        int pageRight = NUMBER_PAGE - pageLeft - 1;

        if (currentPage + pageRight > maxPages) {
            pageRight = maxPages - currentPage;
            pageLeft = NUMBER_PAGE - pageRight - 1;
        }
        printPagination(out, pageLeft, pageRight);
    }

    /**
     * @param out out
     * @param pageLeft pageLeft
     * @param pageRight pageRight
     */
    private void printPagination(JspWriter out, int pageLeft, int pageRight) {
        try {
            out.println("<ul class=\"pagination\">");

            if (this.currentPage > 1) {
                out.println("<li>");
                out.println("<a  aria-label=\"Previous\" href= " + link(currentPage - 1, limit) + "> &laquo; </a>");
                out.println("</li>");
            }

            // display the previous pages
            for (int i = currentPage - pageLeft; i >= 1 && pageLeft > 0; i++) {
                out.println("<li>");
                out.println("<a href= " + link(i, limit) + ">" + i + "</a>");
                out.println("</li>");
                pageLeft--;
            }

            // display the current page
            out.println("<li>");
            out.println("<a href= " + link(currentPage, limit) + ">" + currentPage + "</a>");

            // display the next pages
            for (int i = currentPage + 1; i <= maxPages && pageRight > 0; i++) {
                out.println("<li>");
                out.println("<a href= " + link(i, limit) + ">" + i + "</a>");
                out.println("</li>");
                pageRight--;
            }

            if (this.currentPage < maxPages) {
                out.println("<li>");
                out.println("<a  aria-label=\"Previous\" href= " + link(currentPage + 1, limit) + "> &raquo; </a>");
                out.println("</li>");
            }

            out.println("</ul>");

            printLimit(out);

        } catch (Exception e) {
            LOGGER.debug("error pagination");

        }
    }

    /**
     * @param out out
     * @throws IOException ioException
     */
    private void printLimit(JspWriter out) throws IOException {
        out.println("<div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");
        out.println("<a type=\"button\" class=\"btn btn-default\" href=" + link(1, 10) + " >10</a>");
        out.println("<a type=\"button\" class=\"btn btn-default\" href=" + link(1, 50) + " >50</a>");
        out.println("<a type=\"button\" class=\"btn btn-default\" href=" + link(1, 100) + " >100</a>");
        out.println("</div>");

    }

    /**
     * @param page page
     * @param limit limit
     * @return string
     */
    private String link(int page, int limit) {
        String url = String.format("?%s=%d&%s=%s", uri, page, uriLimit, limit);

        if (uriSearch != null && search != null && !search.isEmpty()) {
            url += String.format("&%s=%s", uriSearch, search);
        }

        return url;
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

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUriLimit() {
        return uriLimit;
    }

    public void setUriLimit(String uriLimit) {
        this.uriLimit = uriLimit;
    }

    public String getUriSearch() {
        return uriSearch;
    }

    public void setUriSearch(String uriSearch) {
        this.uriSearch = uriSearch;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
