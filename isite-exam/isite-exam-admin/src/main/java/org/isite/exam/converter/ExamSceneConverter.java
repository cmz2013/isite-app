package org.isite.exam.converter;

import org.isite.exam.po.ExamScenePo;
import org.isite.misc.data.enums.ObjectType;

/**
 * @author <font color='blue'>zhangcm</font>
 */
public class ExamSceneConverter {

    private ExamSceneConverter() {
    }

    public static ExamScenePo toExamScenePo(ObjectType objectType, String objectValue) {
        ExamScenePo examScenePo = new ExamScenePo();
        examScenePo.setObjectType(objectType);
        examScenePo.setObjectValue(objectValue);
        return examScenePo;
    }
}
