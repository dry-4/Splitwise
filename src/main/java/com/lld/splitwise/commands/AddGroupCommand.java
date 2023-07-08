package com.lld.splitwise.commands;

import com.lld.splitwise.controllers.GroupController;
import com.lld.splitwise.dtos.GroupRequestDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddGroupCommand implements Command{

    private GroupController groupController;

    public AddGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        System.out.println(inputWords);
        System.out.println(inputWords.size() == 3 && inputWords.get(1).equalsIgnoreCase(CommandKeyword.ADD_GROUP));
        return inputWords.size() == 3 && inputWords.get(1).equalsIgnoreCase(CommandKeyword.ADD_GROUP);
    }

    @Override
    public void execute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();

        String createdBy = inputWords.get(0);
        String groupName = inputWords.get(2);

        // call group controller a get our action done
        GroupRequestDto requestDto = new GroupRequestDto();
        requestDto.setName(groupName);
        requestDto.setCreatedBy(createdBy);
        groupController.addGroup(requestDto);

    }
}
