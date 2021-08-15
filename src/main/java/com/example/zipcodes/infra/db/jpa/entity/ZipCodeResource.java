package com.example.zipcodes.infra.db.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "ZIP_CODE", name = "ZIP_CODES")
@Getter
@Setter
public class ZipCodeResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zipcode_id_seq")
	@SequenceGenerator(name = "zipcode_id_seq", sequenceName = "ZIP_CODES_SEQ", allocationSize = 1)
	private BigInteger id;

	/** 全国地方公共団体コード JIS X0401、X0402 */
	@Column(name = "JAPANESE_LOCAL_GOVERMENT_CODE", nullable = false, length = 5)
	private String japaneseLocalGovermentCode;

	/** 旧郵便番号 */
	@Column(name = "OLD_ZIP_CODE", nullable = false, length = 5)
	private String oldZipCode;

	/** 郵便番号 */
	@Column(name = "ZIP_CODE", nullable = false, length = 7)
	private String zipCode;

	/** 都道府県名カナ 半角カタカナ（コード順に掲載） */
	@Column(name = "PREFECTURE_NAME_KANA", nullable = false, length = 10)
	private String prefectureNameKana;

	/** 市区町村名カナ 半角カタカナ（コード順に掲載） */
	@Column(name = "CITY_NAME_KANA", nullable = false, length = 30)
	private String cityNameKana;

	/** 町域名カナ 半角カタカナ（五十音順に掲載） */
	@Column(name = "TOWN_NAME_KANA", nullable = false, length = 100)
	private String townNameKana;

	/** 都道府県名 漢字（コード順に掲載） */
	@Column(name = "PREFECTURE_NAME", nullable = false, length = 5)
	private String prefectureName;

	/** 市区町村名 漢字（コード順に掲載） */
	@Column(name = "CITY_NAME", nullable = false, length = 20)
	private String cityName;

	/** 町域名 漢字（五十音順に掲載） */
	@Column(name = "TOWN_NAME", nullable = false, length = 20)
	private String townName;

	/** 複数番号付与町域フラグ 一町域が二以上の郵便番号で表される場合の表示（「1」は該当、「0」は該当せず） */
	@Column(name = "TOWN_DEVIDED_FLAG", nullable = false, length = 1)
	private String townDevidedFlag;

	/** 小字毎番号付与フラグ 小字毎に番地が起番されている町域の表示（「1」は該当、「0」は該当せず） */
	@Column(name = "ISSUED_PER_KOAZA_FLAG", nullable = false, length = 1)
	private String issuedPerKoazaFlag;

	/** 丁目保有町域フラグ 丁目を有する町域の場合の表示（「1」は該当、「0」は該当せず） */
	@Column(name = "CHOME_TOWN_FLAG", nullable = false, length = 1)
	private String chomeTownFlag;

	/** 複数町域保有フラグ 一つの郵便番号で二以上の町域を表す場合の表示（「1」は該当、「0」は該当せず） */
	@Column(name = "HAS_MULTIPLE_TOWN_FLAG", nullable = false, length = 1)
	private String hasMultipleTownFlag;

	/** 更新の表示 「0」は変更なし、「1」は変更あり、「2」廃止（廃止データのみ使用） */
	@Column(name = "UPDATE_DISPLAY_FLAG", nullable = false, length = 1)
	private String updateDisplayFlag;

	/**
	 * 変更理由
	 * 「0」は変更なし、「1」市政・区政・町政・分区・政令指定都市施行、「2」住居表示の実施、「3」区画整理、「4」郵便区調整等、「5」訂正、「6」廃止（廃止データのみ使用）
	 */
	@Column(name = "CHANGE_REASON_FLAG", nullable = false, length = 1)
	private String changeReasonFlag;
}
