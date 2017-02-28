package ru.ncteam.levelchat.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long user_id;

    @Column(name = "NAME", length = 30)
    private String name;

    @Column(name = "SURNAME", length = 30)
    private String surname;

    @Column(name = "SEX", length = 1)
    private String sex;

    @Column(name = "AGE")
    private int age;

    @Column(name = "COUNTRY", length = 30)
    private String country;

    @Column(name = "CITY", length = 30)
    private String city;

    @Column(name = "LOGIN", length = 100, unique = true)
    private String login;

    @NotNull
    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "PHOTO_AVA")
    private Blob photo_ava;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Blob getPhoto_ava() {
        return photo_ava;
    }

    public void setPhoto_ava(Blob photo_ava) {
        this.photo_ava = photo_ava;
    }
}
