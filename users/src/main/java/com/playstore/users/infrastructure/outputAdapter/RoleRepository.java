package com.playstore.users.infrastructure.outputAdapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playstore.users.domain.Role;
import com.playstore.users.infrastructure.outputPort.IRoleMethods;

@Component
public class RoleRepository implements IRoleMethods {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

}
