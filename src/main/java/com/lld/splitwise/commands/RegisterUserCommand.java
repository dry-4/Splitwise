package com.lld.splitwise.commands;

import com.lld.splitwise.controllers.UserController;
import com.lld.splitwise.dtos.RegisterUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterUserCommand implements Command{
    // Register vinsmokesanji 003 namisswwaann

    private UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();

        return inputWords.size() == 4 && inputWords.get(0).equalsIgnoreCase(CommandKeyword.REGISTER_USER);
    }

    @Override
    public void execute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();

        String password = inputWords.get(1);
        String phoneNumber = inputWords.get(2);
        String userName = inputWords.get(3);

        // call user controller a get our action done

        RegisterUserRequestDto request = new RegisterUserRequestDto();
        request.setUserName(userName);
        request.setPassword(password);
        request.setPhoneNumber(phoneNumber);

        userController.registerUser(request);
    }
}
