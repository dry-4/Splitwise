package com.lld.splitwise.controllers;

import com.lld.splitwise.dtos.GroupRequestDto;
import com.lld.splitwise.dtos.GroupResponseDto;
import com.lld.splitwise.exceptions.UserNotFoundException;
import com.lld.splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    public GroupResponseDto addGroup(GroupRequestDto request) {
        GroupResponseDto response = new GroupResponseDto();
        try {
            groupService.addGroup(
                    request.getCreatedBy(),
                    request.getName()
            );
            response.setStatus("Success");
        }
        catch (UserNotFoundException userNotFoundException) {
            response.setStatus("Failed");
            response.setMessage(userNotFoundException.getMessage());
        }

        return response;
    }

}
