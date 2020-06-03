package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.examples.controllers.CompanyController;
import ru.examples.jpa.entity.CompanyJpa;
import service.mock.MockCompanyJpaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CompanyController.class, MockCompanyJpaService.class})
public class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;

    private MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/companies";

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(companyController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        CompanyJpa company = new CompanyJpa("name_test", "address_test");
        String requestJson = objectMapper.writeValueAsString(company);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get(URL+"/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        CompanyJpa company = new CompanyJpa(1L,"name_test", "address_test");
        String requestJson = objectMapper.writeValueAsString(company);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(URL+"/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
