package com.example.EAT_demo.controller;


import com.example.EAT_demo.Util.IDutils;
import com.example.EAT_demo.pojo.Instrument;
import com.example.EAT_demo.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
@CrossOrigin(origins = "*") // 允许跨域请求
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    /**
     * 根据ID获取器械信息
     */
    @GetMapping("/{instrumentId}")
    public ResponseEntity<?> getInstrumentById(@PathVariable Long instrumentId) {
        try {
            Optional<Instrument> instrument = instrumentService.getInstrumentById(instrumentId);

            if (instrument.isPresent()) {
                return ResponseEntity.ok(instrument.get());
            } else {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("message", "Instrument not found with id: " + instrumentId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error retrieving instrument: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * 获取所有器械
     */
    @GetMapping
    public ResponseEntity<List<Instrument>> getAllInstruments() {
        try {
            List<Instrument> instruments = instrumentService.getAllInstruments();
            return ResponseEntity.ok(instruments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 创建新器械
     */
    @PostMapping
    public ResponseEntity<?> createInstrument(@RequestBody Instrument instrument) {
        try {
            Instrument savedInstrument = instrumentService.saveInstrument(instrument);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInstrument);
        } catch (IllegalArgumentException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error creating instrument: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * 更新器械状态
     */
    @PutMapping("/{instrumentId}/status")
    public ResponseEntity<Map<String, Object>> updateInstrumentStatus(
            @PathVariable Long instrumentId,
            @RequestBody Map<String, String> request) {

        Map<String, Object> response = new HashMap<>();

        try {
            String status = request.get("status");
            if (status == null || status.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Status cannot be empty");
                return ResponseEntity.badRequest().body(response);
            }

            boolean success = instrumentService.updateInstrumentStatus(instrumentId, status.trim());
            if (success) {
                response.put("success", true);
                response.put("message", "Status updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Instrument not found or status unchanged");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error updating status: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * 更新器械信息
     */
    @PutMapping("/{instrumentId}")
    public ResponseEntity<?> updateInstrument(
            @PathVariable Long instrumentId,
            @RequestBody Instrument instrumentDetails) {

        instrumentDetails.setInstrumentId(instrumentId); // 显式设置 ID，用于更新
        Instrument updatedInstrument = instrumentService.saveInstrument(instrumentDetails);
        return ResponseEntity.ok(updatedInstrument);
    }

//        try {
//            if (!instrumentService.existsInstrument(instrumentId)) {
//                Map<String, Object> error = new HashMap<>();
//                error.put("success", false);
//                error.put("message", "Instrument not found with id: " + instrumentId);
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//            }
//
//            instrument.setInstrumentId(instrumentId);
//            Instrument updatedInstrument = instrumentService.saveInstrument(instrument);
//            return ResponseEntity.ok(updatedInstrument);
//        } catch (IllegalArgumentException e) {
//            Map<String, Object> error = new HashMap<>();
//            error.put("success", false);
//            error.put("message", e.getMessage());
//            return ResponseEntity.badRequest().body(error);
//        } catch (Exception e) {
//            Map<String, Object> error = new HashMap<>();
//            error.put("success", false);
//            error.put("message", "Error updating instrument: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//        }
    /**
     * 删除器械
     */
    @DeleteMapping("/{instrumentId}")
    public ResponseEntity<Map<String, Object>> deleteInstrument(@PathVariable Long instrumentId) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = instrumentService.deleteInstrument(instrumentId);

            if (success) {
                response.put("success", true);
                response.put("message", "Instrument deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Instrument not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error deleting instrument: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * 根据名称搜索器械
     */
    @GetMapping("/search")
    public ResponseEntity<List<Instrument>> searchInstruments(@RequestParam String name) {
        try {
            List<Instrument> instruments = instrumentService.searchInstrumentsByName(name);
            return ResponseEntity.ok(instruments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 根据状态获取器械列表
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Instrument>> getInstrumentsByStatus(@PathVariable String status) {
        try {
            List<Instrument> instruments = instrumentService.getInstrumentsByStatus(status);
            return ResponseEntity.ok(instruments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 获取状态统计信息
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getStatusStatistics() {
        try {
            Map<String, Long> statistics = instrumentService.getStatusStatistics();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 批量更新器械状态
     */
    @PutMapping("/batch/status")
    public ResponseEntity<Map<String, Object>> batchUpdateStatus(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            @SuppressWarnings("unchecked")
            List<Long> instrumentIds = (List<Long>) request.get("instrumentIds");
            String status = (String) request.get("status");

            if (instrumentIds == null || instrumentIds.isEmpty()) {
                response.put("success", false);
                response.put("message", "Instrument IDs cannot be empty");
                return ResponseEntity.badRequest().body(response);
            }

            if (status == null || status.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Status cannot be empty");
                return ResponseEntity.badRequest().body(response);
            }

            boolean success = instrumentService.batchUpdateStatus(instrumentIds, status.trim());
            if (success) {
                response.put("success", true);
                response.put("message", "Batch status update completed");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "No instruments were updated");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error in batch update: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}