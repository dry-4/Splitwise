package com.lld.splitwise.commands;

import com.lld.splitwise.controllers.GroupController;
import com.lld.splitwise.dtos.GroupRequestDto;
import com.lld.splitwise.dtos.GroupResponseDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddMemberCommand implements Command{
    private GroupController groupController;

    public AddMemberCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        return inputWords.size() == 4 && inputWords.get(1).equalsIgnoreCase(CommandKeyword.ADD_MEMBER);
    }

    @Override
    public void execute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();

        String owner = inputWords.get(0);
        String groupName = inputWords.get(2);
        String member = inputWords.get(3);

        // call group controller a get our action done


    }
}
