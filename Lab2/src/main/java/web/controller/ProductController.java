package web.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.Product;

@Controller
public class ProductController {
    private List<Product> items = new ArrayList<>(Arrays.asList(
            new Product("A", 1.0),
            new Product("B", 12.0)
    ));

    @GetMapping("/product/form")
    public String form(Model model) {
        Product p1 = new Product("iPhone 30", 5000.0);
        model.addAttribute("p1", p1);
        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("p2") Product p2, Model model) {
        items.add(p2);

        Product p1 = new Product("iPhone 30", 5000.0);
        model.addAttribute("p1", p1);

        return "product/form";
    }

    @ModelAttribute("items")
    public List<Product> getItems() {
        return items;
    }
}
