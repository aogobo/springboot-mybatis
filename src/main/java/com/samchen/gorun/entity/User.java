package com.samchen.gorun.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
}
