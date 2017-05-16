package com.excilys.cdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

@Controller
@RequestMapping("/")
public class Dashboard {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);
    private static final String URI_PAGE = "page";
    private static final String URI_LIMIT = "limit";
    private static final String LIMIT_DEFAULT = "10";
    private static final String URI_SEARCH = "search";
    private static final String URI_ORDER = "order";
    private static final String ORDER_DEFAULT = "ASC";
    private static final String URI_COLUMN = "column";
    private static final String COLUMN_ORDER = "computer.name";

    @Autowired
    private ComputerServiceImpl computerService;

    // @RequestMapping(method = RequestMethod.GET)
    // public String printHello(ModelMap model) {
    // System.out.println("COUCOU");
    // model.addAttribute("message", "Hello Spring MVC Framework!");
    //
    // return "hello";
    // }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(
            @RequestParam(value = URI_PAGE, required = false, defaultValue = "1") String currentPageString,
            @RequestParam(value = URI_LIMIT, required = false, defaultValue = LIMIT_DEFAULT) String limitString,
            @RequestParam(value = URI_SEARCH, required = false) String regex,
            @RequestParam(value = URI_ORDER, required = false, defaultValue = ORDER_DEFAULT) String order,
            @RequestParam(value = URI_COLUMN, required = false, defaultValue = COLUMN_ORDER) String column) {

        ModelAndView mv = new ModelAndView("dashboard");
        int size = 0;
        Pager<Computer> pager;

        int currentPage = Integer.parseInt(currentPageString);
        long limit = Long.parseLong(limitString);

        if (regex != null) {
            pager = computerService.getPage(limit, (currentPage - 1) * limit, regex, column, order);
            size = computerService.count(regex);
            mv.addObject("search", regex);
        } else {
            pager = computerService.getPage(limit, (currentPage - 1) * limit, column, order);
            size = computerService.count();
        }

        mv.addObject("listComputer", ComputerMapper.mapperToDTO(pager.getListEntity()));
        mv.addObject("limit", limit);
        mv.addObject("currentPage", currentPage);
        mv.addObject("maxPages", Math.ceil((double) size / limit));
        mv.addObject("uriPage", URI_PAGE);
        mv.addObject("uriLimit", URI_LIMIT);
        mv.addObject("uriSearch", URI_SEARCH);

        return mv;
    }

       
    // @Override
    // protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    // throws ServletException, IOException {
    //
    // String[] selection = req.getParameter("selection").split(",");
    //
    // List<Integer> listId = new ArrayList<>(selection.length);
    //
    // for (int i = 0; i < selection.length; i++) {
    //
    // if (isInteger(selection[i])) {
    // listId.add(Integer.parseInt(selection[i]));
    // } else {
    // LOGGER.debug("{} not a number", selection[i]);
    // }
    // }
    // computerService.remove(listId);
    // doGet(req, resp);
    // }
    //
    // /**
    // * @param s the string
    // * @return boolean if he is a number
    // */
    // private boolean isInteger(String s) {
    // try {
    // Integer.parseInt(s);
    // } catch (NumberFormatException e) {
    // return false;
    // } catch (NullPointerException e) {
    // return false;
    // }
    // return true;
    // }

}
