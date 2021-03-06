package com.edu.hutech.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "newControllerOfAdmin")
public class NewController {

    @RequestMapping(value = "/admin/news/list", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView mav = new ModelAndView("admin/new/list");
        return mav;
    }

    @RequestMapping(value = "/admin/news/edit", method = RequestMethod.GET)
    public ModelAndView editNew() {
        ModelAndView mav = new ModelAndView("admin/new/edit");
        return mav;
    }
}
