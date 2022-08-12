package com.rob.bitspleaseapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private long user_id;

    @Id
    @Column(nullable = false)
    private String authority;

    @Column(nullable = false)
    private String username;


    public Authority() {}
    public Authority(long user_id, String authority, String username) {
        this.user_id = user_id;
        this.authority = authority;
        this.username = username;
    }

    public long getUser_id() {return user_id;}
    public void setUser_id(long user_id) {this.user_id = user_id;}
    public String getAuthority() {return authority;}
    public void setAuthority(String authority) {this.authority = authority;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
}
