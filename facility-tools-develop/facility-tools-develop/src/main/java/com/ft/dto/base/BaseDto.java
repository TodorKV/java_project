package com.ft.dto.base;

import lombok.*;

import java.io.Serializable;

/**
 * Base DTO abstract class.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class BaseDto implements Serializable {

    @NonNull
    private Long id;
}
