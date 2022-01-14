package com.mountain.mountain.domain.likedmountain.dao;


import com.mountain.mountain.domain.likedmountain.model.Likedmountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedMountainRepository extends JpaRepository<Likedmountain,Long> {
}
