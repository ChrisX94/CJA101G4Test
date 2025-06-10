package com.shakemate.post.vo;

import java.sql.Timestamp;

/**
 * 表格名稱：貼文檢舉 (POST_REPORTS)
 *
 * 主鍵：
 *   - RP_USER_ID
 *
 * 外來鍵：
 *   - USER_ID ➔ USERS(USER_ID)
 *   - POST_ID ➔ POST(POST_ID)
 *   - ADMIN_ID ➔ ADM(ADM_ID)
 *
 * 欄位說明：
 * --------------------------------------------------------------------------------------------------------------
 * | 欄位名稱     | 欄位敘述       | 資料型態 | 長度 | 備註                                                              |
 * --------------------------------------------------------------------------------------------------------------
 * | RP_USER_ID   | 檢舉編號       | INT      |      | Not Null, 主鍵, 自動遞增(AI)                                      |
 * | USER_ID      | 會員編號       | INT      |      | Not Null, 外來鍵                                                  |
 * | POST_ID      | 貼文編號       | INT      |      | Not Null, 外來鍵                                                  |
 * | RP_REASON    | 檢舉事由       | TINYINT  |      | Not Null                                                           |
 * |              |                |          |      | 0: 性暗示或不雅言論                                               |
 * |              |                |          |      | 1: 上傳不當或照片（如裸露、暴力）                                 |
 * |              |                |          |      | 2: 貼文含有誤導或廣告內容                                         |
 * |              |                |          |      | 3: 冒充他人 / 使用假身分                                          |
 * |              |                |          |      | 4: 導流至外部平台或私下交易行為                                   |
 * |              |                |          |      | 5: 詐騙嫌疑（如要求金錢、分享連結等）                             |
 * |              |                |          |      | 6: 使用侮辱性語言、歧視或仇恨言論                                 |
 * |              |                |          |      | 7: 其他（請填寫檢舉說明）                                         |
 * | RP_CONTENT   | 檢舉文字內容   | VARCHAR  | 800  | Not Null                                                           |
 * | RP_PIC       | 檢舉圖片       | VARCHAR  | 800  | 可為 null                                                          |
 * | RP_TIME      | 檢舉時間       | DATETIME |      | Not Null                                                           |
 * | ADM_ID       | 管理員編號     | INT      |      | Not Null, 外來鍵                                                  |
 * | RP_DONE_TIME | 處理完成時間   | DATETIME |      | 可為 null                                                          |
 * | RP_STATUS    | 處理狀態       | TINYINT  |      | Not Null                                                           |
 * |              |                |          |      | 0: 未處理（預設）                                                 |
 * |              |                |          |      | 1: 通過                                                            |
 * |              |                |          |      | 2: 不通過                                                          |
 * | RP_NOTE      | 處理註記       | VARCHAR  | 800  | 可為 null                                                          |
 * --------------------------------------------------------------------------------------------------------------
 */


public class PostReportVO {

    private Integer rpUserId;
    private Integer userId;
    private Integer postId;
    private Byte rpReason;      // 檢舉事由代碼（0~7）
    private String rpContent;
    private String rpPic;
    private Timestamp rpTime;
    private Integer admId;
    private Timestamp rpDoneTime;
    private Byte rpStatus;      // 處理狀態（0:未處理, 1:通過, 2:不通過）
    private String rpNote;

    public Integer getRpUserId() {
        return rpUserId;
    }

    public void setRpUserId(Integer rpUserId) {
        this.rpUserId = rpUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Byte getRpReason() {
        return rpReason;
    }

    public void setRpReason(Byte rpReason) {
        this.rpReason = rpReason;
    }

    public String getRpContent() {
        return rpContent;
    }

    public void setRpContent(String rpContent) {
        this.rpContent = rpContent;
    }

    public String getRpPic() {
        return rpPic;
    }

    public void setRpPic(String rpPic) {
        this.rpPic = rpPic;
    }

    public Timestamp getRpTime() {
        return rpTime;
    }

    public void setRpTime(Timestamp rpTime) {
        this.rpTime = rpTime;
    }

    public Integer getAdmId() {
        return admId;
    }

    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public Timestamp getRpDoneTime() {
        return rpDoneTime;
    }

    public void setRpDoneTime(Timestamp rpDoneTime) {
        this.rpDoneTime = rpDoneTime;
    }

    public Byte getRpStatus() {
        return rpStatus;
    }

    public void setRpStatus(Byte rpStatus) {
        this.rpStatus = rpStatus;
    }

    public String getRpNote() {
        return rpNote;
    }

    public void setRpNote(String rpNote) {
        this.rpNote = rpNote;
    }
}
