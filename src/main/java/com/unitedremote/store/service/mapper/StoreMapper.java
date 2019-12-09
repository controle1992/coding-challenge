package com.unitedremote.store.service.mapper;

import com.unitedremote.store.domain.*;
import com.unitedremote.store.service.dto.StoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Store} and its DTO {@link StoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StoreMapper extends EntityMapper<StoreDTO, Store> {


    @Mapping(target = "removeUser", ignore = true)

    default Store fromId(Long id) {
        if (id == null) {
            return null;
        }
        Store store = new Store();
        store.setId(id);
        return store;
    }
}
