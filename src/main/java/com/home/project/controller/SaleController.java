package com.home.project.controller;

import com.home.project.entity.Product;
import com.home.project.entity.Sale;
import com.home.project.service.ClientService;
import com.home.project.service.ProductService;
import com.home.project.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("ListSale", saleService.findAll());
        return "ListSales";
    }

    @GetMapping("/new")
    public String save(Model model) {
        Sale sale = new Sale();
        model.addAttribute("client", clientService.findAll());
        model.addAttribute("product", productService.findAll());
        model.addAttribute("sale", sale);
        return "NewSale";
    }

    @PostMapping("/saleproduct")
    public String addProduct(@ModelAttribute Product product, @RequestParam Long id){

        Sale sale = saleService.getOne(id);
        product = productService.getOne(product.getId());

        sale.getProducts().add(product);
        saleService.save(sale);

        return "redirect:/sale/details?id=" + sale.getId();
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ("sale") Sale sale){
        saleService.save(sale);
        return "redirect:/sale";
    }

    @GetMapping("/details")
    public ModelAndView findById(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("FindSale");
        Sale saleFind = saleService.getOne(id);
        List<Product> list = new ArrayList<>();
        list.addAll(saleFind.getProducts());
        for(int i = 0; i < saleFind.getProducts().stream().count(); i++){
            System.out.println(list.get(i).getName());
        }
        List<Product> productNoAdd = productService.findAll();
        productNoAdd.removeAll(saleFind.getProducts());
        mv.addObject("listProduct", list);
        mv.addObject("products", productNoAdd);
        mv.addObject("findsale",saleFind);
        return mv;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id){
        Sale sale = saleService.getOne(id);
        saleService.delete(id);
        return "redirect:/sale";
    }
}
