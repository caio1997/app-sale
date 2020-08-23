package com.home.project.config;

import com.home.project.entity.Client;
import com.home.project.entity.Product;
import com.home.project.entity.Sale;
import com.home.project.repository.ClientRepository;
import com.home.project.repository.ProductRepository;
import com.home.project.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class ClassTest implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public void run(String... args) throws Exception {

        Client client = new Client(null, "Caio", "Marinho01", "caio01@hotmail.com");
        clientRepository.save(client);

        Client clientTwo = new Client(null, "Caio01", "Marinho02", "caio02@hotmail.com");
        clientRepository.save(clientTwo);

        Client clientThree = new Client(null, "Caio02", "Marinho03", "caio03@hotmail.com");
        clientRepository.save(clientThree);

        Product productOne = new Product(null, "Product Test 01", "Description Test 01", 10.5, 51.0);
        Product productTwo = new Product(null, "Product Test 02", "Description Test 02", 1.51, 14.0);
        Product productThree = new Product(null, "Product Test 03", "Description Test 03", 5.59, 77.23);
        Product productFour = new Product(null, "Product Test 04", "Description Test 04", 23.5, 5.89);
        Product productOneOne = new Product(null, "Product Test 01 01", "Description Test 01 01", 11.56, 5.0);
        Product productTwoTwo = new Product(null, "Product Test 02 02", "Description Test 02 02", 1.45, 114.0);
        Product productThreeThree = new Product(null, "Product Test 03 03", "Description Test 03 03", 4.59, 12.23);
        Product productFourFour = new Product(null, "Product Test 04 04", "Description Test 04 04", 58.5, 98.84);
        productRepository.saveAll(Arrays.asList(productOne, productTwo, productThree, productFour));
        productRepository.saveAll(Arrays.asList(productOneOne, productTwoTwo, productThreeThree, productFourFour));

        List<Product> listOne = new ArrayList<>();
        listOne.add(productOne);
        listOne.add(productTwo);
        listOne.add(productThree);
        listOne.add(productFour);

        List<Product> listTwo = new ArrayList<>();
        listTwo.add(productOneOne);
        listTwo.add(productTwoTwo);

        System.out.println(listTwo);

        List<Product> listThree = new ArrayList<>();
        listThree.add(productThreeThree);
        listThree.add(productFourFour);

        Sale saleOne = new Sale(null, 0.5, 12.0, client, listOne);
        saleRepository.save(saleOne);

        Sale saleTwo = new Sale(null, 56.51, 59.97, clientTwo, listThree);
        saleRepository.save(saleTwo);

        Sale saleThree = new Sale(null, 20.2, 102.25, clientThree, listTwo);
        saleRepository.save(saleThree);
    }
}
