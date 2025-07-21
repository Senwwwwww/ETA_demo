package com.example.EAT_demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history_instrument")
public class HistoryInstrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyInstrumentId;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    @JsonIgnoreProperties("historyInstruments")
    private Instrument instrument;

    @Column(name = "status")
    private String status;

    @Column(name = "time")
    private String time;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}