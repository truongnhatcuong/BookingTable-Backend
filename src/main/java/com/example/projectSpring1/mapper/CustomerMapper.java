package com.example.projectSpring1.mapper;

import java.util.List;

import com.example.projectSpring1.dto.request.CustomerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.projectSpring1.dto.request.CustomerRequest;
import com.example.projectSpring1.dto.response.CustomerResponse;
import com.example.projectSpring1.entity.Customer;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)  // ✅ Quan trọng
public interface CustomerMapper {

    CustomerResponse toCustomer(Customer customer);
    List<CustomerResponse> toCustomerList(List<Customer> customers);
    @Mapping(target = "id", ignore = true)
    Customer toCustomerEntity(CustomerRequest request);
    @Mapping(target = "id", ignore = true)
    void updateCustomerFromRequest(CustomerUpdateRequest request, @MappingTarget Customer customer);

}
