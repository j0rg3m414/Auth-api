package com.tericcabrel.auth_api.bootstrap;

import com.tericcabrel.auth_api.entities.Role;
import com.tericcabrel.auth_api.entities.RoleEnum;
import com.tericcabrel.auth_api.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }


    private void loadRoles(){
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER,RoleEnum.ADMIN,RoleEnum.SUPER_ADMIN};
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Regra padrão para usuários",
                RoleEnum.ADMIN, "Regra para Administradores",
                RoleEnum.SUPER_ADMIN, "Regra para Super Admins"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
}
