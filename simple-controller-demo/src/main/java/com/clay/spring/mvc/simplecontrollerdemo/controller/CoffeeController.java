package com.clay.spring.mvc.simplecontrollerdemo.controller;

import com.clay.spring.mvc.simplecontrollerdemo.model.Coffee;
import com.clay.spring.mvc.simplecontrollerdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author clay
 */

@Controller
@RequestMapping("/coffee")
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffee();
    }

    @PostMapping("/test")
    @ResponseBody
    public String testForFormData(HttpServletRequest request) throws IOException, ServletException {
        Collection<Part> parts = request.getParts();
        StandardMultipartHttpServletRequest standardMultipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
        Map<String, String[]> parameterMap = standardMultipartHttpServletRequest.getParameterMap();
        System.out.println(request);
        return "ok";
    }
}
