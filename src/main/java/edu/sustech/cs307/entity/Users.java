package edu.sustech.cs307.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-14
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
            "username=" + username +
            ", password=" + password +
        "}";
    }
}
