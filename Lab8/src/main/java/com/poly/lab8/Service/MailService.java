package com.poly.lab8.Service;

import lombok.Builder;
import lombok.Data;

public interface MailService {
    void push(Mail mail);

    void push(String to, String subject, String body);

    @Data
    @Builder(toBuilder = true)
    public static class Mail {
        @Builder.Default
        private String from = "Ass master 9000 <duynnts00876@fpt.edu.vn>";
        private String to, cc, bcc, subject, body, filenames;
    }

    void send(Mail mail);

    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }
}
