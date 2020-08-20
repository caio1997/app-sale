package com.home.project.controller;

import com.home.project.entity.Client;
import com.home.project.entity.Product;
import com.home.project.service.ClientService;
import com.home.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("ListClient", clientService.findAll());
        return "ListClients";
    }

    @GetMapping("/new")
    public String save(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "NewClient";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute ("product") Client client){
        clientService.save(client);
        return "redirect:/client";
    }

    @GetMapping("/find")
    public ModelAndView findById(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("FindClient");
        Client clientFind = clientService.getOne(id);
        mv.addObject("findClient",clientFind);
        return mv;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id){
        Client client = clientService.getOne(id);
        clientService.delete(id);
        return "redirect:/client";
    }

    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model){
        Client client = clientService.getOne(id);
        model.addAttribute("client", client);
        return "UpdateClient";
    }
}
