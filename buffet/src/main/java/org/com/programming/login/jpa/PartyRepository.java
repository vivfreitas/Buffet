package org.com.programming.login.jpa;

import org.com.programming.login.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<PartyEntity, Long> {

    // "SELECT * FROM TB_PARTY WHERE USER_ID = {?}"
    List<PartyEntity> findByUsersIdUser(Long userId);
}
