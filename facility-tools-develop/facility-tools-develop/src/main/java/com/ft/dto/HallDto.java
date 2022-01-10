package com.ft.dto;

import com.ft.dto.base.BaseDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HallDto extends BaseDto {

    private static final long serialVersionUID = 4L;

    private String name;
    private String city;
    private String address;
}
