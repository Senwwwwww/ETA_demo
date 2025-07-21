package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.HistoryInstrument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface HistoryInstrumentRepository extends JpaRepository<HistoryInstrument, Long> {

    // 根据仪器ID查询历史记录
    List<HistoryInstrument> findByInstrumentInstrumentId(Long instrumentId);

    // 根据员工ID查询历史记录
    List<HistoryInstrument> findByEmployeeEmployeeId(Long employeeId);

    // 根据状态查询
    List<HistoryInstrument> findByStatus(String status);

    // 根据仪器ID和状态查询
    List<HistoryInstrument> findByInstrumentInstrumentIdAndStatus(Long instrumentId, String status);

    // 根据时间范围查询
    @Query("SELECT h FROM HistoryInstrument h WHERE h.time BETWEEN :startTime AND :endTime")
    List<HistoryInstrument> findByTimeBetween(@Param("startTime") Time startTime, @Param("endTime") Time endTime);

    // 分页查询指定仪器的历史记录
    Page<HistoryInstrument> findByInstrumentInstrumentId(Long instrumentId, Pageable pageable);

    // 分页查询指定员工的历史记录
    Page<HistoryInstrument> findByEmployeeEmployeeId(Long employeeId, Pageable pageable);
}