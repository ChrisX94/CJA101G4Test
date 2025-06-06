package com.shakemate.post.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

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
