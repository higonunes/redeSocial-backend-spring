package com.prime.repositories;

import com.prime.domain.PostAviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostAvisoRepository extends JpaRepository<PostAviso, Long> {
}
