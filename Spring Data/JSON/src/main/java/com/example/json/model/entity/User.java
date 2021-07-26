package com.example.json.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Set<Product> sold;
    private Set<Product> bought;
    private Set<User> friends;

    @ManyToMany
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany
    public Set<Product> getSold() {
        return sold;
    }

    public void setSold(Set<Product> sold) {
        this.sold = sold;
    }
    @OneToMany
    public Set<Product> getBought() {
        return bought;
    }

    public void setBought(Set<Product> bought) {
        this.bought = bought;
    }

    public User() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
