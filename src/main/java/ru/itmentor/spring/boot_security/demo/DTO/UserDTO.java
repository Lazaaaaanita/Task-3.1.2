package ru.itmentor.spring.boot_security.demo.DTO;

import ru.itmentor.spring.boot_security.demo.model.Role;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    @NotEmpty(message = "Name should be not empty")
    @Size(min=2,max=30, message = "Name should be between 2 and 30 symbols")
    private String name;

    @NotEmpty(message = "Lastname should be not empty")
    @Size(min=2,max=30, message = "Lastname should be between 2 and 30 symbols")
    private String lastName;

    @Min(value = 0, message = "Age must be greater than 0")
    private int age;

    @NotEmpty(message = "Password should be not empty")
    private String password;
    @NotEmpty(message = "Role should be not empty")
    private Set<String> roles;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
