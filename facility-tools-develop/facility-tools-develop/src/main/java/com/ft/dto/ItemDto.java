package com.ft.dto;

import com.ft.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ItemDto extends BaseDto {

    private static final long serialVersionUID = 4L;

    private String name;
    private String description;
    private TenantDto tenant;
    private BoxDto box;
}
