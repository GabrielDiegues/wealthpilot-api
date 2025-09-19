package com.wollace.wealthPilot.repository.apiUser;

import com.wollace.wealthPilot.model.apiUser.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
    Optional<ApiUser> findByUserName(String userName);
}
