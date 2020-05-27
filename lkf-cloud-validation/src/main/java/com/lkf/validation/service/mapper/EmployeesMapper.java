package com.lkf.validation.service.mapper;

import com.lkf.validation.entity.EmployeesPO;
import com.lkf.validation.service.dto.EmployeesDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
 * @date 2020-05-27 14:07
 */
@Mapper(componentModel = "spring")
public interface EmployeesMapper {

    EmployeesPO employeesDto2Po(EmployeesDTO employeesDTO);

    @IterableMapping(dateFormat = "yyyy-MM-dd")
    List<EmployeesDTO> employeesPoList2DtoList(List<EmployeesPO> employeesPOList);
}
