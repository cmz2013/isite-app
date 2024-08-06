package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @Description 多选题
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class MultipleChoice extends ObjectiveQuestion<Set<Integer>> {
}
