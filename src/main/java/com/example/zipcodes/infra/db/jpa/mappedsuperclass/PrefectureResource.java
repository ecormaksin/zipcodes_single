package com.example.zipcodes.infra.db.jpa.mappedsuperclass;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the PREFECTURES database table.
 * 
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrefectureResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private String prefectureCode;

	private String prefectureName;

	private String prefectureNameKana;
}