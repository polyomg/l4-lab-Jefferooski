package com.poly.lab4.Controller;

import com.poly.lab4.Entity.Staff;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StaffController {

    // Hiển thị form (GET)
    @GetMapping("/staff")
    public String showForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff";
    }

    // Xử lý form (POST)
    @PostMapping("/staff/save")
    public String saveForm(@Valid @ModelAttribute("staff") Staff staff,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "staff";
        }
        model.addAttribute("message", "Dữ liệu đã được submit!");
        return "staff";
    }
}
