package nl.willemhustinx.customerservice.controller;

import nl.willemhustinx.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private RestTemplate restTemplate;
    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService customerServiceService, RestTemplate restTemplate) {
        this.service = customerServiceService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieDTO> getMoviesByInterest() {
        //CustomerDTO customer = service.getCustomerById(customerID);

        System.out.println("ja hoor");

        MovieDTO movieDTO = restTemplate.getForObject("http://MOVIE-SERVICE/movies/first/movie", MovieDTO.class);

        System.out.println(movieDTO);
        //ResponseEntity<MovieDTO> forEntity = restTemplate.getForEntity("http://127.0.0.1:6001/movies/" + 1, MovieDTO.class);

        //MovieDTO movie = forEntity.getBody();
        //System.out.println(movie);

        return new ResponseEntity<>(movieDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> list = service.getAllCustomers();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
