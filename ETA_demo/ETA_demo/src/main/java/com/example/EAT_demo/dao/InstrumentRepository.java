package com.example.EAT_demo.dao;
import com.example.EAT_demo.pojo.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    /**
     * 根据器械ID查找器械
     */
    Optional<Instrument> findByInstrumentId(Long instrumentId);

    /**
     * 根据器械名称查找器械
     */
    List<Instrument> findByNameContaining(String name);

    /**
     * 根据状态查找器械
     */
    List<Instrument> findByStatus(String status);

    /**
     * 更新器械状态
     */
    @Modifying
    @Transactional
    @Query("UPDATE Instrument i SET i.status = :status WHERE i.instrumentId = :instrumentId")
    int updateInstrumentStatus(@Param("instrumentId") Long instrumentId, @Param("status") String status);

    /**
     * 检查器械是否存在
     */
    boolean existsByInstrumentId(Long instrumentId);

    /**
     * 根据状态统计器械数量
     */
    @Query("SELECT COUNT(i) FROM Instrument i WHERE i.status = :status")
    long countByStatus(@Param("status") String status);

    /**
     * 获取所有状态的统计信息
     */
    @Query("SELECT i.status, COUNT(i) FROM Instrument i GROUP BY i.status")
    List<Object[]> getStatusStatistics();
}
