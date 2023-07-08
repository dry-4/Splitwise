package com.lld.splitwise.dtos;

import com.lld.splitwise.modles.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupRequestDto {
    private Long id;
    private String description;
    private String name;
    private String createdBy;
    private String memberUserName;
}
