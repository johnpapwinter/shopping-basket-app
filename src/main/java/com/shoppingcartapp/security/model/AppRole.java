package com.shoppingcartapp.security.model;

import com.shoppingcartapp.security.enums.RoleList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APP_ROLES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "APP_ROLE_ID")
    private Long id;

    @Column(name = "ROLES")
    @Enumerated(EnumType.STRING)
    private RoleList roleNames;

    @ManyToMany(mappedBy = "appRoles")
    private Set<AppUser> appUsers = new HashSet<>();

}
