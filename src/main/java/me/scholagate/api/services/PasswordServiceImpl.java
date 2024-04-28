package me.scholagate.api.services;

import me.scholagate.api.models.Password;
import me.scholagate.api.repositories.PasswordRepository;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

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
