package com.example.zipcodes.infra.db.jpa.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the TOWN_AREAS database table.
 * 
 */
@Entity
@Table(schema = "ZIP_CODE", name = "TOWN_AREAS")
@NamedQuery(name = "TownAreaResource.findAll", query = "SELECT t FROM TownAreaResource t")
public class TownAreaResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "CITY_NAME_KANA")
    private String cityNameKana;

    @Id
    private Long id;

    @Column(name = "JAPANESE_LOCAL_GOVERMENT_CODE")
    private String japaneseLocalGovermentCode;

    @Column(name = "PREFECTURE_CODE")
    private String prefectureCode;

    @Column(name = "PREFECTURE_NAME")
    private String prefectureName;

    @Column(name = "PREFECTURE_NAME_KANA")
    private String prefectureNameKana;

    @Column(name = "TOWN_NAME")
    private String townName;

    @Column(name = "TOWN_NAME_KANA")
    private String townNameKana;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    public TownAreaResource() {
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameKana() {
        return this.cityNameKana;
    }

    public void setCityNameKana(String cityNameKana) {
        this.cityNameKana = cityNameKana;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJapaneseLocalGovermentCode() {
        return this.japaneseLocalGovermentCode;
    }

    public void setJapaneseLocalGovermentCode(String japaneseLocalGovermentCode) {
        this.japaneseLocalGovermentCode = japaneseLocalGovermentCode;
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

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownNameKana() {
        return this.townNameKana;
    }

    public void setTownNameKana(String townNameKana) {
        this.townNameKana = townNameKana;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}