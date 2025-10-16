package com.poly.lab6.Controller;

import com.poly.lab6.DAO.CategoryDAO;
import com.poly.lab6.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping("/category/index")
    public String categoryIndex(Model model){
        Category item = new Category();
        model.addAttribute("item",item);
        List<Category> items = categoryDAO.findAll();
        model.addAttribute("items",items);
        return "category/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Category item = categoryDAO.findById(Integer.valueOf(id)).orElse(new Category());
        model.addAttribute("item", item);
        model.addAttribute("items", categoryDAO.findAll());
        return "category/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category item) {
        categoryDAO.save(item);
        return "redirect:/category/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Category item) {
        categoryDAO.save(item);
        return "redirect:/category/edit/" + item.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        categoryDAO.deleteById(Integer.valueOf(id));
        return "redirect:/category/index";
    }
}
