package com.example.EAT_demo.service;

// ===== 4. InstrumentService接口 =====


import com.example.EAT_demo.pojo.Instrument;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InstrumentService {

    /**
     * 根据ID获取器械信息
     */
    Optional<Instrument> getInstrumentById(Long instrumentId);

    /**
     * 保存器械信息
     */
    Instrument saveInstrument(Instrument instrument);

    /**
     * 更新器械状态
     */
    boolean updateInstrumentStatus(Long instrumentId, String status);

    /**
     * 删除器械
     */
    boolean deleteInstrument(Long instrumentId);

    /**
     * 获取所有器械
     */
    List<Instrument> getAllInstruments();

    /**
     * 根据名称搜索器械
     */
    List<Instrument> searchInstrumentsByName(String name);

    /**
     * 根据状态获取器械列表
     */
    List<Instrument> getInstrumentsByStatus(String status);

    /**
     * 检查器械是否存在
     */
    boolean existsInstrument(Long instrumentId);

    /**
     * 获取状态统计信息
     */
    Map<String, Long> getStatusStatistics();

    /**
     * 批量更新器械状态
     */
    boolean batchUpdateStatus(List<Long> instrumentIds, String status);

    /**
     * 验证状态是否合法
     */
    boolean isValidStatus(String status);
}