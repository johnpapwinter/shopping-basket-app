package com.shoppingcartapp.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "APP_ROLE_ID"))
    private Set<AppRole> appRoles = new HashSet<>();

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
