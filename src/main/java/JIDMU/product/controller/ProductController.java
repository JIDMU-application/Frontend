package JIDMU.product.controller;


import JIDMU.product.dto.ProductDTO;
import JIDMU.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String getBlogPage(Model model) {
        model.addAttribute("products", productService.getProduct());
        return "product";  // return product.html
    }

    @GetMapping("/product/add")
    public String getAddPage() {
        return "product-add";  // return product-add.html
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute ProductDTO product,
                                Model model) {
//        repository.save(product);
        productService.create(product);
        return "redirect:/product";
    }

}
