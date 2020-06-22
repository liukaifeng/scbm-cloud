package com.scbm.validation.service.mapper;

import com.scbm.validation.entity.EmployeesPO;
import com.scbm.validation.service.dto.EmployeesDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-27T17:23:35+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class EmployeesMapperImpl implements EmployeesMapper {

    @Override
    public EmployeesPO employeesDto2Po(EmployeesDTO employeesDTO) {
        if ( employeesDTO == null ) {
            return null;
        }

        EmployeesPO employeesPO = new EmployeesPO();

        employeesPO.setEmpNo( employeesDTO.getEmpNo() );
        employeesPO.setBirthDate( employeesDTO.getBirthDate() );
        employeesPO.setFirstName( employeesDTO.getFirstName() );
        employeesPO.setLastName( employeesDTO.getLastName() );
        employeesPO.setHireDate( employeesDTO.getHireDate() );
        employeesPO.setGender( employeesDTO.getGender() );

        return employeesPO;
    }

    @Override
    public List<EmployeesDTO> employeesPoList2DtoList(List<EmployeesPO> employeesPOList) {
        if ( employeesPOList == null ) {
            return null;
        }

        List<EmployeesDTO> list = new ArrayList<EmployeesDTO>( employeesPOList.size() );
        for ( EmployeesPO employeesPO : employeesPOList ) {
            list.add( employeesPOToEmployeesDTO( employeesPO ) );
        }

        return list;
    }

    protected EmployeesDTO employeesPOToEmployeesDTO(EmployeesPO employeesPO) {
        if ( employeesPO == null ) {
            return null;
        }

        EmployeesDTO employeesDTO = new EmployeesDTO();

        employeesDTO.setEmpNo( employeesPO.getEmpNo() );
        employeesDTO.setBirthDate( employeesPO.getBirthDate() );
        employeesDTO.setFirstName( employeesPO.getFirstName() );
        employeesDTO.setLastName( employeesPO.getLastName() );
        employeesDTO.setHireDate( employeesPO.getHireDate() );
        employeesDTO.setGender( employeesPO.getGender() );

        return employeesDTO;
    }
}
