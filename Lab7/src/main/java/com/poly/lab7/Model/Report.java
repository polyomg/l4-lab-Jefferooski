package com.poly.lab7.Model;

import java.io.Serializable;

public interface Report {

    Serializable getGroup(); // Group by category (can be a String, Category object, etc.)

    Double getSum();        // Total price for the category

    Long getCount();        // Count of products in the category
}

