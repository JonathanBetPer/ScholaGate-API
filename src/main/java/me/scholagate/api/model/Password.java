package org.example.chatappapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Passwords", schema = "chatapp")
public class Password {
    @Id
    @Column(name = "idUsuario", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuarios;

    @Column(name = "hashResult", nullable = false)
    private String hashResult;

    @Column(name = "Salt", nullable = false)
    private byte[] salt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public String getHashResult() {
        return hashResult;
    }

    public void setHashResult(String hashResult) {
        this.hashResult = hashResult;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

}