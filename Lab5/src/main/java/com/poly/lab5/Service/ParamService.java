package com.poly.lab5.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value == null ? defaultValue : value;
    };

    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        return value == null ? defaultValue : Double.parseDouble(value);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        return value == null ? defaultValue : Boolean.parseBoolean(value);
    }

    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name); // Get parameter from the request
        if (value != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern); // Create SimpleDateFormat with pattern
                return sdf.parse(value); // Parse the string to date
            } catch (Exception e) {
                throw new RuntimeException("Invalid date format for parameter: " + name, e); // Throw exception if format is wrong
            }
        }
        return null; // Return null if the parameter is not present
    }

    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return null; // If there's no file, return null
        }

        try {
            // Ensure the directory exists
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }

            // Save the file
            File serverFile = new File(directory, file.getOriginalFilename()); // Create a file object with the given path and filename
            file.transferTo(serverFile); // Transfer the file to the server
            return serverFile; // Return the saved file object
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + file.getOriginalFilename(), e); // Handle file saving error
        }
    }

}
