package me.scholagate.api.service;

import me.scholagate.api.model.Password;

public interface PasswordService {
    Password findById(Integer id);
    Password save(Password password);
    Password update(Password password);
    void deleteById(Integer id);
}
