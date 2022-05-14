package edu.sustech.cs307.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

}
