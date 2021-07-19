package com.nurim.mvbunker.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int i_user;
    private String uid;
    private String ex_key;
    private String provider;
    private String upw;
    private String unm;
    private int gender;
    private int age;
    private String unn;
    private String profileImg;
    private String regdt;
    private String auth;
}
