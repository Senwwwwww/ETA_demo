package com.example.EAT_demo.service;


import com.example.EAT_demo.dao.HistoryInstrumentRepository;
import com.example.EAT_demo.pojo.HistoryInstrument;
import com.example.EAT_demo.service.HistoryInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoryInstrumentServiceImpl implements HistoryInstrumentService {

    @Autowired
    private HistoryInstrumentRepository historyInstrumentRepository;

    @Override
    public HistoryInstrument save(HistoryInstrument historyInstrument) {
        return historyInstrumentRepository.save(historyInstrument);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistoryInstrument> findById(Long id) {
        return historyInstrumentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryInstrument> findAll() {
        return historyInstrumentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HistoryInstrument> findAll(Pageable pageable) {
        return historyInstrumentRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        historyInstrumentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return historyInstrumentRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryInstrument> findByInstrumentId(Long instrumentId) {
        return historyInstrumentRepository.findByInstrumentInstrumentId(instrumentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryInstrument> findByEmployeeId(Long employeeId) {
        return historyInstrumentRepository.findByEmployeeEmployeeId(employeeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryInstrument> findByStatus(String status) {
        return historyInstrumentRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryInstrument> findByInstrumentIdAndStatus(Long instrumentId, String status) {
        return historyInstrumentRepository.findByInstrumentInstrumentIdAndStatus(instrumentId, status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryInstrument> findByTimeBetween(Time startTime, Time endTime) {
        return historyInstrumentRepository.findByTimeBetween(startTime, endTime);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HistoryInstrument> findByInstrumentId(Long instrumentId, Pageable pageable) {
        return historyInstrumentRepository.findByInstrumentInstrumentId(instrumentId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HistoryInstrument> findByEmployeeId(Long employeeId, Pageable pageable) {
        return historyInstrumentRepository.findByEmployeeEmployeeId(employeeId, pageable);
    }
}
