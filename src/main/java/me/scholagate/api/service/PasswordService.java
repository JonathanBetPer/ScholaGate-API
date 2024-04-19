package org.example.chatappapi.service;

import org.example.chatappapi.model.Password;

public interface PasswordService {
    Password findById(Integer id);
    Password save(Password password);
    Password update(Password password);
    void deleteById(Integer id);

}
