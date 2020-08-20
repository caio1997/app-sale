package com.home.project.controller;

import com.home.project.entity.Product;
import com.home.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("ListProducts", productService.findAll());
        return "ListProducts";
    }

    @GetMapping("/new")
    public String save(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "NewProduct";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ("product") Product product){
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/find")
    public ModelAndView findById(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("FindProduct");
        Product productFind = productService.getOne(id);
        mv.addObject("findProduct",productFind);
        return mv;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id){
        Product product = productService.getOne(id);
        productService.delete(id);
        return "redirect:/product";
    }


    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model){
        Product product = productService.getOne(id);
        model.addAttribute("product", product);
        return "Update";
    }
}
