package org.com.programming.login.jpa;

import org.com.programming.login.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<PartyEntity, Long> {
}
