package com.lld.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GroupRequestDto {
    private Long id;
    private String description;
    private String name;
    private String createdBy;
    private String memberUserName;
}
