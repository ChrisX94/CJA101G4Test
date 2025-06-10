package com.shakemate.post.vo;

import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * 表格名稱：貼文 (POST)
 *
 * 主鍵：
 *   - POST_ID
 *
 * 外來鍵：
 *   - USER_ID ➔ USERS(USER_ID)
 *
 * 欄位說明：
 * ----------------------------------------------------------------------------------------------
 * | 欄位名稱          | 欄位敘述       | 資料型態 | 長度  | 備註                                  |
 * ----------------------------------------------------------------------------------------------
 * | POST_ID           | 貼文編號       | INT      |       | Not Null, 主鍵, 自動遞增(AI)          |
 * | USER_ID           | 發文者         | INT      |       | Not Null, 外來鍵                      |
 * | POST_TEXT         | 內容           | VARCHAR  | 1000  | Not Null                              |
 * | IMAGE_URL         | 附加圖片       | VARCHAR  | 300   | (IMG_URL)，可為 null                  |
 * | POST_TIME         | 發文時間       | DATETIME |       | Not Null                              |
 * | VIEWER_PERMISSION | 觀看者權限     | TINYINT  |       | Not Null                              |
 * |                   |                |          |       | 0: 所有人（預設）                     |
 * |                   |                |          |       | 1: 配對成功者                          |
 * |                   |                |          |       | 2: 僅限自己                            |
 * | LIKES_COUNT       | 按讚總數       | INT      |       | 可為 null                             |
 * | COMMENT_COUNT     | 留言數         | INT      |       | 預設為 0                              |
 * ----------------------------------------------------------------------------------------------
 */


@Entity
@Table(name = "post")
public class PostVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Integer postId;
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "POST_TEXT", length = 1000, nullable = false)
    private String postText;

    @Column(name = "IMAGE_URL", length = 300)
    private String imageUrl;

    @Column(name = "POST_TIME", nullable = false)
    private Timestamp postTime;

    @Column(name = "VIEWER_PERMISSION", nullable = false, columnDefinition = "TINYINT")
    private Byte viewerPermission; // 0:所有人, 1:配對成功者, 2:僅限自己

    @Column(name = "LIKES_COUNT")
    private Integer likesCount;

    @Column(name = "COMMENT_COUNT")
    private Integer commentCount;

    public PostVO() {
    }

    public PostVO(Integer postId, Integer userId, String postText, String imageUrl, Timestamp postTime, Byte viewerPermission, Integer likesCount, Integer commentCount) {
        this.postId = postId;
        this.userId = userId;
        this.postText = postText;
        this.imageUrl = imageUrl;
        this.postTime = postTime;
        this.viewerPermission = viewerPermission;
        this.likesCount = likesCount;
        this.commentCount = commentCount;
    }

    public PostVO(Integer userId, String postText, String imageUrl, Timestamp postTime, Byte viewerPermission, Integer likesCount, Integer commentCount) {
        this.userId = userId;
        this.postText = postText;
        this.imageUrl = imageUrl;
        this.postTime = postTime;
        this.viewerPermission = viewerPermission;
        this.likesCount = likesCount;
        this.commentCount = commentCount;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public Byte getViewerPermission() {
        return viewerPermission;
    }

    public void setViewerPermission(Byte viewerPermission) {
        this.viewerPermission = viewerPermission;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
