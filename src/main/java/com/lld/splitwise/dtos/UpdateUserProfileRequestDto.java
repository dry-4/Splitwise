package com.lld.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileRequestDto {
    private String userName;
    private String fieldToUpdate;
    private String newDetail;
}
