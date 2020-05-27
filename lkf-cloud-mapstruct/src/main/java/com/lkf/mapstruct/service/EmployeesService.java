package com.lkf.validation.service;

import com.lkf.validation.entity.EmployeesPO;
import com.lkf.validation.service.dto.EmployeesDTO;

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
 * @date 2020-05-27 13:49
 */
public interface EmployeesService {
    List<EmployeesDTO> selectEmployees(EmployeesDTO employeesDTO);
}
