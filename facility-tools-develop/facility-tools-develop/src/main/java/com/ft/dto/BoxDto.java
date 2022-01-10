package com.ft.dto;

import com.ft.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BoxDto extends BaseDto {

    private static final long serialVersionUID = 4L;

    private String name;
    private Integer floor;
    private Integer placementRow;
    private Integer placementColumn;
    private HallDto hall;
}
