package com.poly.lab8.Controller;

import com.poly.lab8.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

//@RestController
@Controller
public class MailController {

    @Autowired
    private MailService mailService;

//    @ResponseBody
//    @RequestMapping("/mail/send")
    // task 1
//    public String sendEmail() {
//        try {
//            // Call the send method with default parameters
//            mailService.send("receiver@example.com", "Subject", "Email Body");
//            return "Mail has been sent successfully!";
//        } catch (Exception e) {
//            return "Error sending email: " + e.getMessage();
//        }
//    }

    // task 2
//    public String sendEmail(Model model) {
//        // Push mail to the queue
//        mailService.push("receiver@example.com", "Subject", "Email Body");
//
//        return "Mail đã được xếp vào hàng đợi và sẽ được gửi trong vài giây.";
//    }

    // task 3
    // Display the form to the user
    @GetMapping("/mail/form")
    public String showForm(Model model) {
        return "mail/email-form";  // This is the HTML form we created earlier
    }

    // Handle form submission
    @RequestMapping(value = "/mail/send-email", method = RequestMethod.POST)
    public String sendEmail(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("cc") String cc,
            @RequestParam("bcc") String bcc,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam("sendOption") String sendOption,
            @RequestParam("attachments") MultipartFile[] attachments) {

        // Create a Mail object using form data
        MailService.Mail mail = MailService.Mail.builder()
                .from(from)
                .to(to)
                .cc(cc)
                .bcc(bcc)
                .subject(subject)
                .body(body)
                .build();

        // Handle file attachments
        if (attachments != null && attachments.length > 0) {
            System.out.println("Number of attachments: " + attachments.length);
            StringBuilder filenames = new StringBuilder();
            for (MultipartFile file : attachments) {
                System.out.println("Processing file: " + file.getOriginalFilename() + " (size: " + file.getSize() + ")");
                try {
                    // Save the uploaded files temporarily
                    File tempFile = new File("uploads/" + file.getOriginalFilename());
                    file.transferTo(tempFile);
                    System.out.println("File saved to: " + tempFile.getAbsolutePath());
                    filenames.append(tempFile.getAbsolutePath()).append(",");
                } catch (IOException e) {
                    System.out.println("Error saving file: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            mail = mail.toBuilder().filenames(filenames.toString()).build();
            System.out.println("Final filenames string: " + filenames.toString());
        } else {
            System.out.println("No attachments found or attachments array is null/empty");
        }

        // Send the email directly or enqueue it based on the option
        if ("direct".equals(sendOption)) {
            mailService.send(mail);
        } else if ("enqueue".equals(sendOption)) {
            mailService.push(mail);
        }

        // Return success message
        return "Email has been " + ("direct".equals(sendOption) ? "sent" : "queued") + " successfully!";
    }
}
