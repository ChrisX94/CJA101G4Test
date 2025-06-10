package com.shakemate.activity.vo;

import java.sql.Timestamp;

/**
 * 表格名稱：活動留言 (ACTIVITY_COMMENTS)
 *
 * 主鍵：
 *   - COMMENT_ID
 *
 * 外來鍵：
 *   - ACTIVITY_ID ➔ ACTIVITY(ACTIVITY_ID)
 *   - USER_ID ➔ USERS(USER_ID)
 *   - PARENT_COMMENT_ID ➔ ACTIVITY_COMMENTS(COMMENT_ID)
 *
 * 欄位說明：
 * ------------------------------------------------------------------------------
 * | 欄位名稱          | 欄位敘述       | 資料型態 | 長度 | 備註                          |
 * ------------------------------------------------------------------------------
 * | COMMENT_ID        | 留言編號       | INT      |      | Not Null, 主鍵, 自動遞增(AI)  |
 * | ACTIVITY_ID       | 活動編號       | INT      |      | Not Null, 外來鍵              |
 * | USER_ID           | 會員編號       | INT      |      | Not Null, 外來鍵              |
 * | CONTENT           | 留言內容       | VARCHAR  | 500  | Not Null                      |
 * | PARENT_COMMENT_ID | 父留言編號     | INT      |      | 外來鍵，可為 null             |
 * | COMMENT_TIME      | 留言時間       | DATETIME |      |                                |
 * | COMMENT_COUNT     | 留言數         | INT      |      | 預設值為 0                    |
 * ------------------------------------------------------------------------------
 */


public class ActivityCommentVO {

    private Integer commentId;
    private Integer activityId;
    private Integer userId;
    private String content;
    private Integer parentCommentId;
    private Timestamp commentTime;
    private Integer commentCount;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
