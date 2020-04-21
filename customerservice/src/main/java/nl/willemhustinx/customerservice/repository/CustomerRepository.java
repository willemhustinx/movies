package nl.willemhustinx.customerservice.repository;

import nl.willemhustinx.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByid(final Long CustomerId);
}
