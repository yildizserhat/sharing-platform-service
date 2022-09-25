package com.yildiz.serhat.sharingplatformservice.service;


import com.yildiz.serhat.sharingplatformservice.domain.model.UserResponseModel;

public interface UserService {
    UserResponseModel registerUser(String email);

}
