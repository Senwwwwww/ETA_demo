package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instrument")
@Entity
public class Instrument {
    //设置id自增
    @Id
    @Column(name = "instrument_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long instrumentId;

    @Column(name = "name")
    private String name;
    @Column(name = "detail")
    private String detail;
    @Column(name = "status")
    private String status;

    // 添加与HistoryInstrument的一对多关系，配置级联删除
    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoryInstrument> historyInstruments;
}
