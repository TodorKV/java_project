package com.ft.entity;

import com.ft.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "halls")
@NoArgsConstructor
@AllArgsConstructor
public class Hall extends BaseEntity {

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String address;

    @OneToMany(targetEntity = Box.class, mappedBy = "hall", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Box> boxes = new ArrayList<>();
}
