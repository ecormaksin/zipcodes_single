package com.example.zipcodes.infra.db.jpa.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the PREFECTURES database table.
 * 
 */
@Entity
@Table(schema = "ZIP_CODE", name = "PREFECTURES")
@NamedQuery(name = "PrefectureResource.findAll", query = "SELECT p FROM PrefectureResource p")
public class PrefectureResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PREFECTURE_CODE", insertable = false, updatable = false)
    private String prefectureCode;

    @Column(name = "PREFECTURE_NAME")
    private String prefectureName;

    @Column(name = "PREFECTURE_NAME_KANA")
    private String prefectureNameKana;

    public PrefectureResource() {
    }

    public String getPrefectureCode() {
        return this.prefectureCode;
    }

    public void setPrefectureCode(String prefectureCode) {
        this.prefectureCode = prefectureCode;
    }

    public String getPrefectureName() {
        return this.prefectureName;
    }

    public void setPrefectureName(String prefectureName) {
        this.prefectureName = prefectureName;
    }

    public String getPrefectureNameKana() {
        return this.prefectureNameKana;
    }

    public void setPrefectureNameKana(String prefectureNameKana) {
        this.prefectureNameKana = prefectureNameKana;
    }

}