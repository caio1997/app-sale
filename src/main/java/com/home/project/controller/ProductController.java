package com.home.project.controller;

import com.home.project.entity.Product;
import com.home.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("ListProducts");
        List<Product> listProduct = productService.findAll();
        mv.addObject("product", listProduct);
        return mv;
    }

    @GetMapping("/new")
    public String save(){
        return "NewProduct";
    }

    @PostMapping("/new")
    public String save(@Valid Product product){
        Product productSave = productService.save(product);
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
    public ModelAndView update(@RequestParam Long id){

        ModelAndView mv = new ModelAndView("Update");
        Product product = productService.getOne(id);
        mv.addObject("productUpdate",  product);
        return mv;
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute Product product){
        Product prod = productService.save(product);
        return "redirect:/product";
    }
}
