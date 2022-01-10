package com.ft.entity;

import com.ft.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(name = "tenant", parameters = {@ParamDef(name = "tenantid", type = "string")})
@Filter(name = "tenant", condition = "tenant_id = :tenantid")
public class Item extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne(targetEntity = Tenant.class)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    @ManyToOne(targetEntity = Box.class)
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private Box box;
}
