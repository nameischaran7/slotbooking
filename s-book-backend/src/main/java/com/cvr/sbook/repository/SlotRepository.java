package com.cvr.sbook.repository;

import com.cvr.sbook.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByVendorId(Long vendorId);
}