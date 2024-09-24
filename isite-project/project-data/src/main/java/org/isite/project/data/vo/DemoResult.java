package org.isite.project.data.vo;

import org.isite.commons.cloud.data.vo.Result;

/**
 * @Description 定义Result子类：
 * 1）注入泛型，在运行时转换JSON。Java是伪泛型，编译时已替换泛型参数（直接用Result转JSON，无法获取泛型参数类）
 * 2）自定义XML模板文件
 * @Author <font color='blue'>zhangcm</font>
 */
public class DemoResult extends Result<DemoVo> {
}
