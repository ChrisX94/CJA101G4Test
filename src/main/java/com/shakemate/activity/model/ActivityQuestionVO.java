package com.shakemate.activity.model;

public class ActivityQuestionVO {

    private Integer questionId;
    private Integer activityId;
    private String questionText;
    private Byte questionOrder; // 顯示順序 (1~5)

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Byte getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Byte questionOrder) {
        this.questionOrder = questionOrder;
    }
}
