package com.scbm.validation.dao;

import com.scbm.validation.entity.EmployeesPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
 * @date 2020-05-27 13:50
 */
@Mapper
public interface EmployeesDao {
    List<EmployeesPO> selectEmployees(EmployeesPO employeesPO);
}
