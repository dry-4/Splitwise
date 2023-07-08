package com.lld.splitwise.controllers;

import com.lld.splitwise.dtos.RegisterUserRequestDto;
import com.lld.splitwise.dtos.RegisterUserResponseDto;
import com.lld.splitwise.dtos.UpdateUserProfileRequestDto;
import com.lld.splitwise.dtos.UpdateUserProfileResponseDto;
import com.lld.splitwise.exceptions.UserAlreadyExistsException;
import com.lld.splitwise.exceptions.UserNotFoundException;
import com.lld.splitwise.modles.User;
import com.lld.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class UserController {
    private UserService userService;

    private Logger logger;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {
        User user;
        RegisterUserResponseDto response = new RegisterUserResponseDto();
        try {
            user = userService.registerUser(
                    request.getUserName(),
                    request.getPassword(),
                    request.getPhoneNumber()
            );

            response.setUserId(user.getId());
            response.setStatus("SUCCESS");
        }
        catch (UserAlreadyExistsException userAlreadyExistsException) {
            response.setStatus("FAILURE");
            response.setMessage(userAlreadyExistsException.getMessage());
        }

        return  response;
    }

    public UpdateUserProfileResponseDto updateUserProfile(UpdateUserProfileRequestDto request) {
        User user;
        UpdateUserProfileResponseDto response = new UpdateUserProfileResponseDto();

        try {
            user = userService.updateProfile(
                    request.getUserName(),
                    request.getFieldToUpdate(),
                    request.getNewDetail()
            );

            response.setUserName(request.getUserName());
            response.setStatus("Success");
        }
        catch (UserNotFoundException userNotFoundException){
            response.setStatus("Failed");
            response.setMessage(userNotFoundException.getMessage());
        }

        return response;
    }
}
