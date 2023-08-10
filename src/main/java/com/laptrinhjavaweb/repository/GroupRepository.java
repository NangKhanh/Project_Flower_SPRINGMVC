package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
