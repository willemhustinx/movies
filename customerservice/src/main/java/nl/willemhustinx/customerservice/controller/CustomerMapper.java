package nl.willemhustinx.customerservice.controller;

import nl.willemhustinx.customerservice.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Customer convertToNewEntity(final CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public void convertToUpdatedEntity(final CustomerDTO customerDTO, final Customer customer) {
        modelMapper.map(customerDTO, customer);
    }

    public CustomerDTO convertToDTO(final Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
