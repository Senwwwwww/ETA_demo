package com.example.EAT_demo.service;

import com.example.EAT_demo.pojo.Instrument;
import com.example.EAT_demo.dao.InstrumentRepository;
import com.example.EAT_demo.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    private InstrumentRepository instrumentRepository;

    // 定义合法的状态值
    private static final Set<String> VALID_STATUSES = Set.of("normal", "repairing", "broken", "disabled");

    @Override
    public Optional<Instrument> getInstrumentById(Long instrumentId) {
        return instrumentRepository.findByInstrumentId(instrumentId);
    }

    @Override
    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }


    @Override
    public boolean updateInstrumentStatus(Long instrumentId, String status) {
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        int updatedRows = instrumentRepository.updateInstrumentStatus(instrumentId, status);
        return updatedRows > 0;
    }

    @Override
    public boolean deleteInstrument(Long instrumentId) {
        if (instrumentRepository.existsByInstrumentId(instrumentId)) {
            Optional<Instrument> instrument = instrumentRepository.findByInstrumentId(instrumentId);
            if (instrument.isPresent()) {
                instrumentRepository.delete(instrument.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    @Override
    public List<Instrument> searchInstrumentsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return instrumentRepository.findByNameContaining(name.trim());
    }

    @Override
    public List<Instrument> getInstrumentsByStatus(String status) {
        if (!isValidStatus(status)) {
            return Collections.emptyList();
        }
        return instrumentRepository.findByStatus(status);
    }

    @Override
    public boolean existsInstrument(Long instrumentId) {
        return instrumentRepository.existsByInstrumentId(instrumentId);
    }

    @Override
    public Map<String, Long> getStatusStatistics() {
        List<Object[]> results = instrumentRepository.getStatusStatistics();
        Map<String, Long> statistics = new HashMap<>();

        for (Object[] result : results) {
            String status = (String) result[0];
            Long count = (Long) result[1];
            statistics.put(status, count);
        }

        // 确保所有状态都有值（即使为0）
        for (String status : VALID_STATUSES) {
            statistics.putIfAbsent(status, 0L);
        }

        return statistics;
    }

    @Override
    public boolean batchUpdateStatus(List<Long> instrumentIds, String status) {
        if (!isValidStatus(status) || instrumentIds == null || instrumentIds.isEmpty()) {
            return false;
        }

        int successCount = 0;
        for (Long instrumentId : instrumentIds) {
            if (updateInstrumentStatus(instrumentId, status)) {
                successCount++;
            }
        }

        return successCount > 0;
    }

    @Override
    public boolean isValidStatus(String status) {
        return status != null && VALID_STATUSES.contains(status.toLowerCase());
    }
}
