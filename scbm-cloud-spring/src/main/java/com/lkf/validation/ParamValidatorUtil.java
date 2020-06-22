package com.scbm.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * todo
 *
 * @author 刘凯峰
 * @version V1.0
 * update-logs:方法变更说明
 * ****************************************************
 * name:
 * date:
 * description:
 * *****************************************************
 * @date 2020-04-24 14:26
 */
public class ParamValidatorUtil {
    public static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验实体参数,返回第一条错误信息
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String validateV2(T t) {
        if (t == null) {
            return "参数不能为空！";
        }
        Set<ConstraintViolation<T>> validationSet = validator.validate(t, Default.class);
        String message = null;
        if (validationSet != null && validationSet.size() > 0) {
            ConstraintViolation<T> violation = validationSet.iterator().next();
            message = violation.getMessage();
        }
        return message;
    }

    public static <T> void validate(T t) {
        String msg = validateV2(t);
        if (msg != null) {
//            throw new BizException(msg);
        }
    }
}
