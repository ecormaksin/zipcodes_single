package com.example.zipcodes.infra.db.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the PREFECTURES database table.
 * 
 */
@Entity
@Table(schema = "ZIP_CODE", name = "PREFECTURES")
@Getter
@Setter
public class PrefectureResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PREFECTURE_CODE", insertable = false, updatable = false)
	private String prefectureCode;

	@Column(name = "PREFECTURE_NAME", insertable = false, updatable = false)
	private String prefectureName;

	@Column(name = "PREFECTURE_NAME_KANA", insertable = false, updatable = false)
	private String prefectureNameKana;
}