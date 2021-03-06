/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrenko.spittr.web.controller;

import com.vitrenko.spittr.model.service.SpittleService;
import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Vitalii_Vitrenko
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private final SpittleService spittleService;

    @Inject
    public SpittleController(SpittleService spitterService) {
        this.spittleService = spitterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        model.addAttribute(spittleService.find());
        return "spittles";
    }

    @RequestMapping(method = RequestMethod.GET, params = {"count"})
    public String spittles(
            @PageableDefault(sort = {"date"}, size = 10) Pageable pageable,
            Model model) {
        model.addAttribute("spittlePage", spittleService.find(pageable));
        return "spittles";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String spittle(@PathVariable("id") long id, Model model) {
        model.addAttribute("spittle", spittleService.find(id));
        return "spittle";
    }
}
