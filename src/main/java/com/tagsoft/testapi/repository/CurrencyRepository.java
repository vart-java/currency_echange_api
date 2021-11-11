package com.tagsoft.testapi.repository;


import com.tagsoft.testapi.model.entity.CurrencyExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchangeEntity, Long> {

    @Query("select c from CurrencyExchangeEntity c where c.loadingDate between :start and :end")
    List<CurrencyExchangeEntity> findTest(Timestamp start, Timestamp end);
}
