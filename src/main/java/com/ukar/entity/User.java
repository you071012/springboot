package com.ukar.entity;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by jyou on 2017/9/15.
 */
@Table(name = "t_user")
public class User  implements Serializable{
    private Long id;

    private String name;

    private String password;

    @Transient
    private String idCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
