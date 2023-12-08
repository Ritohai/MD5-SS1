package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.model.entity.Product;
import ra.model.service.IProductService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/")
    public String index(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/add")
    public  String add(Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String processEdit(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id) {
        productService.delete(productService.findById(id));
        return "redirect:/";
    }

}
