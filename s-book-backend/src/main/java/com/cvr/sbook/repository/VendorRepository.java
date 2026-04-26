package com.cvr.sbook.repository;

import com.cvr.sbook.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    // This interface automatically gives you Save, Find, Delete, etc.
    Optional<Vendor> findByEmail(String email);
    List<Vendor> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrLocationContainingIgnoreCase(
            String name, String category, String location,Double pricePerHour
    );
}