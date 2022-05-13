package JIDMU.theblog.controller;


import JIDMU.theblog.model.Blog;
import JIDMU.theblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository repository;

    @GetMapping("/blog")
    public String getBlogPage(Model model) {
        model.addAttribute("blogs", repository.findAll());
        return "blog";  // return blog.html
    }

    @GetMapping("/blog/add")
    public String getAddPage() {
        return "blog-add";  // return blog-add.html
    }

    @PostMapping("/blog/add")
    public String addBlog(@ModelAttribute Blog blog,
                                Model model) {
        repository.save(blog);
        return "redirect:/blog";
    }

}
