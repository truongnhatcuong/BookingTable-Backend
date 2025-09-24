package com.example.projectSpring1.mapper;


import com.example.projectSpring1.dto.request.EmployeeRequest;
import com.example.projectSpring1.dto.response.EmployeeResponse;
import com.example.projectSpring1.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    @Mapping(target = "employeeId", ignore = true)
    Employee toEmployee(EmployeeRequest employee);
    // Entity -> Response DTO
    EmployeeResponse toResponse(Employee employee);
}
