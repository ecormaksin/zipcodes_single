-- Project Name : 郵便番号検索システム
-- Date/Time    : 2021/08/29 19:11:03
-- Author       : ecor
-- RDBMS Type   : Oracle Database
-- Application  : A5:SQL Mk-2

-- 事業所_個別_郵便番号_ワーク
CREATE TABLE OFFICE_ZIP_CODES_WORK (
  JIS_CODE_OF_LOCATION VARCHAR2(5)
  , OFFICE_NAME_KANA VARCHAR2(100 CHAR)
  , OFFICE_NAME VARCHAR2(160 CHAR)
  , PREFECTURE_NAME VARCHAR2(5 CHAR)
  , CITY_NAME VARCHAR2(20 CHAR)
  , TOWN_AREA_NAME VARCHAR2(50 CHAR)
  , REST_PART_OF_ADDRESS VARCHAR2(124 CHAR)
  , OFFICE_ZIP_CODE VARCHAR2(7)
  , OLD_ZIP_CODE VARCHAR2(5)
  , DISTRIBUTOR VARCHAR2(40 CHAR)
  , ZIP_CODE_TYPE VARCHAR2(1)
  , MULTIPLE_CODE_EXISTS_FLAG VARCHAR2(1)
  , REVISION_CODE VARCHAR2(1)
) ;

-- 郵便番号
CREATE TABLE ZIP_CODES (
  ID NUMBER(11,0) NOT NULL
  , JAPANESE_LOCAL_GOVERMENT_CODE VARCHAR2(5)
  , OLD_ZIP_CODE VARCHAR2(5)
  , ZIP_CODE VARCHAR2(7)
  , PREFECTURE_NAME_KANA VARCHAR2(10 CHAR)
  , CITY_NAME_KANA VARCHAR2(30 CHAR)
  , TOWN_NAME_KANA VARCHAR2(100 CHAR)
  , PREFECTURE_NAME VARCHAR2(5 CHAR)
  , CITY_NAME VARCHAR2(20 CHAR)
  , TOWN_NAME VARCHAR2(50 CHAR)
  , TOWN_DEVIDED_FLAG VARCHAR2(1)
  , ISSUED_PER_KOAZA_FLAG VARCHAR2(1)
  , CHOME_TOWN_FLAG VARCHAR2(1)
  , HAS_MULTIPLE_TOWN_FLAG VARCHAR2(1)
  , UPDATE_DISPLAY_FLAG VARCHAR2(1)
  , CHANGE_REASON_FLAG VARCHAR2(1)
  , CONSTRAINT ZIP_CODES_PKC PRIMARY KEY (ID)
) ;

ALTER TABLE ZIP_CODES ADD CONSTRAINT ZIP_CODES_IX1
  UNIQUE (JAPANESE_LOCAL_GOVERMENT_CODE,OLD_ZIP_CODE,ZIP_CODE,PREFECTURE_NAME_KANA,CITY_NAME_KANA,TOWN_NAME_KANA,PREFECTURE_NAME,CITY_NAME,TOWN_NAME,TOWN_DEVIDED_FLAG,ISSUED_PER_KOAZA_FLAG,CHOME_TOWN_FLAG,HAS_MULTIPLE_TOWN_FLAG,UPDATE_DISPLAY_FLAG,CHANGE_REASON_FLAG) ;

CREATE INDEX ZIP_CODES_IX2
  ON ZIP_CODES(JAPANESE_LOCAL_GOVERMENT_CODE);

CREATE INDEX ZIP_CODES_IX3
  ON ZIP_CODES(ZIP_CODE);

CREATE INDEX ZIP_CODES_IX4
  ON ZIP_CODES(PREFECTURE_NAME);

CREATE INDEX ZIP_CODES_IX5
  ON ZIP_CODES(CITY_NAME);

CREATE INDEX ZIP_CODES_IX6
  ON ZIP_CODES(PREFECTURE_NAME_KANA);

CREATE INDEX ZIP_CODES_IX7
  ON ZIP_CODES(CITY_NAME_KANA);

-- 郵便番号_ワーク
CREATE TABLE ZIP_CODES_WORK (
  JAPANESE_LOCAL_GOVERMENT_CODE VARCHAR2(5)
  , OLD_ZIP_CODE VARCHAR2(5)
  , ZIP_CODE VARCHAR2(7)
  , PREFECTURE_NAME_KANA VARCHAR2(10 CHAR)
  , CITY_NAME_KANA VARCHAR2(30 CHAR)
  , TOWN_NAME_KANA VARCHAR2(100 CHAR)
  , PREFECTURE_NAME VARCHAR2(5 CHAR)
  , CITY_NAME VARCHAR2(20 CHAR)
  , TOWN_NAME VARCHAR2(50 CHAR)
  , TOWN_DEVIDED_FLAG VARCHAR2(1)
  , ISSUED_PER_KOAZA_FLAG VARCHAR2(1)
  , CHOME_TOWN_FLAG VARCHAR2(1)
  , HAS_MULTIPLE_TOWN_FLAG VARCHAR2(1)
  , UPDATE_DISPLAY_FLAG VARCHAR2(1)
  , CHANGE_REASON_FLAG VARCHAR2(1)
) ;

-- 市区町村
CREATE VIEW CITIES AS 
SELECT DISTINCT
  SUBSTR(JAPANESE_LOCAL_GOVERMENT_CODE, 1, 2) AS PREFECTURE_CODE -- 都道府県コード
  , JAPANESE_LOCAL_GOVERMENT_CODE -- 地方公共団体コード
  , PREFECTURE_NAME -- 都道府県名
  , CITY_NAME -- 市区町村名
  , PREFECTURE_NAME_KANA -- 都道府県名ｶﾅ
  , CITY_NAME_KANA -- 市区町村名ｶﾅ
FROM
  ZIP_CODES
WHERE
  UPDATE_DISPLAY_FLAG IN ('0', '1')
;

-- 都道府県
CREATE VIEW PREFECTURES AS 
SELECT DISTINCT
  SUBSTR(JAPANESE_LOCAL_GOVERMENT_CODE, 1, 2) AS PREFECTURE_CODE -- 都道府県コード
  , PREFECTURE_NAME -- 都道府県名
  , PREFECTURE_NAME_KANA -- 都道府県名ｶﾅ
FROM
  ZIP_CODES
WHERE
  UPDATE_DISPLAY_FLAG IN ('0', '1')
;

-- 町域
CREATE VIEW TOWN_AREAS AS 
SELECT
    ROW_NUMBER() OVER ( 
        ORDER BY
            PREFECTURE_CODE
            , JAPANESE_LOCAL_GOVERMENT_CODE
            , ZIP_CODE
            , PREFECTURE_NAME
            , CITY_NAME
            , TOWN_NAME
            , PREFECTURE_NAME_KANA
            , CITY_NAME_KANA
            , TOWN_NAME_KANA 
    ) AS ID
    , PREFECTURE_CODE                           -- 都道府県コード
    , JAPANESE_LOCAL_GOVERMENT_CODE             -- 地方公共団体コード
    , ZIP_CODE                                  -- 郵便番号
    , PREFECTURE_NAME                           -- 都道府県名
    , CITY_NAME                                 -- 市区町村名
    , TOWN_NAME                                 -- 町域名
    , PREFECTURE_NAME_KANA                      -- 都道府県名ｶﾅ
    , CITY_NAME_KANA                            -- 市区町村名ｶﾅ
    , TOWN_NAME_KANA                            -- 町域名ｶﾅ
FROM
    ( 
        SELECT
            PREFECTURE_CODE
            , JAPANESE_LOCAL_GOVERMENT_CODE
            , ZIP_CODE
            , PREFECTURE_NAME
            , CITY_NAME
            , TOWN_NAME
            , PREFECTURE_NAME_KANA
            , CITY_NAME_KANA
            , TOWN_NAME_KANA 
        FROM
            ( 
                SELECT
                    PREFECTURE_CODE
                    , JAPANESE_LOCAL_GOVERMENT_CODE
                    , ZIP_CODE
                    , PREFECTURE_NAME
                    , CITY_NAME
                    , CASE 
                        WHEN NVL(TOWN_NAME, '') = '以下に掲載がない場合' 
                            THEN '' 
                        ELSE TOWN_NAME 
                        END AS TOWN_NAME
                    , PREFECTURE_NAME_KANA
                    , CITY_NAME_KANA
                    , CASE 
                        WHEN NVL(TOWN_NAME_KANA, '') = 'ｲｶﾆｹｲｻｲｶﾞﾅｲﾊﾞｱｲ' 
                            THEN '' 
                        ELSE TOWN_NAME_KANA 
                        END AS TOWN_NAME_KANA 
                FROM
                    ( 
                        SELECT
                            SUBSTR(JAPANESE_LOCAL_GOVERMENT_CODE, 1, 2) AS PREFECTURE_CODE
                            , JAPANESE_LOCAL_GOVERMENT_CODE
                            , ZIP_CODE
                            , PREFECTURE_NAME
                            , CITY_NAME
                            , CASE 
                                WHEN INSTR(TOWN_NAME, '（') > 0 
                                AND ( 
                                    INSTR(TOWN_NAME, '）') = 0 
                                    OR INSTR(TOWN_NAME, '（') < INSTR(TOWN_NAME, '）')
                                ) 
                                    THEN SUBSTR(TOWN_NAME, 1, INSTR(TOWN_NAME, '（') - 1) 
                                WHEN INSTR(TOWN_NAME, '）') > 0 
                                OR INSTR(TOWN_NAME, '、') > 0 
                                    THEN NULL 
                                ELSE TOWN_NAME 
                                END AS TOWN_NAME
                            , PREFECTURE_NAME_KANA
                            , CITY_NAME_KANA
                            , CASE 
                                WHEN INSTR(TOWN_NAME_KANA, '(') > 0 
                                AND ( 
                                    INSTR(TOWN_NAME_KANA, ')') = 0 
                                    OR INSTR(TOWN_NAME_KANA, '(') < INSTR(TOWN_NAME_KANA, ')')
                                ) 
                                    THEN SUBSTR( 
                                    TOWN_NAME_KANA
                                    , 1
                                    , INSTR(TOWN_NAME_KANA, '(') - 1
                                ) 
                                WHEN INSTR(TOWN_NAME_KANA, ')') > 0 
                                OR INSTR(TOWN_NAME_KANA, '、') > 0 
                                    THEN NULL 
                                ELSE TOWN_NAME_KANA 
                                END AS TOWN_NAME_KANA 
                        FROM
                            ZIP_CODES 
                        WHERE
                            UPDATE_DISPLAY_FLAG IN ('0', '1')
                    ) SQ_A 
                WHERE
                    TOWN_NAME IS NOT NULL
            ) SQ_B 
        GROUP BY
            PREFECTURE_CODE
            , JAPANESE_LOCAL_GOVERMENT_CODE
            , ZIP_CODE
            , PREFECTURE_NAME
            , CITY_NAME
            , TOWN_NAME
            , PREFECTURE_NAME_KANA
            , CITY_NAME_KANA
            , TOWN_NAME_KANA
    ) SQ_C

;

COMMENT ON TABLE CITIES IS '市区町村';
COMMENT ON COLUMN CITIES.PREFECTURE_CODE IS '都道府県コード';
COMMENT ON COLUMN CITIES.JAPANESE_LOCAL_GOVERMENT_CODE IS '地方公共団体コード';
COMMENT ON COLUMN CITIES.PREFECTURE_NAME IS '都道府県名';
COMMENT ON COLUMN CITIES.CITY_NAME IS '市区町村名';
COMMENT ON COLUMN CITIES.PREFECTURE_NAME_KANA IS '都道府県名ｶﾅ';
COMMENT ON COLUMN CITIES.CITY_NAME_KANA IS '市区町村名ｶﾅ';

COMMENT ON TABLE OFFICE_ZIP_CODES_WORK IS '事業所_個別_郵便番号_ワーク	 郵便局が提供している大口事業所個別の郵便番号データ
https://www.post.japanpost.jp/zipcode/dl/jigyosyo/readme.html';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.JIS_CODE_OF_LOCATION IS '大口事業所の所在地のJISコード';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.OFFICE_NAME_KANA IS '大口事業所名カナ';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.OFFICE_NAME IS '大口事業所名';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.PREFECTURE_NAME IS '都道府県名';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.CITY_NAME IS '市区町村名';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.TOWN_AREA_NAME IS '町域名';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.REST_PART_OF_ADDRESS IS '小字名_丁目_番地_等';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.OFFICE_ZIP_CODE IS '大口事業所個別番号';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.OLD_ZIP_CODE IS '旧郵便番号';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.DISTRIBUTOR IS '取扱局';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.ZIP_CODE_TYPE IS '個別番号種別	 個別番号の種別の表示 「0」大口事業所 「1」私書箱';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.MULTIPLE_CODE_EXISTS_FLAG IS '複数番号有無フラグ	 複数番号の有無 「0」複数番号無し 「1」複数番号を設定している場合の個別番号の1 「2」複数番号を設定している場合の個別番号の2 「3」複数番号を設定している場合の個別番号の3  一つの事業所が同一種別の個別番号を複数持つ場合に複数番号を設定しているものとします。 従って、一つの事業所で大口事業所、私書箱の個別番号をそれぞれ一つづつ設定している場合は「0」となります。';
COMMENT ON COLUMN OFFICE_ZIP_CODES_WORK.REVISION_CODE IS '修正コード	 修正コード 「0」修正なし 「1」新規追加 「5」廃止';

COMMENT ON TABLE PREFECTURES IS '都道府県';
COMMENT ON COLUMN PREFECTURES.PREFECTURE_CODE IS '都道府県コード';
COMMENT ON COLUMN PREFECTURES.PREFECTURE_NAME IS '都道府県名';
COMMENT ON COLUMN PREFECTURES.PREFECTURE_NAME_KANA IS '都道府県名ｶﾅ';

COMMENT ON TABLE TOWN_AREAS IS '町域';
COMMENT ON COLUMN TOWN_AREAS.ID IS 'ID';
COMMENT ON COLUMN TOWN_AREAS.PREFECTURE_CODE IS '都道府県コード';
COMMENT ON COLUMN TOWN_AREAS.JAPANESE_LOCAL_GOVERMENT_CODE IS '地方公共団体コード';
COMMENT ON COLUMN TOWN_AREAS.ZIP_CODE IS '郵便番号';
COMMENT ON COLUMN TOWN_AREAS.PREFECTURE_NAME IS '都道府県名';
COMMENT ON COLUMN TOWN_AREAS.CITY_NAME IS '市区町村名';
COMMENT ON COLUMN TOWN_AREAS.TOWN_NAME IS '町域名';
COMMENT ON COLUMN TOWN_AREAS.PREFECTURE_NAME_KANA IS '都道府県名ｶﾅ';
COMMENT ON COLUMN TOWN_AREAS.CITY_NAME_KANA IS '市区町村名ｶﾅ';
COMMENT ON COLUMN TOWN_AREAS.TOWN_NAME_KANA IS '町域名ｶﾅ';

COMMENT ON TABLE ZIP_CODES IS '郵便番号	 「ZIP_CODES_WORK」テーブルからID（連番）を付与してシステム上正式に参照するテーブル';
COMMENT ON COLUMN ZIP_CODES.ID IS 'ID';
COMMENT ON COLUMN ZIP_CODES.JAPANESE_LOCAL_GOVERMENT_CODE IS '全国地方公共団体コード	 JIS X0401、X0402';
COMMENT ON COLUMN ZIP_CODES.OLD_ZIP_CODE IS '旧郵便番号';
COMMENT ON COLUMN ZIP_CODES.ZIP_CODE IS '郵便番号';
COMMENT ON COLUMN ZIP_CODES.PREFECTURE_NAME_KANA IS '都道府県名カナ	 半角カタカナ（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES.CITY_NAME_KANA IS '市区町村名カナ	 半角カタカナ（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES.TOWN_NAME_KANA IS '町域名カナ	 半角カタカナ（五十音順に掲載）';
COMMENT ON COLUMN ZIP_CODES.PREFECTURE_NAME IS '都道府県名	 漢字（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES.CITY_NAME IS '市区町村名	 漢字（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES.TOWN_NAME IS '町域名	 漢字（五十音順に掲載）';
COMMENT ON COLUMN ZIP_CODES.TOWN_DEVIDED_FLAG IS '複数番号付与町域フラグ	 一町域が二以上の郵便番号で表される場合の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES.ISSUED_PER_KOAZA_FLAG IS '小字毎番号付与フラグ	 小字毎に番地が起番されている町域の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES.CHOME_TOWN_FLAG IS '丁目保有町域フラグ	 丁目を有する町域の場合の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES.HAS_MULTIPLE_TOWN_FLAG IS '複数町域保有フラグ	 一つの郵便番号で二以上の町域を表す場合の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES.UPDATE_DISPLAY_FLAG IS '更新の表示	 「0」は変更なし、「1」は変更あり、「2」廃止（廃止データのみ使用）';
COMMENT ON COLUMN ZIP_CODES.CHANGE_REASON_FLAG IS '変更理由	 「0」は変更なし、「1」市政・区政・町政・分区・政令指定都市施行、「2」住居表示の実施、「3」区画整理、「4」郵便区調整等、「5」訂正、「6」廃止（廃止データのみ使用）';

COMMENT ON TABLE ZIP_CODES_WORK IS '郵便番号_ワーク	 郵便局が提供している郵便番号データ
https://www.post.japanpost.jp/zipcode/dl/readme.html
csvファイルをインポートするためのワークテーブル';
COMMENT ON COLUMN ZIP_CODES_WORK.JAPANESE_LOCAL_GOVERMENT_CODE IS '全国地方公共団体コード	 JIS X0401、X0402';
COMMENT ON COLUMN ZIP_CODES_WORK.OLD_ZIP_CODE IS '旧郵便番号';
COMMENT ON COLUMN ZIP_CODES_WORK.ZIP_CODE IS '郵便番号';
COMMENT ON COLUMN ZIP_CODES_WORK.PREFECTURE_NAME_KANA IS '都道府県名カナ	 半角カタカナ（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES_WORK.CITY_NAME_KANA IS '市区町村名カナ	 半角カタカナ（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES_WORK.TOWN_NAME_KANA IS '町域名カナ	 半角カタカナ（五十音順に掲載）';
COMMENT ON COLUMN ZIP_CODES_WORK.PREFECTURE_NAME IS '都道府県名	 漢字（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES_WORK.CITY_NAME IS '市区町村名	 漢字（コード順に掲載）';
COMMENT ON COLUMN ZIP_CODES_WORK.TOWN_NAME IS '町域名	 漢字（五十音順に掲載）';
COMMENT ON COLUMN ZIP_CODES_WORK.TOWN_DEVIDED_FLAG IS '複数番号付与町域フラグ	 一町域が二以上の郵便番号で表される場合の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES_WORK.ISSUED_PER_KOAZA_FLAG IS '小字毎番号付与フラグ	 小字毎に番地が起番されている町域の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES_WORK.CHOME_TOWN_FLAG IS '丁目保有町域フラグ	 丁目を有する町域の場合の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES_WORK.HAS_MULTIPLE_TOWN_FLAG IS '複数町域保有フラグ	 一つの郵便番号で二以上の町域を表す場合の表示（「1」は該当、「0」は該当せず）';
COMMENT ON COLUMN ZIP_CODES_WORK.UPDATE_DISPLAY_FLAG IS '更新の表示	 「0」は変更なし、「1」は変更あり、「2」廃止（廃止データのみ使用）';
COMMENT ON COLUMN ZIP_CODES_WORK.CHANGE_REASON_FLAG IS '変更理由	 「0」は変更なし、「1」市政・区政・町政・分区・政令指定都市施行、「2」住居表示の実施、「3」区画整理、「4」郵便区調整等、「5」訂正、「6」廃止（廃止データのみ使用）';

