package com.poly.lab8.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    // Create a queue for storing mails
    private List<Mail> queue = new ArrayList<>();

    @Override
    public void send(Mail mail) {
        try {
            // 1. Create MimeMessage
            MimeMessage message = mailSender.createMimeMessage();

            // 2. Create helper for message content
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1. Set sender's email
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            // 2.2. Set recipient email
            helper.setTo(mail.getTo());
            if (!isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc());
            }
            if (!isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc());
            }

            // 2.3. Set subject and body content
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);  // true to send as HTML

            // 2.4. Attach files if any
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }

            // 3. Send the email
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void push(Mail mail) {
        // Add mail to the queue
        queue.add(mail);
    }

    @Override
    public void push(String to, String subject, String body) {
        // Create Mail object and add it to the queue
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        push(mail);
    }

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().length() == 0);
    }

    @Scheduled(fixedDelay = 500)  // This will run every 500ms
    public void run() {
        while (!queue.isEmpty()) {
            try {
                // Dequeue and send the first mail
                send(queue.remove(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

