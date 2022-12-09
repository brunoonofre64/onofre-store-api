package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.CustomerDTO;
import io.brunoonofre64.domain.dto.DataToCreateCustomerDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.mapper.CustomerMapper;
import io.brunoonofre64.infrastructure.jpa.CustomerRepository;
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
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceImplTest {
    private final static Long ID = 1L;

    private final static String UUID = "8d9af531-1809-4f61-ad96-3e0f39b6e643";

    private final static String NAME = "NAME";

    private final static String AGE = "AGE";

    private static final String NAME_2 = "NAME_2";

    private static final String AGE_2 = "AGE_2";

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

    private CustomerEntity customerEntityUpdate;

    private CustomerDTO customerDTO;

    private CustomerDTO customerDTOUpdate;

    private DataToCreateCustomerDTO createCustomerDTO;

    private DataToCreateCustomerDTO createCustomerDTOUpdate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    @DisplayName("Must save new customer an then return CustomerDTO intance.")
    void mustSaveNewCustomerAnThenReturnCustomerDtoIntance() {
        when(mapper.convertDTOToEntity(any())).thenReturn(customerEntity);
        when(mapper.convertEntityToDTO(any())).thenReturn(customerDTO);
        when(repository.save(any())).thenReturn(customerEntity);

        CustomerDTO response = service.saveNewCustomerInDb(createCustomerDTO);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass() );
        assertEquals(UUID, response.getUuid());
        assertEquals(NAME, response.getName());
        assertEquals(INC_DATE, response.getInclusionDate());
    }

    @Test
    @DisplayName("When search for a customer by uuid and returns the instance.")
    void whenFindByUuidAnThenReturnCustomerInstance() {
        when(repository.findByUuid(any())).thenReturn(customerEntity);
        when(repository.existsByUuid(any())).thenReturn(true);
        when(mapper.convertEntityToDTO(any())).thenReturn(customerDTO);

        CustomerDTO response = service.getCustomerByUuid(UUID);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass());
        assertEquals(UUID, response.getUuid());
        assertEquals(NAME, response.getName());
        assertEquals(INC_DATE, response.getInclusionDate());
    }

    @Test
    @DisplayName("Must look for all paged clients and return the instance.")
    void mustFindAllCustomerPagedAnReturnAnInstance() {
        pageable = PageRequest.of(0,10);

        when(repository.findAll(pageable)).thenReturn(buildCustomerEntityPaged());
        when(serviceMock.mapPagesCustomerEntityToDTO(buildCustomerEntityPaged())).thenReturn(buildCustomerDtoPaged());

        Page<CustomerDTO> reponse = service.getAllCustomers(pageable);

        assertNotNull(reponse);
        assertEquals(PageImpl.class, reponse.getClass());
        assertEquals(1, reponse.getSize());
    }

    @Test
    @DisplayName("Must update data of customer and return an instance.")
    void mustUpdateCustomerAndReturnAnInstance() {
        startCustomerDatatoUpdate();

        when(repository.existsByUuid(UUID)).thenReturn(true);
        when(repository.findByUuid(UUID)).thenReturn(customerEntity);
        when(repository.save(any())).thenReturn(customerEntityUpdate);
        when(mapper.convertEntityToDTO(any())).thenReturn(customerDTOUpdate);

        CustomerDTO response = service.updateCustomerByUuid(UUID, createCustomerDTOUpdate);

        assertNotNull(response);
        assertEquals(CustomerDTO.class, response.getClass());
        assertEquals(NAME_2, response.getName());
        assertEquals(AGE_2, response.getAge());
        assertEquals(INC_DATE, response.getInclusionDate());
        assertEquals(MODF_DATE, response.getModifyDate());
    }

    @Test
    @DisplayName("Must delete customer of database and reponse return null.")
    void mustDeleteCustomerOfDataBaseAndRepponseReturnNull() {
        when(repository.existsByUuid(UUID)).thenReturn(true);
        doNothing().when(serviceMock).deleteCustomerOfDb(UUID);

       serviceMock.deleteCustomerOfDb(UUID);

        verify(serviceMock).deleteCustomerOfDb(UUID);
        verify(serviceMock, times(1)).deleteCustomerOfDb(UUID);
        verify(serviceMock, atLeastOnce()).deleteCustomerOfDb(UUID);
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

    private void startCustomerDatatoUpdate() {
        customerEntityUpdate = new CustomerEntity(ID, UUID, NAME_2, AGE_2, INC_DATE, MODF_DATE);
        customerDTOUpdate = new CustomerDTO(UUID, NAME_2, AGE_2, INC_DATE, MODF_DATE);
        createCustomerDTOUpdate = new DataToCreateCustomerDTO(NAME_2, AGE_2);
    }
}
