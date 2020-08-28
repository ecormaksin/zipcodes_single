package com.example.zipcodes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


/**
 * The persistent class for the PREFECTURES database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "prefectureCode",
    "prefectureName",
    "prefectureNameKana"
})
@Entity
@Table(name="PREFECTURES")
@Data
public class Prefecture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PREFECTURE_CODE", insertable=false, updatable=false)
    @JsonProperty("prefectureCode")
	private String prefectureCode;

	@Column(name="PREFECTURE_NAME")
    @JsonProperty("prefectureName")
	private String prefectureName;

	@Column(name="PREFECTURE_NAME_KANA")
	private String prefectureNameKana;
}