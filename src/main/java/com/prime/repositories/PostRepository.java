package com.prime.repositories;

import com.prime.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prime.domain.SystemUser;
import org.springframework.data.domain.Pageable;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Post p, Follow f WHERE p.dono = f.seguindo AND f.dono = :dono")
    Page<Post> feedUsuario(@Param("dono") SystemUser dono, Pageable pageRequest);
}
