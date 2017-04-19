package com.excilys.computerDatabase.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LinkTag extends SimpleTagSupport {

    public LinkTag() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void doTag() throws JspException, IOException {

        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
                                             
        try {
            out.println("Hello Custom Tag!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
