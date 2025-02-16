package com.qorvia.accountservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MiniUserInfoDTO {
    private String name;
    private String email;
    private String profileImg;
    private boolean isPrivate;
}
