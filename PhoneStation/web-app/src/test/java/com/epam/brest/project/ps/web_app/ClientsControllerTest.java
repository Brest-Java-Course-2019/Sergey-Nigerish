package com.epam.brest.project.ps.web_app;

import com.epam.brest.project.ps.model.Client;
import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.service.ClientsService;
import com.epam.brest.project.ps.service.TariffsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
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
import org.thymeleaf.util.StringUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/root-context.xml", "classpath:rest-spring-test.xml"})
class ClientsControllerTest {

    private static final String TEXT_HTML_UTF8 = "text/html;charset=UTF-8";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private TariffsService tariffsService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(clientsService);
        Mockito.reset(clientsService);

        Mockito.verifyNoMoreInteractions(tariffsService);
        Mockito.reset(tariffsService);
    }

    @Test
    void clientsList() throws Exception {
        Mockito.when(clientsService.findAll()).thenReturn(arrayListClient());
        Mockito.when(tariffsService.findAll()).thenReturn(arrayListTariffs());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.view().name("clients"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("<title>Client management</title>")))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findAll();
        Mockito.verify(tariffsService, Mockito.times(ONE)).findAll();
    }

    @Test
    void filteringClients() throws Exception {
        Mockito.when(clientsService.findAllByFilter(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(arrayListClient());
        Mockito.when(tariffsService.findAll()).thenReturn(arrayListTariffs());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("blocking", "true")
                        .param("startDate", "03.02.2018")
                        .param("endDate", "02.3.2019")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.view().name("clients"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("<title>Client management</title>")))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findAllByFilter(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(tariffsService, Mockito.times(ONE)).findAll();
    }

    @Test
    void test() {
        System.out.println("".equals(""));
    }
    @Test
    void filteringClientsByBlocking() throws Exception {
        Mockito.when(clientsService.findAllByBlocking(Mockito.anyBoolean())).thenReturn(arrayListClient());
        Mockito.when(tariffsService.findAll()).thenReturn(arrayListTariffs());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/true")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.view().name("clients"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("<title>Client management</title>")))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findAllByBlocking(Mockito.anyBoolean());
        Mockito.verify(tariffsService, Mockito.times(ONE)).findAll();
    }

    @Test
    void gotoEditClientPage() throws Exception {

        Mockito.when(clientsService.findById(Mockito.anyInt())).thenReturn(createClient(ONE));
        Mockito.when(tariffsService.findAll()).thenReturn(arrayListTariffs());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/client/3")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(">Edit client</span>")))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findById((Mockito.anyInt()));
        Mockito.verify(tariffsService, Mockito.times(ONE)).findAll();
    }

    @Test
    void updateClient() throws Exception {
        Mockito.doNothing().when(clientsService).add(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("clientContractId", "1")
                        .param("clientFIO", "FIO1")
                        .param("clientAddress", "Address1")
                        .param("client_to_idTariff", "2")
                        .param("clientBlocked", "on")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).update(Mockito.any());
    }

    @Test
    void updateClientValidateError() throws Exception {
        Mockito.doNothing().when(clientsService).update(Mockito.any());

        String errorFilled = StringUtils.repeat("+", 300);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("clientContractId", "")
                        .param("clientFIO", errorFilled)
                        .param("clientAddress", "")
                        .param("client_to_idTariff", "1")
                        .param("clientBlocked", "on")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("The number of characters must not exceed 255.</div>")))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("The field must not be empty.</div>")))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(clientsService, Mockito.times(ZERO)).update(Mockito.any());
    }

    @Test
    void gotoAddClientPage() throws Exception {
        Mockito.when(tariffsService.findAll()).thenReturn(arrayListTariffs());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/client")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString(">Add client</span>")))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).findAll();
    }

    @Test
    void addClient() throws Exception {
        Mockito.doNothing().when(clientsService).add(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/clientAdd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("clientContractId", "")
                        .param("clientFIO", "FIO1")
                        .param("clientAddress", "Address1")
                        .param("client_to_idTariff", "1")
                        .param("clientBlocked", "on")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).add(Mockito.any());
    }

    @Test
    void addClientValidateError() throws Exception {
        Mockito.doNothing().when(clientsService).add(Mockito.any());

        String errorFilled = StringUtils.repeat("+", 300);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/clientAdd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("clientContractId", "")
                        .param("clientFIO", errorFilled)
                        .param("clientAddress", "")
                        .param("client_to_idTariff", "1")
                        .param("clientBlocked", "on")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("The number of characters must not exceed 255.</div>")))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("The field must not be empty.</div>")))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(clientsService, Mockito.times(ZERO)).add(Mockito.any());
    }

    private ArrayList<Client> arrayListClient() {
        return new ArrayList<Client>() {{add(createClient(ZERO));
            add(createClient(ONE));}};
    }

    private Client createClient(int index) {
        Client client = new Client();
        client.setClientContractId(index);
        client.setClientFIO("FIO" + index);
        client.setClientAddress("Address" + index);
        client.setClient_to_idTariff(index);
        return client;
    }

    private ArrayList<Tariff> arrayListTariffs() {
        return new ArrayList<Tariff>() {{add(createTariff(ZERO));
            add(createTariff(ONE));}};
    }

    private Tariff createTariff(int index) {
        Tariff tariff = new Tariff();
        tariff.setTariffId(index);
        tariff.setTariffName("Tariff" + index);
        tariff.setTariffDeleted(false);
        return tariff;
    }
}