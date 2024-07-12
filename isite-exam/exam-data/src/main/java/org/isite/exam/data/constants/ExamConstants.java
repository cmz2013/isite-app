package org.isite.exam.data.constants;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class ExamConstants {

    private ExamConstants() {
    }

    /**
     * 服务ID
     */
    public static final String SERVICE_ID = "exam-admin";

    /**
     * 题目类型
     */
    public static final String QUESTION_SINGLE_CHOICE = "SINGLE_CHOICE";
    public static final String QUESTION_MULTIPLE_CHOICE = "MULTIPLE_CHOICE";
    public static final String QUESTION_JUDGMENT = "JUDGMENT";
    public static final String QUESTION_SHORT_ANSWER = "SHORT_ANSWER";

    /**
     * 题目分数算法类型
     */
    public static final String SINGLE_SCORE_EXACT = "SINGLE_SCORE_EXACT";
    public static final String TOTAL_SCORE_EXACT = "TOTAL_SCORE_EXACT";
    public static final String TOTAL_SCORE_FUZZY = "TOTAL_SCORE_FUZZY";
    public static final String SINGLE_SCORE_FUZZY = "SINGLE_SCORE_FUZZY";

    /**
     * 选题方式
     */
    public static final String EXAM_MODE_MANUALLY = "MANUALLY";
    public static final String EXAM_MODE_RANDOM = "RANDOM";
}
