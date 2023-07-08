package com.lld.splitwise.commands;

import com.lld.splitwise.controllers.UserController;
import com.lld.splitwise.dtos.UpdateUserProfileRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateUserCommand implements Command{
    private UserController userController;

    @Autowired
    public UpdateUserCommand(UserController userController) {
        this.userController = userController;
    }
    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        return inputWords.size() == 4 && inputWords.get(1).equalsIgnoreCase(CommandKeyword.UPDATE_PROFILE);
    }

    @Override
    public void execute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        System.out.println(inputWords);
        String userName = inputWords.get(0);
        String fieldToUpdate = inputWords.get(2);
        String newDetail = inputWords.get(3);

        // call user controller a get our action done
        UpdateUserProfileRequestDto request = new UpdateUserProfileRequestDto();
        request.setUserName(userName);
        request.setFieldToUpdate(fieldToUpdate);
        request.setNewDetail(newDetail);

        userController.updateUserProfile(request);

    }
}
