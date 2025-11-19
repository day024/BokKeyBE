package com.BokKey.BokKey.api.store;

import com.BokKey.BokKey.api.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByArea(String area);

    List<Store> findByIsLive(boolean isLive);

    @Query("SELECT s FROM Store s JOIN s.Target st WHERE st.province = :Target")
    List<Store> findBySupportType(@Param("supportType") String supportType);

    List<Store> findByNameContaining(String name);
}
