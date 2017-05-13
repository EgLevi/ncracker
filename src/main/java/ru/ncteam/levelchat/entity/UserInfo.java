package ru.ncteam.levelchat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long user_id;

    @Column(name = "NAME", length = 30)
    @Size(min=0, max=30, message="Имя должно быть от 1 до 20 символов")
    @Pattern(regexp="[a-zA-Z0-9[А-я][-_*]]{0,}", message="Допустимые символы: буквы, цифры, _, -, *")
    private String name;


    @Column(name = "SURNAME", length = 30)
    @Size(min=0, max=30, message="Фамилия должна быть от 1 до 20 символов")
    @Pattern(regexp="[a-zA-Z0-9[А-я][-_*]]{0,}", message="Допустимые символы: буквы, цифры, _, -, *")
    private String surname;

    @Column(name = "SEX", length = 1)
    private String sex;

    @Column(name = "AGE")
    @Max(value=150, message="Возраст не может превышать 150 лет")
    @Min(value=0, message="Возраст не может быть меньше 1 года")
    private int age;

    @Size(min=0, max=30, message="Слишком большое(маленькое) название страны")
    @Pattern(regexp="[a-zA-Z0-9[А-я][-_*]]{0,}", message="Допустимые символы: буквы, цифры, _, -, *")
    @Column(name = "COUNTRY", length = 30)
    private String country;

    @Size(min=0, max=30, message="Слишком большое(маленькое) название города")
    @Pattern(regexp="[a-zA-Z0-9[А-я][-_*]]{0,}", message="Допустимые символы: буквы, цифры, _, -, *")
    @Column(name = "CITY", length = 30)
    private String city;


    @Column(name = "LOGIN", length = 30, unique = true)
    @Size(min=1, max=30, message="Логин должен быть от 1 до 20 символов")
    @Pattern(regexp="[a-zA-Z0-9[А-я][-_*]]+", message="Допустимые символы: буквы, цифры, _, -, *")
    private String login;


    @Column(name = "PASSWORD", length = 60)
    @Size(min=6, max=30, message="Пароль должен быть от 6 до 30 символов")
    @Pattern(regexp="[a-zA-Z0-9[-_*]]+", message="Допустимые символы: буквы, цифры, _, -, *")
    private String password;
    

    @Column(name = "EMAIL", length = 30)
    @Size(min=0, max=30, message="Email должен быть от 6 до 30 символов")
    @Pattern(regexp="[[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,4}]{0,1}", message="неправильный Email")
    private String email;

    @Column(name = "PHOTO_AVA")
    private String photo_ava;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    @JsonIgnore
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_INTEREST",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "INTEREST_ID")})
    @JsonIgnore
    private Set<Interests> interests = new HashSet<Interests>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_CHAT",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CHAT_ID")})
    public Set<Chat> chats = new HashSet<Chat>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
    @JsonIgnore
    public Set<Message> messages = new HashSet<Message>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
    @JsonIgnore
    public Set<PhotoLib> photoLibs = new HashSet<PhotoLib>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
    @JsonIgnore
    public Set<UserData> userData = new HashSet<UserData>();

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

    public String getPhoto_ava() {
        return photo_ava;
    }

    public void setPhoto_ava(String photo_ava) {
        this.photo_ava = photo_ava;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Interests> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interests> interests) {
        this.interests = interests;
    }

    @JsonIgnore
    public Set<Chat> getChat() {
        return chats;
    }

    public void setChatGroups(Set<Chat> chats) {
        this.chats = chats;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<PhotoLib> getPhotoLibs() {
        return photoLibs;
    }

    public void setPhotoLibs(Set<PhotoLib> photoLibs) {
        this.photoLibs = photoLibs;
    }

    public Set<UserData> getUserData() {
        return userData;
    }

    public void setUserData(Set<UserData> userDatas) {
        this.userData = userDatas;
    }
}
