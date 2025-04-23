package com.harshvardhan.quality_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetails {

    private String name;
    private String email;
    private String password;
}
