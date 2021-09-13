package com.kosalaam.api.modules.kouser.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KoUserRepository extends JpaRepository<KoUser, Long> {

    public KoUser findByFirebaseUuid(String firebaseUuid);

}
