package com.tnt.modelshoptrantrung.repository;

import com.tnt.modelshoptrantrung.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRegionRepository extends JpaRepository<LocationRegion, Long> {

}
