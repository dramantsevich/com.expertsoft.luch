package model;

import java.util.Objects;

public class User {
    private String username;
    private String phone;
    private String email;
    private String city;

    public User(String username, String phone, String email, String city){
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public User(String username, String phone, String email){
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    public User(String username){
        this.username = username;
    }

    public User(String phone, String email){
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    @Override
    public String toString(){
        return "User{}"+
                "username= '" + username + '\'' +
                ", phone= '" + phone + '\'' +
                ", email= '" + email + '\'' +
                ", city = '" + city + '\'';
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof User)) return false;
        User user=(User) o;
        return Objects.equals(getUsername(),user.getUsername()) &&
                Objects.equals(getPhone(),user.getPhone());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUsername(), getPhone());
    }
}
