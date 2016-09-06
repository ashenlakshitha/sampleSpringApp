package com.intervest.staysure.aggregator;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intervest.staysure.api.service.MarginProtectionService;
import com.intervest.staysure.api.service.TestService;
import com.intervest.staysure.database.model.Test;

/**
 * Handles requests for the application home page.
 *
 */
@Controller
public class HomeController {

    @Autowired
    private TestService testService;

    @Autowired
    private MarginProtectionService marginProtectionService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("allQuotes", testService.getAllTests());
        return "home";
    }

    @RequestMapping(value = "/newQuote", method = RequestMethod.GET)
    public String saveNewQuote(Model model) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
        Test test = new Test();
        test.setStatus(testService.getAllTests().size());
        test.setDescription(dateFormat.format(new Date()));
        testService.saveTest(test);
        return "redirect:/index";
    }

    @RequestMapping(value = "/checkMarginProtectionService", method = RequestMethod.GET)
    public String checkMarginProtectionService(Model model) {
        model.addAttribute("value", marginProtectionService.getProtectedMargin("BASE", new BigDecimal("100"), new Date()));
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }

}
