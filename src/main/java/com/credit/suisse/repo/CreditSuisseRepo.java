package com.credit.suisse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.suisse.entity.CreditSuisseEvents;

@Repository
public interface CreditSuisseRepo extends JpaRepository<CreditSuisseEvents, Integer>{

}
