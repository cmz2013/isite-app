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
     * 考试分数算法
     */
    public static final String SCORE_ALGORITHM_EXACT = "EXACT";
    public static final String SCORE_ALGORITHM_FUZZY = "FUZZY";

    /**
     * 选题方式
     */
    public static final String EXAM_MODE_MANUALLY = "MANUALLY";
    public static final String EXAM_MODE_RANDOM = "RANDOM";
}
