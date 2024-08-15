package com.playstore.users.infrastructure.outputPort;

import com.playstore.users.application.exception.RoleNotFoundException;
import com.playstore.users.domain.Role;

public interface IRoleMethods {

    Role findById(Long id) throws RoleNotFoundException;

    Role save(Role role);
}
