package com.poly.lab7.Controller;

import com.poly.lab7.Model.Report;
import com.poly.lab7.Repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ProductDAO productDAO; // Inject ProductDAO

    @RequestMapping("/report/inventory-by-category")
    public String inventory(Model model) {
        // Get the aggregated inventory data
        List<Report> items = productDAO.getInventoryByCategory();

        // Add data to model
        model.addAttribute("items", items);

        // Return the view name
        return "report/inventory-by-category";
    }
}
