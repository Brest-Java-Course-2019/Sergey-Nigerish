package com.epam.brest.project.ps.rest_app;

import com.epam.brest.project.ps.model.Client;
import com.epam.brest.project.ps.service.ClientsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
class ClientsRestControllerTest {

    @Autowired
    private ClientsRestController clientsRestController;

    @Autowired
    private ClientsService clientsService;

    private MockMvc mockMvc;

    private static final Date DATE = Date.valueOf("2019-03-22");
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String CLIENT = "{\"clientContractId\":1,\"clientContractDay_date\":\"2015-10-02\"," +
            "\"clientFIO\":\"FIO1\",\"clientAddress\":\"Address1\"," +
            "\"clientBlocked\":false,\"client_to_idTariff\":1," +
            "\"clientDeleted\":false}";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientsRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(clientsService);
        Mockito.reset(clientsService);
    }

    @Test
    public void findAll() throws Exception {
        Mockito.when(clientsService.findAll()).thenReturn(arrayListClient());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/all")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientFIO", Matchers.is("FIO" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientContractId", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientAddress", Matchers.is("Address" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].client_to_idTariff", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientFIO", Matchers.is("FIO" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientContractId", Matchers.is(ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientAddress", Matchers.is("Address" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].client_to_idTariff", Matchers.is(ONE)))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findAll();
    }

    @Test
    void findAllByFilter() throws Exception {
        Mockito.when(clientsService.findAllByFilter(false, DATE, DATE)).thenReturn(arrayListClient());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/filter/false/" + DATE + "/" + DATE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientFIO", Matchers.is("FIO" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientContractId", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientAddress", Matchers.is("Address" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].client_to_idTariff", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientFIO", Matchers.is("FIO" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientContractId", Matchers.is(ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientAddress", Matchers.is("Address" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].client_to_idTariff", Matchers.is(ONE)))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findAllByFilter(false, DATE, DATE);
    }

    @Test
    void findAllByBlocking() throws Exception {
        Mockito.when(clientsService.findAllByBlocking(true)).thenReturn(arrayListClient());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/blockingFilter/false")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientFIO", Matchers.is("FIO" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientContractId", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].clientAddress", Matchers.is("Address" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].client_to_idTariff", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientFIO", Matchers.is("FIO" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientContractId", Matchers.is(ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].clientAddress", Matchers.is("Address" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].client_to_idTariff", Matchers.is(ONE)))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findAllByBlocking(true);
    }

    @Test
    void findById() throws Exception {
        Mockito.when(clientsService.findById(ONE)).thenReturn(createClient(ONE));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/" + ONE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("clientFIO", Matchers.is("FIO" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("clientContractId", Matchers.is(ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("clientAddress", Matchers.is("Address" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("client_to_idTariff", Matchers.is(ONE)))
        ;

        Mockito.verify(clientsService, Mockito.times(ONE)).findById(ONE);
    }

    @Test
    void add() throws Exception {
        Mockito.doNothing().when(clientsService).add(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CLIENT)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(clientsService, Mockito.times(ONE)).add(Mockito.any());
    }

    @Test
    void update() throws Exception {
        Mockito.doNothing().when(clientsService).update(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.put("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CLIENT)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(clientsService, Mockito.times(ONE)).update(Mockito.any());
    }

    @Test
    void updateTariff() throws Exception {
        Mockito.doNothing().when(clientsService).updateTariff(Mockito.any(), Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/updateTariff/" + ONE + "/" + ZERO)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(clientsService, Mockito.times(ONE)).updateTariff(ONE, ZERO);
    }

    @Test
    void updateBlocking() throws Exception {
        Mockito.doNothing().when(clientsService).updateBlocking(ONE, true);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clients/updateBlocking/" + ONE + "/" + true)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(clientsService, Mockito.times(ONE)).updateBlocking(ONE, true);
    }

    @Test
    void delete() throws Exception {
        Mockito.doNothing().when(clientsService).delete(ONE);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/clients/" + ONE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(clientsService, Mockito.times(ONE)).delete(ONE);
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
}