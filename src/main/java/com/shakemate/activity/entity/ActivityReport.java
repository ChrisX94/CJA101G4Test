package com.shakemate.activity.entity;

import com.shakemate.user.model.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;

/**
 * 表格名稱：活動檢舉 (ACTIVITY_REPORTS)
 *
 * 主鍵：
 *   - RP_USER_ID
 *
 * 外來鍵：
 *   - USER_ID ➔ USERS(USER_ID)
 *   - ACTIVITY_ID ➔ ACTIVITY(ACTIVITY_ID)
 *   - ADMIN_ID ➔ ADM(ADM_ID)
 *
 * 欄位說明：
 * -----------------------------------------------------------------------------------------------
 * | 欄位名稱      | 欄位敘述         | 資料型態 | 長度 | 備註                                         |
 * -----------------------------------------------------------------------------------------------
 * | RP_USER_ID    | 檢舉編號         | INT      |      | Not Null, 主鍵, 自動遞增(AI)                |
 * | USER_ID       | 會員編號         | INT      |      | Not Null, 外來鍵                             |
 * | ACTIVITY_ID   | 活動編號         | INT      |      | Not Null, 外來鍵                             |
 * | RP_REASON     | 檢舉事由         | TINYINT  |      | Not Null                                     |
 * |               |                  |          |      | 0: 活動內容不當                              |
 * |               |                  |          |      | 1: 含有誤導或廣告內容                        |
 * |               |                  |          |      | 2: 詐騙嫌疑（如要求金錢、分享連結等）        |
 * |               |                  |          |      | 3: 使用侮辱性語言、歧視或仇恨言論            |
 * |               |                  |          |      | 4: 其他（請填寫檢舉說明）                    |
 * | RP_CONTENT    | 檢舉文字內容     | VARCHAR  | 800  | Not Null                                     |
 * | RP_PIC        | 檢舉圖片         | VARCHAR  | 800  | 可為 null                                    |
 * | RP_TIME       | 檢舉時間         | DATETIME |      | Not Null                                     |
 * | ADM_ID        | 管理員編號       | INT      |      | Not Null, 外來鍵                             |
 * | RP_DONE_TIME  | 處理完成時間     | DATETIME |      | 可為 null                                    |
 * | RP_STATUS     | 處理狀態         | TINYINT  |      | Not Null                                     |
 * |               |                  |          |      | 0: 未處理（預設）                            |
 * |               |                  |          |      | 1: 通過                                       |
 * |               |                  |          |      | 2: 不通過                                     |
 * | RP_NOTE       | 處理註記         | VARCHAR  | 800  | 可為 null                                    |
 * -----------------------------------------------------------------------------------------------
 */

@Entity
@Table(name = "activity_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RP_USER_ID")
    private Integer rpUserId;

    @NotNull
    @Column(name = "USER_ID", insertable = false, updatable = false, nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "ACTIVITY_ID", insertable = false, updatable = false, nullable = false)
    private Integer activityId;

    @NotNull
    @Column(name = "RP_REASON", nullable = false)
    private Byte rpReason; // 0~4 對應檢舉原因

    @NotNull
    @Size(max = 800)
    @Column(name = "RP_CONTENT", nullable = false, length = 800)
    private String rpContent;

    @Size(max = 800)
    @Column(name = "RP_PIC", length = 800)
    private String rpPic;

    @NotNull
    @Column(name = "RP_TIME", nullable = false)
    private Timestamp rpTime;

    @NotNull
    @Column(name = "ADM_ID", insertable = false, updatable = false, nullable = false)
    private Integer admId;

    @Column(name = "RP_DONE_TIME")
    private Timestamp rpDoneTime;

    @NotNull
    @Column(name = "RP_STATUS", nullable = false)
    private Byte rpStatus; // 0: 未處理, 1: 通過, 2: 不通過

    @Size(max = 800)
    @Column(name = "RP_NOTE", nullable = false, length = 800)
    private String rpNote;

    // ----- 關聯關係 -----

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITY_ID")
    private Activity activity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ADM_ID")
//    private AdminVO admin;

}
