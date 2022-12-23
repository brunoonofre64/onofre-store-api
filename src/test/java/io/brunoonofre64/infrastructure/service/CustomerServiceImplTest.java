package io.brunoonofre64.infrastructure.service;

import io.brunoonofre64.domain.dto.CustomerOutputDTO;
import io.brunoonofre64.domain.dto.CustomerInputDTO;
import io.brunoonofre64.domain.entities.CustomerEntity;
import io.brunoonofre64.domain.entities.OrderEntity;
import io.brunoonofre64.domain.exception.CpfRepeatedException;
import io.brunoonofre64.domain.exception.DtoNullOrIsEmptyException;
import io.brunoonofre64.domain.exception.ListIsEmptyException;
import io.brunoonofre64.domain.exception.UuidNotFoundOrNullException;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceImplTest {

    private final static Long ID = 1L;

    private final static String UUID = "8d9af531-1809-4f61-ad96-3e0f39b6e643";

    private final static String CPF = "38915839668"; //GERADO ALEATORIAMENTE

    private final static String CPF_2 = "86132623507"; //GERADO ALEATORIAMENTE

    private final static String UUID_NONEXISTENT = "123";

    private final static String NAME = "NAME";

    private final static String AGE = "AGE";

    private static final String NAME_2 = "NAME_2";

    private static final String AGE_2 = "AGE_2";

    private static  final List<OrderEntity> REQUESTS = List.of(new OrderEntity());

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

    private CustomerEntity customerEntity;

    private CustomerEntity customerEntityUpdate;

    private CustomerOutputDTO customerOutputDTO;

    private CustomerOutputDTO customerOutputDTOUpdate;

    private CustomerInputDTO createCustomerDTO;

    private CustomerInputDTO createCustomerDTOUpdate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    @DisplayName("Must save new customer an then return CustomerDTO intance.")
    void mustSaveNewCustomerAnThenReturnCustomerDtoIntance() {
        when(mapper.convertDTOToEntity(any())).thenReturn(customerEntity);
        when(mapper.convertEntityToDTO(any())).thenReturn(customerOutputDTO);
        when(repository.existsByCpf(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(customerEntity);

        CustomerOutputDTO response = service.saveNewCustomerInDb(createCustomerDTO);

        assertNotNull(response);
        assertEquals(CustomerOutputDTO.class, response.getClass() );
        assertEquals(UUID, response.getUuid());
        assertEquals(NAME, response.getName());
        assertEquals(INC_DATE, response.getInclusionDate());
    }

    @Test
    @DisplayName("Must throw error when try saver new customer with name null.")
    void mustThrowErroWhenTrySaveNewCustomerWithNameNull() {
       createCustomerDTO.setName(null);

        Throwable ex =  assertThrows(DtoNullOrIsEmptyException.class,
                () -> service.saveNewCustomerInDb(createCustomerDTO));

        assertEquals(DtoNullOrIsEmptyException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw error when try saver new customer with cpf repetead.")
    void mustThrowErroWhenTrySaveNewCustomerWithCpfRepetead() {
        when(repository.existsByCpf(any())).thenReturn(true);

        Throwable ex =  assertThrows(CpfRepeatedException.class,
                () -> service.saveNewCustomerInDb(createCustomerDTO));

        assertEquals(CpfRepeatedException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw error when try saver new customer with name empty.")
    void mustThrowErroWhenTrySaveNewCustomerWithNameEmpty() {
        createCustomerDTO.setName("");

       Throwable ex =  assertThrows(DtoNullOrIsEmptyException.class,
                () -> service.saveNewCustomerInDb(createCustomerDTO));

       assertEquals(DtoNullOrIsEmptyException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw error when try saver new customer with dto null.")
    void mustThrowErroWhenTrySaveNewCustomerWithDtoNull() {
        createCustomerDTO = null;

        Throwable ex =  assertThrows(DtoNullOrIsEmptyException.class,
                () -> service.saveNewCustomerInDb(createCustomerDTO));

        assertEquals(DtoNullOrIsEmptyException.class, ex.getClass());
    }


    @Test
    @DisplayName("When search for a customer by uuid and returns the instance.")
    void whenFindByUuidAnThenReturnCustomerInstance() {
        when(repository.findByUuid(any())).thenReturn(customerEntity);
        when(repository.existsByUuid(any())).thenReturn(true);
        when(mapper.convertEntityToDTO(any())).thenReturn(customerOutputDTO);

        CustomerOutputDTO response = service.getCustomerByUuid(UUID);

        assertNotNull(response);
        assertEquals(CustomerOutputDTO.class, response.getClass());
        assertEquals(UUID, response.getUuid());
        assertEquals(NAME, response.getName());
        assertEquals(INC_DATE, response.getInclusionDate());
    }

    @Test
    @DisplayName("Must throw exception by UUID null.")
    void mustThrowErrorByUuidNull() {
        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.getCustomerByUuid(null));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw exception by UUID is empty.")
    void mustThrowErrorByUuidIsEmpty() {
        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.getCustomerByUuid(""));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw a error by UUID not exists.")
    void mustThrowErrorByUuidNotExists() {
        when(repository.existsByUuid(UUID)).thenReturn(false);

        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.getCustomerByUuid(UUID_NONEXISTENT));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must find by uuid in repository, and return a customer instance.")
    void mustFindCustomerByUuidInRepostory(){
        when(repository.existsByUuid(UUID)).thenReturn(true);
        when(repository.findByUuid(UUID)).thenReturn(customerEntity);
        when(mapper.convertEntityToDTO(any())).thenReturn(customerOutputDTO);

        CustomerEntity response = repository.findByUuid(UUID);

        assertNotNull(response);
        assertEquals(CustomerEntity.class, response.getClass());
        assertEquals(NAME, response.getName());
        assertEquals(UUID, response.getUuid());
        assertEquals(AGE, response.getAge());
    }

    @Test
    @DisplayName("Must look for all paged clients and return the instance.")
    void mustFindAllCustomerPagedAnReturnAnInstance() {
        pageable = PageRequest.of(0,10);

        when(repository.findAll(pageable)).thenReturn(buildCustomerEntityPaged());
        when(mapper.mapPagesCustomerEntityToDTO(any())).thenReturn(buildCustomerDtoPaged());

        Page<CustomerOutputDTO> reponse = service.getAllCustomers(pageable);

        assertNotNull(reponse);
        assertEquals(PageImpl.class, reponse.getClass());
        assertEquals(1, reponse.getSize());
    }

    @Test
    @DisplayName("Must look for all paged customers of repository, and then return instance.")
    void mustFindAllCustomerPagedOfRepositoryAnReturnAnInstance() {
        pageable = PageRequest.of(0,10);

        when(repository.findAll(pageable)).thenReturn(buildCustomerEntityPaged());

        Page<CustomerEntity> reponse = repository.findAll(pageable);

        assertNotNull(reponse);
        assertEquals(PageImpl.class, reponse.getClass());
        assertEquals(1, reponse.getSize());
    }

    @Test
    @DisplayName("Must throw a error by page null.")
    void mustThrowErrorByPageIsNull() {
        pageable = null;

        Throwable ex = assertThrows(ListIsEmptyException.class,
                () -> service.getAllCustomers(pageable));

        assertEquals(ListIsEmptyException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw a error by page is empty.")
    void mustThrowErrorByPageIsEmpty() {
        Page<CustomerOutputDTO> accessPage = new PageImpl<>(Collections.emptyList());
        pageable = accessPage.getPageable();

        Throwable ex = assertThrows(ListIsEmptyException.class,
                () -> service.getAllCustomers(pageable));

        assertEquals(ListIsEmptyException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must update data of customer and return an instance.")
    void mustUpdateCustomerAndReturnAnInstance() {
        startCustomerDatatoUpdate();

        when(repository.existsByUuid(UUID)).thenReturn(true);
        when(repository.findByUuid(UUID)).thenReturn(customerEntity);
        when(repository.save(any())).thenReturn(customerEntityUpdate);
        when(mapper.convertEntityToDTO(any())).thenReturn(customerOutputDTOUpdate);

        CustomerOutputDTO response = service.updateCustomerByUuid(UUID, createCustomerDTOUpdate);

        assertNotNull(response);
        assertEquals(CustomerOutputDTO.class, response.getClass());
        assertEquals(NAME_2, response.getName());
        assertEquals(AGE_2, response.getAge());
        assertEquals(INC_DATE, response.getInclusionDate());
        assertEquals(MODF_DATE, response.getModifyDate());
    }

    @Test
    @DisplayName("When try update by uuid null, and then throw a error.")
    void mustThrowErrorByTryUpdateWithUuidNull() {
        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.updateCustomerByUuid(null, createCustomerDTO));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("When try update by uuid empty, and then throw a error.")
    void mustThrowErrorByTryUpdateWithUuidEmpty() {
        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.updateCustomerByUuid("", createCustomerDTO));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("When try update by uuid nonexistence, and then throw a error.")
    void mustThrowErrorByTryUpdateWithUuidnoNonexistence() {
        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.updateCustomerByUuid(UUID_NONEXISTENT, createCustomerDTO));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("When try update by new name null, and then throw a error.")
    void mustThrowErrorByTryUpdateWithNewNameNull() {
        createCustomerDTO.setName(null);

        when(repository.existsByUuid(UUID)).thenReturn(true);

        Throwable ex = assertThrows(DtoNullOrIsEmptyException.class,
                () -> service.updateCustomerByUuid(UUID, createCustomerDTO));

        assertEquals(DtoNullOrIsEmptyException.class, ex.getClass());
    }

    @Test
    @DisplayName("When try update by new name empty, and then throw a error.")
    void mustThrowErrorByTryUpdateWithNewNameEmpty() {
        createCustomerDTO.setName("");

        when(repository.existsByUuid(UUID)).thenReturn(true);

        Throwable ex = assertThrows(DtoNullOrIsEmptyException.class,
                () -> service.updateCustomerByUuid(UUID, createCustomerDTO));

        assertEquals(DtoNullOrIsEmptyException.class, ex.getClass());
    }

    @Test
    @DisplayName("When try update by new dto null, and then throw a error.")
    void mustThrowErrorByTryUpdateWithDTONull() {
        createCustomerDTO = null;

        when(repository.existsByUuid(UUID)).thenReturn(true);

        Throwable ex = assertThrows(DtoNullOrIsEmptyException.class,
                () -> service.updateCustomerByUuid(UUID, createCustomerDTO));

        assertEquals(DtoNullOrIsEmptyException.class, ex.getClass());
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

    @Test
    @DisplayName("Must delete customer of repository by uuid with success.")
    void mustDeleteCustomerOfRepositoryByUuidWithSuccess() {
        when((repository.existsByUuid(any()))).thenReturn(true);
        doNothing().when(repository).deleteByUuid(UUID);

        repository.deleteByUuid(UUID);

        verify(repository).deleteByUuid(UUID);
        verify(repository, times(1)).deleteByUuid(UUID);
        verify(repository, atLeastOnce()).deleteByUuid(UUID);
    }

    @Test
    @DisplayName("Must throw error when try to delete by uuid null.")
    void mustThrowErrorWhenDeleteByUuidNull() {
        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
        () -> service.deleteCustomerOfDb(null));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw error when try to delete by uuid empty.")
    void mustThrowErrorWhenDeleteByUuidEmpty() {
        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.deleteCustomerOfDb(""));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must throw error when try to delete by uuid nonexistence.")
    void mustThrowErrorWhenDeleteByUuidNonexistence() {
        when(repository.existsById(any())).thenReturn(false);

        Throwable ex = assertThrows(UuidNotFoundOrNullException.class,
                () -> service.deleteCustomerOfDb(UUID_NONEXISTENT));

        assertEquals(UuidNotFoundOrNullException.class, ex.getClass());
    }

    @Test
    @DisplayName("Must mapper CustomerEntity to dto, and then return instance.")
    void mustMapperCustomerEntityToDTOAnThenReturnInstance() {
        when(mapper.convertEntityToDTO(any())).thenReturn(customerOutputDTO);

        CustomerOutputDTO response = mapper.convertEntityToDTO(customerEntity);

        assertNotNull(response);
        assertEquals(CustomerOutputDTO.class, response.getClass());
        assertEquals(NAME, response.getName());
        assertEquals(UUID, response.getUuid());
        assertEquals(AGE, response.getAge());
    }

    @Test
    @DisplayName("Must mapper DTO to CustomerEntity, and then return dto instance.")
    void mustMapperDTOToCustomerEntityAnThenReturnInstance() {
        when(mapper.convertDTOToEntity(any())).thenReturn(customerEntity);

        CustomerEntity response = mapper.convertDTOToEntity(createCustomerDTO);

        assertNotNull(response);
        assertEquals(CustomerEntity.class, response.getClass());
        assertEquals(NAME, response.getName());
        assertEquals(UUID, response.getUuid());
        assertEquals(AGE, response.getAge());
    }

    @Test
    @DisplayName("Must mapper CustomerEntity paged to dtoPaged, and then return instance.")
    void mustMapperCustomerEntityPagedToDtoPagedAnReturnInstance() {
        when(mapper.mapPagesCustomerEntityToDTO(any())).thenReturn(buildCustomerDtoPaged());

        Page<CustomerOutputDTO> reponse = mapper.mapPagesCustomerEntityToDTO(buildCustomerEntityPaged());

        assertNotNull(reponse);
        assertEquals(PageImpl.class, reponse.getClass());
        assertEquals(1, reponse.getSize());
    }

    private Page<CustomerEntity> buildCustomerEntityPaged() {
        List<CustomerEntity> entityList = Collections.singletonList(customerEntity);
        return new PageImpl<>(entityList);
    }

    private Page<CustomerOutputDTO> buildCustomerDtoPaged() {
        List<CustomerOutputDTO> entityList = Collections.singletonList(customerOutputDTO);
        return new PageImpl<>(entityList);
    }

    private void startCustomer() {
        customerEntity = new CustomerEntity(ID, UUID, CPF, NAME, AGE, INC_DATE, REQUESTS, MODF_DATE);
        createCustomerDTO = new CustomerInputDTO(NAME, AGE, CPF);
        customerOutputDTO = new CustomerOutputDTO(ID, UUID, NAME, AGE, CPF, INC_DATE, MODF_DATE);
    }

    private void startCustomerDatatoUpdate() {
        customerEntityUpdate = new CustomerEntity(ID, UUID, CPF, NAME_2, AGE_2, INC_DATE, REQUESTS, MODF_DATE);
        customerOutputDTOUpdate = new CustomerOutputDTO(ID, UUID, NAME_2, AGE_2, CPF_2, INC_DATE, MODF_DATE);
        createCustomerDTOUpdate = new CustomerInputDTO(NAME_2, AGE_2, CPF_2);
    }
}
