package com.wollace.wealthPilot.repository.appUser;

import com.wollace.wealthPilot.model.appUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByFirebaseUid(String firebaseUid);
    Optional<AppUser> findByFirebaseUid(String firebaseUid);
    void deleteByFirebaseUid(String firebaseUid);
}
