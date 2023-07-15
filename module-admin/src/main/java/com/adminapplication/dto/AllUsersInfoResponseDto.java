package com.adminapplication.dto;

import com.core.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class AllUsersInfoResponseDto {
    private Integer id;
    private String nickname;
    private String email;
    private Role role;
    private Integer qtyOfBoard;   // Quantity Of Board
    private Integer qtyOfComment; // Quantity Of Board
}
