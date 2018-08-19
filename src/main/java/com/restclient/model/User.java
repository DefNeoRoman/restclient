package com.restclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;


public class User implements Serializable {

    private Long id;
    private int age = 123;
    private String name;
    private String email = "user@mail.com";
    private Timestamp createdDate;
    private String password = "user";
    private Boolean enabled = true;

    @JsonIgnore
    private Set<Role> roles;

    public User() {
        createdDate = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {

        return roles;
    }

    public String getRolesString() {
        StringBuilder sb = new StringBuilder();
        if (roles != null) {
            roles.forEach(role -> {
                sb.append(role.getName()).append(", ");
            });
            if (sb.length() > 2) {
                return sb.toString().substring(0, sb.length() - 2);
            } else {
                if (roles.size() != 0) {
                    return roles.stream().findFirst().toString();
                } else {
                    return "NO_ROLE";
                }

            }
        }

        return sb.toString();

    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(createdDate, user.createdDate) &&
                Objects.equals(password, user.password) &&
                Objects.equals(enabled, user.enabled);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name, email, createdDate, password, enabled);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
