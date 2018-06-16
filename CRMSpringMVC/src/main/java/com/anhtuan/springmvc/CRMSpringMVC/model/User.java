package com.anhtuan.springmvc.CRMSpringMVC.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Integer id;

    @Column(name = "IDENTIFICATION_CARD")
    private int identifyCard;

    @NotNull
    @Size(max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @JsonIgnore
    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @JsonIgnore
    @Column(name = "RETIREMENT_DATE")
    private Date retirementDate;

    @Size(max = 30)
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotNull
    @Size(max = 30)
    @Column(name = "LOGIN", unique = true)
    private String login;

    @NotNull
    @Size(max = 225)
    @Column(name = "PASSWORD")
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID_USER", foreignKey = @ForeignKey(name = "FK_USERS")),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID_ROLE", foreignKey = @ForeignKey(name = "FK_ROLES"))
    )
    //https://howtodoinjava.com/hibernate/hibernate-jpa-cascade-types/
    private List<Role> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(int identifyCard) {
        this.identifyCard = identifyCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(Date retirementDate) {
        this.retirementDate = retirementDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", identifyCard=" + identifyCard +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", joinDate=" + joinDate +
                ", retirementDate=" + retirementDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
