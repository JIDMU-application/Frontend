package JIDMU.product.controller;


import JIDMU.product.annotation.RequiresCaptcha;
import JIDMU.product.dto.ProductRequest;
import JIDMU.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

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
    public String getAddPage(ProductRequest productRequest) {
        return "product-add";  // return product-add.html
    }

    @PostMapping("/product/add")
    @RequiresCaptcha
    public String addProduct(@Valid ProductRequest product,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors())
            return "product-add";
//        repository.save(product);
        productService.create(product);
        return "redirect:/product";
    }

}
