package com.shakemate.post.vo;

import java.sql.Timestamp;

/**
 * 表格名稱：貼文留言 (POST_COMMENTS)
 *
 * 主鍵：
 *   - COMMENT_ID
 *
 * 外來鍵：
 *   - POST_ID ➔ POST(POST_ID)
 *   - USER_ID ➔ USERS(USER_ID)
 *   - PARENT_COMMENT_ID ➔ POST_COMMENTS(COMMENT_ID)
 *
 * 欄位說明：
 * ----------------------------------------------------------------------------------------------
 * | 欄位名稱           | 欄位敘述     | 資料型態 | 長度 | 備註                                |
 * ----------------------------------------------------------------------------------------------
 * | COMMENT_ID         | 留言編號     | INT      |      | Not Null, 主鍵, 自動遞增(AI)        |
 * | POST_ID            | 貼文編號     | INT      |      | Not Null, 外來鍵                    |
 * | COMMENT_TIME       | 留言時間     | DATETIME |      | 可為 null                           |
 * | COMMENT_TEXT       | 留言內容     | VARCHAR  | 500  | 可為 null                           |
 * | USER_ID            | 會員編號     | INT      |      | Not Null, 外來鍵                    |
 * | PARENT_COMMENT_ID  | 父留言       | INT      |      | 外來鍵，允許 null                   |
 * | COMMENT_COUNT      | 留言數       | INT      |      | 預設為 0                            |
 * ----------------------------------------------------------------------------------------------
 */


public class PostCommentVO {

    private Integer commentId;
    private Integer postId;
    private Timestamp commentTime;
    private String commentText;
    private Integer userId;
    private Integer parentCommentId;  // 可為 null
    private Integer commentCount;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
