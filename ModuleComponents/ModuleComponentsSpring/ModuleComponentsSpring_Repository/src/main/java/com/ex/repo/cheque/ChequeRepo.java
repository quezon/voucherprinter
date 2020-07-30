package com.ex.repo.cheque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.entity.cheque.Cheque;

@Repository
public interface ChequeRepo extends JpaRepository<Cheque, Long> {
	
}
