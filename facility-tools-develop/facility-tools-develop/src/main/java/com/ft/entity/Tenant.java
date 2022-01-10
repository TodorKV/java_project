package com.ft.entity;

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
@Table(name = "tenants")
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {

    @Id
    private String id;

    @Column(name = "tenant_value")
    private String tenantValue;

    @OneToOne(mappedBy = "tenant")
    private User user;

    @OneToMany(targetEntity = Item.class, mappedBy = "tenant")
    private Set<Item> items = new HashSet<>();
}
