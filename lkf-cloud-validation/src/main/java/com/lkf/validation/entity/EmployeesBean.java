package com.lkf.validation.entity;

import lombok.Data;

import java.util.Date;

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
 * @date 2020-05-27 15:55
 */
@Data
public class EmployeesBean {
    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Date hireDate;
    private String gender;
}
