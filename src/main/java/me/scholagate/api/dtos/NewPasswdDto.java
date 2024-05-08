package me.scholagate.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record NewPasswdDto(@NotNull @Size(max = 75) String password) implements Serializable {
}