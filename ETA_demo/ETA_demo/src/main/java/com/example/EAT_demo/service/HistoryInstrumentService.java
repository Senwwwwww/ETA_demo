
// ===== 2. Service 接口层 =====
// HistoryInstrumentService.java
package com.example.EAT_demo.service;

import com.example.EAT_demo.pojo.HistoryInstrument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
@Service
public interface HistoryInstrumentService {

    // 基本 CRUD 操作
    HistoryInstrument save(HistoryInstrument historyInstrument);

    Optional<HistoryInstrument> findById(Long id);

    List<HistoryInstrument> findAll();

    Page<HistoryInstrument> findAll(Pageable pageable);

    void deleteById(Long id);

    boolean existsById(Long id);

    // 业务查询方法
    List<HistoryInstrument> findByInstrumentId(Long instrumentId);

    List<HistoryInstrument> findByEmployeeId(Long employeeId);

    List<HistoryInstrument> findByStatus(String status);

    List<HistoryInstrument> findByInstrumentIdAndStatus(Long instrumentId, String status);

    List<HistoryInstrument> findByTimeBetween(Time startTime, Time endTime);

    // 分页查询
    Page<HistoryInstrument> findByInstrumentId(Long instrumentId, Pageable pageable);

    Page<HistoryInstrument> findByEmployeeId(Long employeeId, Pageable pageable);
}
