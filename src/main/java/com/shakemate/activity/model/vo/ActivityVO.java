package com.shakemate.activity.model.vo;

import java.sql.Timestamp;

/**
 * 表格名稱：活動（ACTIVITY）
 * 主鍵：ACTIVITY_ID
 * 外來鍵：USER_ID → USERS(USER_ID)
 *
 *
 * +-----+-------------------+------------------+-------------+----------+---------------------------------------------+
 * | No. | Column Name       | Description      | Type        | Length   | Notes                                       |
 * +-----+-------------------+------------------+-------------+----------+---------------------------------------------+
 * |  1  | ACTIVITY_ID       | 活動編號         | INT         |          | PK, AI, Not Null                            |
 * |  2  | USER_ID           | 發起者           | INT         |          | FK, Not Null                                |
 * |  3  | TITLE             | 活動標題         | VARCHAR     | 50       | Not Null                                    |
 * |  4  | CONTENT           | 活動內文         | VARCHAR     | 1000     | Not Null                                    |
 * |  5  | IMAGE_URL         | 活動圖片         | VARCHAR     | 300      | (IMG_URL)                                   |
 * |  6  | LOCATION          | 活動地點         | VARCHAR     | 100      | Not Null                                    |
 * |  7  | ACTIVITY_STATUS   | 活動狀態         | TINYINT     |          | Not Null; 0:發起中, 1:已開始, 2:已結束, 3:取消 |
 * |  8  | CREATED_TIME      | 創建時間         | DATETIME    |          | Not Null                                    |
 * |  9  | UPDATED_TIME      | 更新時間         | DATETIME    |          | Not Null                                    |
 * | 10  | REG_START_TIME    | 報名開始         | DATETIME    |          | Not Null                                    |
 * | 11  | REG_END_TIME      | 報名截止         | DATETIME    |          | Not Null                                    |
 * | 12  | ACTIV_START_TIME  | 活動開始時間     | DATETIME    |          | Not Null                                    |
 * | 13  | ACTIV_END_TIME    | 活動結束時間     | DATETIME    |          | Not Null                                    |
 * | 14  | GENDER_FILTER     | 篩選性別         | TINYINT     |          | Not Null; 0:不限, 1:男, 2:女                |
 * | 15  | MAX_AGE           | 年齡上限         | INT         |          | Not Null                                    |
 * | 16  | MIN_AGE           | 年齡下限         | INT         |          | Not Null                                    |
 * | 17  | EXPIRED_TIME      | 申請過期時間     | DATETIME    |          | Not Null                                    |
 * | 18  | MAX_PEOPLE        | 人數上限         | INT         |          | Not Null                                    |
 * | 19  | MIN_PEOPLE        | 人數下限         | INT         |          | Not Null                                    |
 * | 20  | SIGNUP_COUNT      | 已報名人數       | INT         |          | Not Null                                    |
 * | 21  | RATING_COUNT      | 評價人數         | INT         |          | Default 0                                   |
 * | 22  | RATING            | 評價分數         | INT         |          | Default 0                                   |
 * | 23  | COMMENT_COUNT     | 留言數           | INT         |          | Default 0                                   |
 * | 24  | REPORT_COUNT      | 檢舉次數         | INT         |          | Default 0                                   |
 * +-----+-------------------+------------------+-------------+----------+---------------------------------------------+
 */

public class ActivityVO {

    private Integer activityId;
    private Integer userId;
    private String title;
    private String content;
    private String imageUrl;
    private String location;
    private Byte activityStatus; // 0: 發起中, 1: 開始, 2: 結束, 3: 取消或下架
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private Timestamp regStartTime;
    private Timestamp regEndTime;
    private Timestamp activStartTime;
    private Timestamp activEndTime;
    private Byte genderFilter; // 0: 不限, 1: 男, 2: 女
    private Integer maxAge;
    private Integer minAge;
    private Timestamp expiredTime;
    private Integer maxPeople;
    private Integer minPeople;
    private Integer signupCount;
    private Integer ratingCount;
    private Integer rating;
    private Integer commentCount;
    private Integer reportCount;

}
