package ru.ncteam.levelchat.entity;


import javax.persistence.*;
import java.sql.Blob;
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

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "PHOTO_AVA")
    private Blob photo_ava;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_INTEREST",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "INTEREST_ID")})
    private Set<Interests> interests = new HashSet<Interests>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<ChatGroup> chatGroups = new HashSet<ChatGroup>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
    public Set<Messages> messages = new HashSet<Messages>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
    public Set<PhotoLib> photoLibs = new HashSet<PhotoLib>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
    public Set<UserData> userDatas = new HashSet<UserData>();

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

    public Set<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(Set<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public Set<Messages> getMessages() {
        return messages;
    }

    public void setMessages(Set<Messages> messages) {
        this.messages = messages;
    }

    public Set<PhotoLib> getPhotoLibs() {
        return photoLibs;
    }

    public void setPhotoLibs(Set<PhotoLib> photoLibs) {
        this.photoLibs = photoLibs;
    }

    public Set<UserData> getUserDatas() {
        return userDatas;
    }

    public void setUserDatas(Set<UserData> userDatas) {
        this.userDatas = userDatas;
    }
}
