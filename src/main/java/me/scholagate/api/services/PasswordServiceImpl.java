package me.scholagate.api.service;

import me.scholagate.api.model.Password;
import me.scholagate.api.repository.PasswordRepository;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService{

    private final PasswordRepository passwordRepository;

    public PasswordServiceImpl(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Override
    public Password findById(Integer id) {
        return passwordRepository.findById(id).orElse(null);
    }

    @Override
    public Password save(Password password) {
        return passwordRepository.save(password);
    }

    @Override
    public Password update(Password password) {
        return passwordRepository.save(password);
    }

    @Override
    public void deleteById(Integer id) {
        passwordRepository.deleteById(id);
    }
}
