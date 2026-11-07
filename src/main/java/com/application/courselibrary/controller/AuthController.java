package com.application.courselibrary.controller;

import com.application.courselibrary.dto.RegistrationForm;
import com.application.courselibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    private boolean isLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
    }

    @GetMapping("/login")
    public String login() {
        if (isLoggedIn()) {
            return "redirect:/books";
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        if (isLoggedIn()) {
            return "redirect:/books";
        }
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationForm registrationForm,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (registrationForm.getPassword() != null
                && !registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "mismatch", "Passwords do not match");
        }
        if (registrationForm.getEmail() != null && !result.hasFieldErrors("email")
                && userService.emailExists(registrationForm.getEmail())) {
            result.rejectValue("email", "duplicate", "An account with this email already exists");
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerUser(registrationForm);
        redirectAttributes.addFlashAttribute("registered", true);
        return "redirect:/login";
    }
}
