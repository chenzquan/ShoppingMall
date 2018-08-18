package com.example.shopping_ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：Created by 权.
 * 时间：2018/8/18.
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {

    @Id
    private long UserId = 0;
    private String name = null;
    private String avatar = null;
    private String gender = null;
    private String address = null;
    @Generated(hash = 253389480)
    public UserProfile(long UserId, String name, String avatar, String gender,
            String address) {
        this.UserId = UserId;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.UserId;
    }
    public void setUserId(long UserId) {
        this.UserId = UserId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
