package com.agenciatorus.api.Entities;

import com.agenciatorus.api.validation.ExistsByUsername;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusers")
    private Long id;

    @ExistsByUsername//creado por mi esa anotacion
    @Column(unique = true)
    @NotBlank // no puede servacio
    @Size(min = 4, max = 12)
    private String username;
    @NotBlank // no puede servacio
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // se excluye cuando se lee el json osea en el metodo get
    //@JsonIgnore // elimina para leer y escribir osea post y get wq wbada
    private String password;

    @JsonIgnoreProperties({"usersList", "handler", "hibernateLazyInitializer"}) //ignorada contenido que queramos de la lista de roles, el cotenido basura que no queremos
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roleList;

    private boolean enable; // de la bd ya esta
    @PrePersist
    public  void prePersist (){
        enable = true; // es true cunado se  crea
    }

    @Transient // de pa la clase no de la bd
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;


    public Users() {
        this.roleList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(username, users.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
