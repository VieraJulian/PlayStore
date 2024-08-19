package com.playstore.users.infrastructure.outputPort;

import java.util.Optional;

import com.playstore.users.domain.Role;

public interface IRoleMethods {

    Optional<Role> findById(Long id);

    Role save(Role role);
}
