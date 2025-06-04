package com.shakemate.servicecase.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceCaseVO implements Serializable {
    private Integer caseId;
    private Integer userId;
    private Integer admId;
    private Integer caseTypeId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String title;
    private String content;
    private Integer caseStatus;

    public Integer getCaseId() {
        return caseId;
    }
    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getAdmId() {
        return admId;
    }
    public void setAdmId(Integer admId) {
        this.admId = admId;
    }
    public Integer getCaseTypeId() {
        return caseTypeId;
    }
    public void setCaseTypeId(Integer caseTypeId) {
        this.caseTypeId = caseTypeId;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public Timestamp getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getCaseStatus() {
        return caseStatus;
    }
    public void setCaseStatus(Integer caseStatus) {
        this.caseStatus = caseStatus;
    }
}
