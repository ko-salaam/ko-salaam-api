package com.kosalaam.api.modules.kouser.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface KoUserRepository extends JpaRepository<KoUser, Long> {

    public Optional<KoUser> findByFirebaseUuid(String firebaseUuid);

}
