package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "bioinformatics")

public class Bioinformatics {
    @Id
    @Column(name = "bioinfo_id")
    private long bioinfoId = IDutils.getID();;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

// 头像图片
    @Lob
    @Column(name = "face", columnDefinition = "LONGBLOB")
    private byte[] face;
}
