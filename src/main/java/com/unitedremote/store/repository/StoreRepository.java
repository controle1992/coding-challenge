package com.unitedremote.store.repository;
import com.unitedremote.store.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Store entity.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "select distinct store from Store store left join fetch store.users",
        countQuery = "select count(distinct store) from Store store")
    Page<Store> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct store from Store store left join fetch store.users")
    List<Store> findAllWithEagerRelationships();

    @Query("select store from Store store left join fetch store.users where store.id =:id")
    Optional<Store> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select distinct store from Store store left join fetch store.users users where users.login = ?#{principal.username}")
    List<Store> findFavorites();
}
