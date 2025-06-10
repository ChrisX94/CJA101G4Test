package com.shakemate.activity.vo;

/**
 * 表格名稱：活動回答 (ACTIVITY_ANSWERS)
 *
 * 主鍵：
 *   - ANSWER_ID
 *
 * 外來鍵：
 *   - ACTIVITY_ID ➔ ACTIVITY(ACTIVITY_ID)
 *   - QUESTION_ID ➔ ACTIVITY_QUESTIONS(QUESTION_ID)
 *   - USER_ID ➔ USERS(USER_ID)
 *
 * 欄位說明：
 * ------------------------------------------------------------------------------
 * | 欄位名稱     | 欄位敘述   | 資料型態 | 長度 | 備註                          |
 * ------------------------------------------------------------------------------
 * | ANSWER_ID    | 回答編號   | INT      |      | Not Null, 主鍵, 自動遞增(AI) |
 * | ACTIVITY_ID  | 活動編號   | INT      |      | Not Null, 外來鍵              |
 * | QUESTION_ID  | 問題編號   | INT      |      | Not Null, 外來鍵              |
 * | USER_ID      | 回答者     | INT      |      | Not Null, 外來鍵              |
 * | ANSWER_TEXT  | 回答內容   | VARCHAR  | 500  | Not Null                      |
 * ------------------------------------------------------------------------------
 */


public class ActivityAnswerVO {

    private Integer answerId;
    private Integer activityId;
    private Integer questionId;
    private Integer userId;
    private String answerText;

}
