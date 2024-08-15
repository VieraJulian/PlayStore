package com.playstore.users.infrastructure.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playstore.users.domain.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}
