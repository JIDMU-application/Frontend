package JIDMU.product.controller;

import JIDMU.product.dto.ReviewRequest;
import JIDMU.product.service.JwtAccessTokenService;
import JIDMU.product.service.ProductService;
import JIDMU.product.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.security.Principal;


@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private JwtAccessTokenService jwtService;

    @GetMapping("/{productId}")
    public String getReviewPage(@PathVariable UUID productId,
                                Model model) {

        model.addAttribute("product",
                productService.getProductById(productId));
        model.addAttribute("reviews",
                reviewService.getProductReview(productId));

        return "review";
    }

    @GetMapping("/add/{productId}")
    public String getReviewForm(@PathVariable UUID productId,
                                Model model) {
        model.addAttribute("productId", productId);
        return "review-add";
    }

    @PostMapping("/add")
    public String createReview(@ModelAttribute ReviewRequest review,
                               Model model, Principal principal) {
        String username = principal.getName();
        review.setUsername(username);

        reviewService.createReview(review);
        return "redirect:/review/" + review.getProductId();
    }
}

