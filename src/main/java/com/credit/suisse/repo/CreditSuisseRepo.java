package com.credit.suisse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.suisse.entity.CreditSuissesServerLog;

@Repository
public interface CreditSuisseRepo extends JpaRepository<CreditSuissesServerLog, Integer>{

}
