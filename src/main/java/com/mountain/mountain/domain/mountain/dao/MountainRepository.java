package com.mountain.mountain.domain.mountain.dao;

import com.mountain.mountain.domain.mountain.model.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MountainRepository extends JpaRepository<Mountain,Long> {
}
