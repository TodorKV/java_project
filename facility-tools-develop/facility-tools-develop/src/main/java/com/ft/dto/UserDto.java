package com.ft.dto;

import com.ft.dto.base.BaseDto;
import com.ft.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {

    private static final long serialVersionUID = 4L;

    private String username;
    private String password;
    private Role role;
    private TenantDto tenant;
}
