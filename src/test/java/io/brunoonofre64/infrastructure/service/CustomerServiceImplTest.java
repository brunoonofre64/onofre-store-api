package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.mapper.CustomerMapper;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
import liquibase.pro.packaged.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceImplTest {
    private final static Long ID = 1L;

    private final static String UUID = "8d9af531-1809-4f61-ad96-3e0f39b6e643";

    private final static String NAME = "NAME";

    private final static String AGE = "AGE";

    private final static LocalDateTime INC_DATE = LocalDateTime.now();

    private final static LocalDateTime MODF_DATE = LocalDateTime.now();

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerServiceImpl serviceMock;

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @Mock
    private Pageable pageable;

    private Page<CustomerEntity> customerEntityPage;

    private Page<CustomerDTO> customerDTOPage;

    private CustomerEntity customerEntity;

    private CustomerDTO customerDTO;

    private DataToCreateCustomerDTO createCustomerDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    @DisplayName("Must save new customer an then return CustomerDTO intance.")
    void mustSaveNewCustomerAnThenReturnCustomerDtoIntance() {
        when(mapper.convertDTOToEntity(createCustomerDTO)).thenReturn(customerEntity);
        when(mapper.convertEntityToDTO(customerEntity)).thenReturn(customerDTO);
        when(repository.save(customerEntity)).thenReturn(customerEntity);

        CustomerDTO reponse = service.saveNewCustomerInDb(createCustomerDTO);

        assertNotNull(reponse);
        assertEquals(UUID, reponse.getUuid());
        assertEquals(NAME, reponse.getName());
    }

    @Test
    @DisplayName("When search for a customer by uuid and returns the instance.")
    void whenFindByUuidAnThenReturnCustomerInstance() {
        when(repository.findByUuid(anyString())).thenReturn(customerEntity);
        when(repository.existsByUuid(anyString())).thenReturn(true);
        when(mapper.convertEntityToDTO(customerEntity)).thenReturn(customerDTO);

        CustomerDTO reponse = service.getCustomerByUuid(UUID);

        assertNotNull(reponse);
        assertEquals(CustomerDTO.class, reponse.getClass());
        assertEquals(UUID, reponse.getUuid());
        assertEquals(NAME, reponse.getName());
    }

    @Test
    @DisplayName("Must look for all paged clients and return the instance.")
    void mustFindAllCustomerPagedAnReturnAnInstance() {
        pageable = PageRequest.of(0, 10);

        when(repository.findAll(pageable)).thenReturn(buildCustomerEntityPaged());
        when(serviceMock.mapPagesCustomerEntityToDTO(buildCustomerEntityPaged())).thenReturn(buildCustomerDtoPaged());

        Page<CustomerDTO> reponse = service.getAllCustomers(pageable);

        assertNotNull(reponse);
        assertEquals(PageImpl.class, reponse.getClass());
        assertEquals(1, reponse.getSize());
    }

    private Page<CustomerEntity> buildCustomerEntityPaged() {
        List<CustomerEntity> entityList = Arrays.asList(customerEntity);
        return customerEntityPage = new PageImpl<>(entityList);
    }

    private Page<CustomerDTO> buildCustomerDtoPaged() {
        List<CustomerDTO> entityList = Arrays.asList(customerDTO);
        return customerDTOPage = new PageImpl<>(entityList);
    }

    private void startCustomer() {
        customerEntity = new CustomerEntity(ID, UUID, NAME, AGE, INC_DATE, MODF_DATE);
        createCustomerDTO = new DataToCreateCustomerDTO(NAME, AGE);
        customerDTO = new CustomerDTO(UUID, NAME, AGE, INC_DATE, MODF_DATE);
    }
}
