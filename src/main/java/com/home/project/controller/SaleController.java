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

    @PostMapping("/save")
    public String save(@ModelAttribute ("sale") Sale sale){
        saleService.save(sale);
        return "redirect:/sale/saveProductsInSale?id=" + sale.getId();
    }

    @PostMapping("/saleproduct")
    public String addProduct(@ModelAttribute Product product, @RequestParam Long id){

        Sale sale = saleService.getOne(id);
        product = productService.getOne(product.id)
;        System.out.println("------------------------------------------------");
        System.out.println(product);
        System.out.println(product.getId());
        System.out.println(product.getName());

        System.out.println(sale);
        System.out.println(sale.getId());
        System.out.println(sale.getProducts());
        System.out.println(sale.getClient().getName());
        System.out.println("------------------------------------------------");

        sale.getProducts().add(product);
        saleService.save(sale);

        return "redirect:/sale/saveProductsInSale?id=" + sale.getId();
    }

    @GetMapping("/saveProductsInSale")
    public ModelAndView saveProductsInSale(@RequestParam Long id){

        Sale saleFind = saleService.getOne(id);
        ModelAndView mv = new ModelAndView("AddProductInSale");
        mv.addObject("findsale",saleFind);
        List<Product> list = new ArrayList<>();
        list.addAll(saleFind.getProducts());
        for(int i = 0; i < saleFind.getProducts().stream().count(); i++){
            System.out.println(list.get(i).getName());
        }
        mv.addObject("listProduct", list);
        List<Product> productNoAdd = productService.findAll();
        productNoAdd.removeAll(saleFind.getProducts());
        mv.addObject("products", productNoAdd);

        return mv;
    }

    @GetMapping("/details")
    public ModelAndView findById(@RequestParam Long id){

        Sale saleFind = saleService.getOne(id);
        ModelAndView mv = new ModelAndView("FindSale");
        mv.addObject("findsale",saleFind);
        List<Product> list = new ArrayList<>();
        list.addAll(saleFind.getProducts());
        for(int i = 0; i < saleFind.getProducts().stream().count(); i++){
            System.out.println(list.get(i).getName());
        }
        mv.addObject("listProduct", list);
        return mv;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id){
        Sale sale = saleService.getOne(id);
        saleService.delete(id);
        return "redirect:/sale";
    }
}
