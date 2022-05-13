package JIDMU.product.controller;


import JIDMU.product.model.Product;
import JIDMU.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/product")
    public String getBlogPage(Model model) {
        model.addAttribute("products", repository.findAll());
        return "product";  // return product.html
    }

    @GetMapping("/product/add")
    public String getAddPage() {
        return "product-add";  // return product-add.html
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute Product product,
                                Model model) {
        repository.save(product);
        return "redirect:/product";
    }

}
