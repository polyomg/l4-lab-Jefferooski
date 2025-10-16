package com.poly.lab6.Controller;

import com.poly.lab6.DAO.ProductDAO;
import com.poly.lab6.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        String sortField = field.orElse("price"); // default to "price"
        Sort sort = Sort.by(Sort.Direction.DESC, sortField);

        List<Product> items = productDAO.findAll(sort);
        model.addAttribute("items", items);
        model.addAttribute("field", sortField.toUpperCase()); // show current sort field
        return "product/sort";
    }

    @GetMapping("/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        int pageIndex = p.orElse(0);          // Default page index = 0 (first page)
        Pageable pageable = PageRequest.of(pageIndex, 5); // 5 items per page
        Page<Product> page = productDAO.findAll(pageable);

        model.addAttribute("page", page);
        return "product/page";
    }
}
