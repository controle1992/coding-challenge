package com.unitedremote.store.repository;
import com.unitedremote.store.domain.Store;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Store entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select store from Store store where store.user.login = ?#{principal.username}")
    List<Store> findByUserIsCurrentUser();

}
