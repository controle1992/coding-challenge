package com.unitedremote.store.service;

import com.unitedremote.store.domain.Store;
import com.unitedremote.store.domain.User;
import com.unitedremote.store.repository.StoreRepository;
import com.unitedremote.store.repository.UserRepository;
import com.unitedremote.store.security.SecurityUtils;
import com.unitedremote.store.service.dto.StoreDTO;
import com.unitedremote.store.service.mapper.StoreMapper;
import com.unitedremote.store.service.util.Location;
import com.unitedremote.store.service.util.LocationComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Store}.
 */
@Service
@Transactional
public class StoreService {

    private final Logger log = LoggerFactory.getLogger(StoreService.class);

    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    private final StoreMapper storeMapper;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a store.
     *
     * @param storeDTO the entity to save.
     * @return the persisted entity.
     */
    public StoreDTO save(StoreDTO storeDTO) {
        log.debug("Request to save Store : {}", storeDTO);
        Store store = storeMapper.toEntity(storeDTO);
        store = storeRepository.save(store);
        return storeMapper.toDto(store);
    }

    /**
     * Get all the stores.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StoreDTO> findAll() {
        log.debug("Request to get all Stores");
        return storeRepository.findAllWithEagerRelationships().stream()
            .map(storeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the stores with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<StoreDTO> findAllWithEagerRelationships(Pageable pageable) {
        return storeRepository.findAllWithEagerRelationships(pageable).map(storeMapper::toDto);
    }


    /**
     * Get one store by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StoreDTO> findOne(Long id) {
        log.debug("Request to get Store : {}", id);
        return storeRepository.findOneWithEagerRelationships(id)
            .map(storeMapper::toDto);
    }

    /**
     * Delete the store by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Store : {}", id);
        storeRepository.deleteById(id);
    }

    /**
     * Get all the user's favorite stores.
     *
     * @return the list of entities.
     */
    public List<StoreDTO> findUserFavoriteStores() {
        log.debug("Request to get all Stores for current User");
        return storeRepository.findFavorites().stream()
            .map(storeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<StoreDTO> getStoresByLocation(Location userLocation) {
        List<Store> stores = storeRepository.findAllWithEagerRelationships();
        Collections.sort(stores, new LocationComparator(userLocation));
        return storeMapper.toDto(stores);
    }
}
