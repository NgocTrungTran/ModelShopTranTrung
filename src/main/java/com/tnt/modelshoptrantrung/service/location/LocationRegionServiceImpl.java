package com.tnt.modelshoptrantrung.service.location;

import com.tnt.modelshoptrantrung.model.LocationRegion;
import com.tnt.modelshoptrantrung.repository.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LocationRegionServiceImpl implements LocationRegionService {
    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public Iterable<LocationRegion> findAll() {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return Optional.empty ();
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return locationRegionRepository.save ( locationRegion );
    }

    @Override
    public LocationRegion getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
