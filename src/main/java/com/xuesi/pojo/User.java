package com.xuesi.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer id;

    private String email;

    private String password;

    private String nickname;

    private String sex;

    private String birthday;

    private String address;

    private String imgurl;

    private String code;

    private Date createtime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", createtime=" + createtime +
                '}';
    }

}