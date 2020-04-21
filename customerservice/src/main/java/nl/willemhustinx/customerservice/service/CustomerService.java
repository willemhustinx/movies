package nl.willemhustinx.customerservice.service;

import nl.willemhustinx.customerservice.controller.CustomerDTO;
import nl.willemhustinx.customerservice.controller.CustomerMapper;
import nl.willemhustinx.customerservice.exception.NotFoundException;
import nl.willemhustinx.customerservice.model.Customer;
import nl.willemhustinx.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.repository = customerRepository;
        this.mapper = mapper;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> list = repository.findAll();

        return list.stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long customerID) {
        Customer foundCustomer = repository.findByid(customerID);

        if (foundCustomer != null) {
            return mapper.convertToDTO(foundCustomer);
        }
        throw new NotFoundException("Customer not found");
    }
}
