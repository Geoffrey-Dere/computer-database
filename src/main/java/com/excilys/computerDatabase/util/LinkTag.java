package com.excilys.computerDatabase.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.persistence.ConnectionManager;

public class LinkTag extends SimpleTagSupport {

    private static final int NUMBER_PAGE = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkTag.class);

    private int currentPage;
    private int limit;
    private int maxPages;
    private String uri;
    private String uriLimit;

    public LinkTag() {
        // TODO Auto-generated constructor stub
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

        try {
            out.println("<ul class=\"pagination\">");

            if (this.currentPage > 1) {
                out.println(link(currentPage - 1, "&laquo;", " aria-label=\"Previous\" ", limit));
            }

            // display the previous pages
            for (int i = currentPage - pageLeft; i >= 1 && pageLeft > 0; i++) {
                out.println(link(i, String.valueOf(i), "", limit));
                pageLeft--;
            }

            // display the current page
            out.println(link(currentPage, String.valueOf(currentPage), "", limit));

            // display the next pages
            for (int i = currentPage + 1; i <= maxPages && pageRight > 0; i++) {
                out.println(link(i, String.valueOf(i), "", limit));
                pageRight--;
            }

            if (this.currentPage < maxPages) {
                out.println(link(currentPage + 1, "&raquo;", " aria-label=\"Next\" ", limit));
            }

            out.println("</ul>");

            displayLink(out);

        } catch (Exception e) {
            LOGGER.debug("error pagination");

        }
    }

    private void displayLink(JspWriter out) throws IOException {

        out.println("<div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");
        out.println("<a type=\"button\" class=\"btn btn-default\" href=" + href(currentPage, 10) + " >10</a>");
        out.println("<a type=\"button\" class=\"btn btn-default\" href=" + href(currentPage, 50) + " >50</a>");
        out.println("<a type=\"button\" class=\"btn btn-default\" href=" + href(currentPage, 100) + " >100</a>");
        out.println("</div>");

    }

    private String link(int page, String value, String decorator, int limit) {

        String url = String.format("<li>" + "<a  %s href= \"?%s=%d&%s=%s\">%s</a>" + "</li>", decorator, uri, page,
                uriLimit, limit, value);
        return url;
    }

    private String href(int page, int limit) {

        String url = String.format("\"?%s=%s\"", uriLimit, limit);
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

}
