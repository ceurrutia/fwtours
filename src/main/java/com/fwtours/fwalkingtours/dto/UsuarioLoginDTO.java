package com.fwtours.fwalkingtours.dto;

public class UsuarioLoginDTO {
    private String email;
    private String password;

    public UsuarioLoginDTO() {}

    public UsuarioLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
