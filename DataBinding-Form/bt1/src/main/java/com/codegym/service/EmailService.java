package com.codegym.service;

import com.codegym.model.Email;

import java.util.ArrayList;
import java.util.List;

public class EmailService implements IEmailService {
    private  static List<Email> emails = new ArrayList<>();
    static {
        emails.add(new Email(1,"Vietnamese",25,true,"Hello world!"));
    }
    @Override
    public List<Email> findAll() {
        return emails;
    }

    @Override
    public void save(Email email) {
        emails.add(email);
    }

    @Override
    public Email findById(int id) {
        for (Email email : emails) {
            if (email.getId() == id) {
                return email;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Email email) {
        for (int i = 0; i < emails.size(); i++) {
            if (emails.get(i).getId() == id) {
                emails.set(i, email);
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < emails.size(); i++) {
            if (emails.get(i).getId() == id) {
                emails.remove(i);
                break;
            }
        }
    }
}
