package JIDMU.theblog.controller;

import JIDMU.theblog.model.User;
import JIDMU.theblog.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup"; // return signup.html
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute User user,
                             Model model,
                             @ModelAttribute("confirmPassword") String passwordValidator) {

        String signupError = null;

        if (!signupService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            String rowsAdded = signupService.createUser(user, passwordValidator);
            switch (rowsAdded) {
                case "error: 101":
                    signupError = "Firstname must not be empty";
                    break;
                case "error: 102":
                    signupError = "Lastname must not be empty";
                    break;
                case "error: 103":
                    signupError = "Username must not be empty";
                    break;
                case "error: 104":
                    signupError = "Password must not be empty";
                    break;
                case "error: 105":
                    signupError = "Password confirmation fail";
                    break;
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        }

        else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
