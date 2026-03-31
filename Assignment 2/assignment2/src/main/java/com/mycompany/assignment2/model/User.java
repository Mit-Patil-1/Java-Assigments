package com.mycompany.assignment2.model;

public class User {
    private int userId;
    private String username;
    private String loginId;
    private String password;
    private String passwordQuestion;
    private String passwordAnswer;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pin;
    private String role;

    public User() {}

    public User(String username, String loginId, String password, String role) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPasswordQuestion() { return passwordQuestion; }
    public void setPasswordQuestion(String passwordQuestion) { this.passwordQuestion = passwordQuestion; }

    public String getPasswordAnswer() { return passwordAnswer; }
    public void setPasswordAnswer(String passwordAnswer) { this.passwordAnswer = passwordAnswer; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
