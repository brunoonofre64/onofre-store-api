package br.com.onofrestore.infrastructure.service;

import br.com.onofrestore.domain.mapper.EmployeeMapper;
import br.com.onofrestore.infrastructure.jpa.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private EmployeeServiceImpl serviceMock;

    @Mock
    private EmployeeMapper mapper;

    @Mock
    private EmployeeRepository repository;



    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
}
