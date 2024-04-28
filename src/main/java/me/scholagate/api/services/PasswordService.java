package me.scholagate.api.services;

import me.scholagate.api.models.Password;

public interface PasswordService {
    Password findById(Integer id);
    Password save(Password password);
    Password update(Password password);
    void deleteById(Integer id);
}
