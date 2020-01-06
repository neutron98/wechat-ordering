package cmu.youchun.recommender.model;

import lombok.Data;

import java.util.Date;
@Data
public class UserModel {

    private Integer id;

    private Date createdTime;

    private Date updatedTime;

    private String phone;

    private String password;


    private String nickName;

    private Integer gender;

}