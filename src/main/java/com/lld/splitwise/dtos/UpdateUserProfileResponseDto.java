package com.lld.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileResponseDto {
    private String userName;
    private String status;
    private String message;
}
