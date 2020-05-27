package com.lkf.validation.controller;

import com.lkf.validation.service.EmployeesService;
import com.lkf.validation.service.dto.EmployeesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @date 2020-05-27 13:47
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/list")
    public ResponseEntity<List<EmployeesDTO>> getEmployeesList(RequestEntity<EmployeesDTO> requestEntity) {
        List<EmployeesDTO> employeesDTOList = employeesService.selectEmployees(requestEntity.getBody());
        return ResponseEntity.ok(employeesDTOList);
    }
}
