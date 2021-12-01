package com.example.demo.dto.request;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostRequest {

    private String username;
    private String password;
    private String email;
    private Set<String> authorities;


}
