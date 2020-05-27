package com.lkf.validation.service.impl;

import com.lkf.validation.dao.EmployeesDao;
import com.lkf.validation.entity.EmployeesPO;
import com.lkf.validation.service.EmployeesService;
import com.lkf.validation.service.dto.EmployeesDTO;
import com.lkf.validation.service.mapper.EmployeesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Slf4j
@Service
public class EmployeesServiceImpl implements EmployeesService {
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private EmployeesDao employeesDao;

    @Override
    public List<EmployeesDTO> selectEmployees(EmployeesDTO employeesDTO) {
        EmployeesPO employeesPO = employeesMapper.employeesDto2Po(employeesDTO);
        List<EmployeesPO> employeesPOList = employeesDao.selectEmployees(employeesPO);
        return employeesMapper.employeesPoList2DtoList(employeesPOList);
    }
}
