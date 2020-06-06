package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.examples.controllers.EmployeeController;
import ru.examples.jpa.entity.CompanyJpa;
import ru.examples.jpa.entity.EmployeeJpa;
import service.mock.MockEmployeeJpaService;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployeeController.class, MockEmployeeJpaService.class})
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/employees";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(employeeController).build();
    }

    @Test
    public void createTest() throws Exception {
        EmployeeJpa employee = new EmployeeJpa("ФИО_test",
                new GregorianCalendar(2018, Calendar.JANUARY, 11).getTime(),
                null, new CompanyJpa(1L));
        String requestJson = objectMapper.writeValueAsString(employee);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get(URL+"/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        EmployeeJpa employee = new EmployeeJpa(1L, "ФИО_test",
                new GregorianCalendar(2018, Calendar.JANUARY, 11).getTime(),
                null, new CompanyJpa(1L));
        String requestJson = objectMapper.writeValueAsString(employee);
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
