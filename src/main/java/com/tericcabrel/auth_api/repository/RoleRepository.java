package com.tericcabrel.auth_api.repository;

import com.tericcabrel.auth_api.entities.Role;
import com.tericcabrel.auth_api.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
