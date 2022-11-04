package com.shoppingcartapp.security.repository;

import com.shoppingcartapp.security.enums.RoleList;
import com.shoppingcartapp.security.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    Optional<AppRole> findAppRoleByRoleNames(RoleList roleName);
}
