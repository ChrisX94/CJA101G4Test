package com.shakemate.post.model;

import java.sql.Timestamp;


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
