package com.shakemate.activity.model;

import java.sql.Timestamp;

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

    public ActivityVO(){

    }

    public ActivityVO(Integer activityId, Integer userId, String title, String content, String imageUrl, String location, Byte activityStatus, Timestamp createdTime, Timestamp updatedTime, Timestamp regStartTime, Timestamp regEndTime, Timestamp activStartTime, Timestamp activEndTime, Byte genderFilter, Integer maxAge, Integer minAge, Timestamp expiredTime, Integer maxPeople, Integer minPeople, Integer signupCount, Integer ratingCount, Integer rating, Integer commentCount, Integer reportCount) {
        this.activityId = activityId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.location = location;
        this.activityStatus = activityStatus;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.regStartTime = regStartTime;
        this.regEndTime = regEndTime;
        this.activStartTime = activStartTime;
        this.activEndTime = activEndTime;
        this.genderFilter = genderFilter;
        this.maxAge = maxAge;
        this.minAge = minAge;
        this.expiredTime = expiredTime;
        this.maxPeople = maxPeople;
        this.minPeople = minPeople;
        this.signupCount = signupCount;
        this.ratingCount = ratingCount;
        this.rating = rating;
        this.commentCount = commentCount;
        this.reportCount = reportCount;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Byte getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Byte activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Timestamp getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(Timestamp regStartTime) {
        this.regStartTime = regStartTime;
    }

    public Timestamp getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Timestamp regEndTime) {
        this.regEndTime = regEndTime;
    }

    public Timestamp getActivStartTime() {
        return activStartTime;
    }

    public void setActivStartTime(Timestamp activStartTime) {
        this.activStartTime = activStartTime;
    }

    public Timestamp getActivEndTime() {
        return activEndTime;
    }

    public void setActivEndTime(Timestamp activEndTime) {
        this.activEndTime = activEndTime;
    }

    public Byte getGenderFilter() {
        return genderFilter;
    }

    public void setGenderFilter(Byte genderFilter) {
        this.genderFilter = genderFilter;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(Integer minPeople) {
        this.minPeople = minPeople;
    }

    public Integer getSignupCount() {
        return signupCount;
    }

    public void setSignupCount(Integer signupCount) {
        this.signupCount = signupCount;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    @Override
    public String toString() {
        return "ActivityVO{" +
                "activityId=" + activityId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", location='" + location + '\'' +
                ", activityStatus=" + activityStatus +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", regStartTime=" + regStartTime +
                ", regEndTime=" + regEndTime +
                ", activStartTime=" + activStartTime +
                ", activEndTime=" + activEndTime +
                ", genderFilter=" + genderFilter +
                ", maxAge=" + maxAge +
                ", minAge=" + minAge +
                ", expiredTime=" + expiredTime +
                ", maxPeople=" + maxPeople +
                ", minPeople=" + minPeople +
                ", signupCount=" + signupCount +
                ", ratingCount=" + ratingCount +
                ", rating=" + rating +
                ", commentCount=" + commentCount +
                ", reportCount=" + reportCount +
                '}';
    }
}
