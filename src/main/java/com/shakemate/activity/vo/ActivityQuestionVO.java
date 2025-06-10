package com.shakemate.activity.vo;

/**
 * 表格名稱：活動問題 (ACTIVITY_QUESTIONS)
 *
 * 主鍵：
 *   - QUESTION_ID
 *
 * 外來鍵：
 *   - ACTIVITY_ID ➔ ACTIVITY(ACTIVITY_ID)
 *
 * 欄位說明：
 * ------------------------------------------------------------------------------
 * | 欄位名稱       | 欄位敘述   | 資料型態 | 長度 | 備註                         |
 * ------------------------------------------------------------------------------
 * | QUESTION_ID    | 問題編號   | INT      |      | Not Null, 主鍵, 自動遞增(AI) |
 * | ACTIVITY_ID    | 活動編號   | INT      |      | Not Null, 外來鍵             |
 * | QUESTION_TEXT  | 問題內文   | VARCHAR  | 255  | Not Null                     |
 * | QUESTION_ORDER | 顯示順序   | TINYINT  |      | 1~5，用於排序                |
 * ------------------------------------------------------------------------------
 */


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
