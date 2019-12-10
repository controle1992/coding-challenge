package com.unitedremote.store.service.util;

import com.unitedremote.store.domain.Store;

import java.util.Comparator;

public class LocationComparator implements Comparator<Store> {
    Location origin;

    public LocationComparator(Location origin){
        this.origin= origin;
    }
    @Override
    public int compare(Store first, Store second) {
        Location left = new Location(first.getLongitude(), first.getLatitude());
        Location right = new Location(second.getLongitude(), second.getLatitude());
        return Double.compare(origin.distanceTo(left), origin.distanceTo(right));    }
}
