package com.unitedremote.store.service;

import com.unitedremote.store.domain.Store;
import com.unitedremote.store.repository.StoreRepository;
import com.unitedremote.store.repository.UserRepository;
import com.unitedremote.store.service.dto.StoreDTO;
import com.unitedremote.store.service.mapper.StoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Store}.
 */
@Service
@Transactional
public class StoreService {

    private final Logger log = LoggerFactory.getLogger(StoreService.class);

    private final StoreRepository storeRepository;

    private final StoreMapper storeMapper;


    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
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
        return storeRepository.findAll().stream()
            .map(storeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
        return storeRepository.findById(id)
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

}
