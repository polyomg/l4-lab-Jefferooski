package com.poly.lab7.Controller;

import com.poly.lab7.Repository.ProductDAO;
import com.poly.lab7.Entity.Product;
import com.poly.lab7.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDAO dao;

    @Autowired
    private SessionService session;

    // task 1
//    @RequestMapping("/search")
//    public String search(Model model,
//                         @RequestParam("min") Optional<Double> min,
//                         @RequestParam("max") Optional<Double> max,
//                         @RequestParam("page") Optional<Integer> page,
//                         @RequestParam("size") Optional<Integer> size) {
//
//        int pageSize = size.orElse(5);
//        int requestedPage = page.orElse(0);
//
//        double minPrice = min.orElse(Double.MIN_VALUE);
//        double maxPrice = max.orElse(Double.MAX_VALUE);
//
//        // Get the total pages based on the initial query
//        Pageable tempPageable = PageRequest.of(0, pageSize);
//        Page<Product> tempPage = dao.findByPrice(minPrice, maxPrice, pageable);
//        int totalPages = tempPage.getTotalPages();
//
//        // Wrap-around logic for pagination
//        if (totalPages > 0) {
//            if (requestedPage >= totalPages) {
//                requestedPage = 0;
//            } else if (requestedPage < 0) {
//                requestedPage = totalPages - 1;
//            }
//        }
//
//        // Final query with adjusted page number
//        Pageable finalPageable = PageRequest.of(requestedPage, pageSize);
//        Page<Product> productPage = dao.findByPrice(minPrice, maxPrice, pageable);
//
//        model.addAttribute("items", productPage.getContent());
//        model.addAttribute("totalPages", productPage.getTotalPages());
//        model.addAttribute("currentPage", productPage.getNumber());
//        model.addAttribute("min", minPrice);
//        model.addAttribute("max", maxPrice);
//        model.addAttribute("size", pageSize);
//
//        return "product/search";
//    }
    // Task 4
    @RequestMapping("/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {

        int pageSize = size.orElse(5);
        int requestedPage = page.orElse(0);

        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);

        // Get the total pages based on the initial query
        Pageable tempPageable = PageRequest.of(0, pageSize);
        Page<Product> tempPage = dao.findByPriceBetween(minPrice, maxPrice);
        int totalPages = tempPage.getTotalPages();

        // Wrap-around logic for pagination
        if (totalPages > 0) {
            if (requestedPage >= totalPages) {
                requestedPage = 0;
            } else if (requestedPage < 0) {
                requestedPage = totalPages - 1;
            }
        }

        // Final query with adjusted page number
        Pageable finalPageable = PageRequest.of(requestedPage, pageSize);
        Page<Product> productPage = dao.findByPriceBetween(minPrice, maxPrice);

        model.addAttribute("items", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", productPage.getNumber());
        model.addAttribute("min", minPrice);
        model.addAttribute("max", maxPrice);
        model.addAttribute("size", pageSize);

        return "product/search";
    }

    // Task 2: Search with keywords and pagination
//    @RequestMapping("/search-and-page")
//    public String searchAndPage(Model model,
//                                @RequestParam("keywords") Optional<String> kw,
//                                @RequestParam("p") Optional<Integer> p) {
//
//        String kwords = kw.orElse(session.get("keywords", ""));
//        session.set("keywords", kwords);
//
//        Pageable pageable = PageRequest.of(p.orElse(0), 5);
//        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);
//
//        model.addAttribute("page", page);
//        model.addAttribute("keywords", kwords); // To refill input field
//
//        return "product/search-and-page";
//    }

    // task 5
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") String keywords,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {

        // Default values for pagination
        int pageSize = size.orElse(5);
        int requestedPage = page.orElse(0);

        // Create Pageable object for pagination
        Pageable pageable = PageRequest.of(requestedPage, pageSize);

        // Fetch paginated and filtered products based on keywords
        Page<Product> productPage = dao.findAllByNameLike("%" + keywords + "%", pageable);

        // Add data to the model
        model.addAttribute("page", productPage);
        model.addAttribute("keywords", keywords);  // To pre-fill the search input

        return "product/search-and-page";  // Assuming your view is named like this
    }
}
