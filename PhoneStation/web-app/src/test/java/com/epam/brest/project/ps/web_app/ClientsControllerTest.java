package com.epam.brest.project.ps.web_app;

import com.epam.brest.project.ps.service.ClientsService;
import com.epam.brest.project.ps.service.TariffsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class ClientsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void clientsList() throws Exception {

//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/clients/all")
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<h1>Hello Java!</h1>")))
        ;
    }

    @Test
    void filteringClientsByDate() {
    }

    @Test
    void filteringClientsByBlocking() {
    }

    @Test
    void gotoEditClientPage() {
    }

    @Test
    void updateClient() {
    }

    @Test
    void gotoAddClientPage() {
    }

    @Test
    void addClient() {
    }

    @Test
    void changeTariff() {
    }

    @Test
    void updateBlockingStatus() {
    }

    @Test
    void deleteClientById() {
    }
}