package com.ft.entity;

import com.ft.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "boxes")
@NoArgsConstructor
@AllArgsConstructor
public class Box extends BaseEntity {

    @Column
    private String name;

    @Column
    private Integer floor;

    @Column(name = "placement_row")
    private Integer placementRow;

    @Column(name = "placement_column")
    private Integer placementColumn;

    @ManyToOne(targetEntity = Hall.class)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @OneToMany(targetEntity = Item.class, mappedBy = "box", cascade={CascadeType.REMOVE})
    private Set<Item> items = new HashSet<>();
}
