package com.ex.repo.voucher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.entity.Particular;

@Repository
public interface ParticularRepo extends JpaRepository<Particular, Long> {

}
