package com.prime.repositories;

import com.prime.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    @Transactional(readOnly = true)
    SystemUser findByEmail(String email);
}
