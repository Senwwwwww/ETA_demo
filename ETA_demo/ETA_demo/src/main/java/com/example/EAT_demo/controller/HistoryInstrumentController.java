package com.example.EAT_demo.controller;


import com.example.EAT_demo.aop.RecordBehavior;
import com.example.EAT_demo.pojo.HistoryInstrument;
import com.example.EAT_demo.service.HistoryInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/history-instruments")
public class HistoryInstrumentController {

    @Autowired
    private HistoryInstrumentService historyInstrumentService;

    // 创建历史记录
    @PostMapping
    @RecordBehavior("进行巡检")
    @CrossOrigin
    public ResponseEntity<HistoryInstrument> createHistoryInstrument(@RequestBody HistoryInstrument historyInstrument) {
        try {
            HistoryInstrument saved = historyInstrumentService.save(historyInstrument);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据ID查询历史记录
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<HistoryInstrument> getHistoryInstrumentById(@PathVariable Long id) {
        Optional<HistoryInstrument> historyInstrument = historyInstrumentService.findById(id);
        return historyInstrument.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 获取所有历史记录
    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<HistoryInstrument>> getAllHistoryInstruments() {
        try {
            List<HistoryInstrument> historyInstruments = historyInstrumentService.findAll();
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 分页获取所有历史记录
    @GetMapping("/page")
    @CrossOrigin
    public ResponseEntity<Page<HistoryInstrument>> getAllHistoryInstruments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "historyInstrumentId") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        try {
            Sort sort = sortDir.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<HistoryInstrument> historyInstruments = historyInstrumentService.findAll(pageable);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 更新历史记录
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<HistoryInstrument> updateHistoryInstrument(
            @PathVariable Long id, @RequestBody HistoryInstrument historyInstrument) {
        if (!historyInstrumentService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            historyInstrument.setHistoryInstrumentId(id);
            HistoryInstrument updated = historyInstrumentService.save(historyInstrument);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 删除历史记录
    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<HttpStatus> deleteHistoryInstrument(@PathVariable Long id) {
        if (!historyInstrumentService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            historyInstrumentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据仪器ID查询历史记录
    @GetMapping("/instrument/{instrumentId}")
    @CrossOrigin
    public ResponseEntity<List<HistoryInstrument>> getHistoryByInstrumentId(@PathVariable Long instrumentId) {
        try {
            List<HistoryInstrument> historyInstruments = historyInstrumentService.findByInstrumentId(instrumentId);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 分页查询指定仪器的历史记录
    @GetMapping("/instrument/{instrumentId}/page")
    @CrossOrigin
    public ResponseEntity<Page<HistoryInstrument>> getHistoryByInstrumentId(
            @PathVariable Long instrumentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "time") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        try {
            Sort sort = sortDir.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<HistoryInstrument> historyInstruments = historyInstrumentService.findByInstrumentId(instrumentId, pageable);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据员工ID查询历史记录
    @GetMapping("/employee/{employeeId}")
    @CrossOrigin
    public ResponseEntity<List<HistoryInstrument>> getHistoryByEmployeeId(@PathVariable Long employeeId) {
        try {
            List<HistoryInstrument> historyInstruments = historyInstrumentService.findByEmployeeId(employeeId);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 分页查询指定员工的历史记录
    @GetMapping("/employee/{employeeId}/page")
    @CrossOrigin
    public ResponseEntity<Page<HistoryInstrument>> getHistoryByEmployeeId(
            @PathVariable Long employeeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "time") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        try {
            Sort sort = sortDir.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<HistoryInstrument> historyInstruments = historyInstrumentService.findByEmployeeId(employeeId, pageable);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据状态查询历史记录
    @GetMapping("/status/{status}")
    @CrossOrigin
    public ResponseEntity<List<HistoryInstrument>> getHistoryByStatus(@PathVariable String status) {
        try {
            List<HistoryInstrument> historyInstruments = historyInstrumentService.findByStatus(status);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据仪器ID和状态查询历史记录
    @GetMapping("/instrument/{instrumentId}/status/{status}")
    @CrossOrigin
    public ResponseEntity<List<HistoryInstrument>> getHistoryByInstrumentIdAndStatus(
            @PathVariable Long instrumentId, @PathVariable String status) {
        try {
            List<HistoryInstrument> historyInstruments =
                    historyInstrumentService.findByInstrumentIdAndStatus(instrumentId, status);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据时间范围查询历史记录
    @GetMapping("/time-range")
    @CrossOrigin
    public ResponseEntity<List<HistoryInstrument>> getHistoryByTimeRange(
            @RequestParam String startTime, @RequestParam String endTime) {
        try {
            Time start = Time.valueOf(startTime);
            Time end = Time.valueOf(endTime);
            List<HistoryInstrument> historyInstruments =
                    historyInstrumentService.findByTimeBetween(start, end);
            return new ResponseEntity<>(historyInstruments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}