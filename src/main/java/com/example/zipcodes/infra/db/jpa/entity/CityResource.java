package com.example.zipcodes.infra.db.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the CITIES database table.
 * 
 */
@Entity
@Table(schema = "ZIP_CODE", name = "CITIES")
@Getter
@Setter
public class CityResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CITY_NAME", insertable = false, updatable = false)
	private String cityName;

	@Column(name = "CITY_NAME_KANA", insertable = false, updatable = false)
	private String cityNameKana;

	@Id
	@Column(name = "JAPANESE_LOCAL_GOVERMENT_CODE", insertable = false, updatable = false)
	private String japaneseLocalGovermentCode;

	@Column(name = "PREFECTURE_CODE", insertable = false, updatable = false)
	private String prefectureCode;

	@Column(name = "PREFECTURE_NAME", insertable = false, updatable = false)
	private String prefectureName;

	@Column(name = "PREFECTURE_NAME_KANA", insertable = false, updatable = false)
	private String prefectureNameKana;
}