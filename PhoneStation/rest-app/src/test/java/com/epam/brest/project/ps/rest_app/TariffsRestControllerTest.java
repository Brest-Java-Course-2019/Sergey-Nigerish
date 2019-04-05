package com.epam.brest.project.ps.rest_app;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.service.TariffsService;
import com.epam.brest.project.ps.stub.TariffStub;
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

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
class TariffsRestControllerTest {

    @Autowired
    private TariffsRestController tariffsRestController;

    @Autowired
    private TariffsService tariffsService;

    private MockMvc mockMvc;

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String TARIFF = "{\"tariffId\":1,\"tariffName\":\"Tariff1\",\"tariffDeleted\":false}";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tariffsRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(tariffsService);
        Mockito.reset(tariffsService);
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(tariffsService.findAll()).thenReturn(arrayListTariffs());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tariffs/all")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].tariffId", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].tariffName", Matchers.is("Tariff" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].tariffDeleted", Matchers.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].tariffId", Matchers.is(ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].tariffName", Matchers.is("Tariff" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].tariffDeleted", Matchers.is(false)))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).findAll();
    }

    @Test
    void findAllStubs() throws Exception {
        Mockito.when(tariffsService.findAllStubs()).thenReturn(arrayListTariffsStubs());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tariffs/allStubs")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].tariffId", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].tariffName", Matchers.is("Tariff" + ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].tariffDeleted", Matchers.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ZERO + "].tariffCountClients", Matchers.is(ZERO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].tariffId", Matchers.is(ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].tariffName", Matchers.is("Tariff" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].tariffDeleted", Matchers.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[" + ONE + "].tariffCountClients", Matchers.is(ONE)))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).findAllStubs();
    }

    @Test
    void findById() throws Exception {
        Mockito.when(tariffsService.findById(ONE)).thenReturn(createTariff(ONE));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tariffs/" + ONE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("tariffId", Matchers.is(ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("tariffName", Matchers.is("Tariff" + ONE)))
                .andExpect(MockMvcResultMatchers.jsonPath("tariffDeleted", Matchers.is(false)))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).findById(ONE);
    }

    @Test
    void add() throws Exception {
        Mockito.doNothing().when(tariffsService).add(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tariffs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TARIFF)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(tariffsService, Mockito.times(ONE)).add(Mockito.any());
    }

    @Test
    void update() throws Exception {
        Mockito.doNothing().when(tariffsService).update(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.put("/tariffs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TARIFF)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(tariffsService, Mockito.times(ONE)).update(Mockito.any());
    }

    @Test
    void delete() throws Exception {
        Mockito.doNothing().when(tariffsService).delete(ONE);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/tariffs/" + ONE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(tariffsService, Mockito.times(ONE)).delete(ONE);
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

    private ArrayList<TariffStub> arrayListTariffsStubs() {
        return new ArrayList<TariffStub>() {{add(createTariffStub(ZERO));
            add(createTariffStub(ONE));}};
    }

    private TariffStub createTariffStub(int index) {
        TariffStub tariff = new TariffStub();
        tariff.setTariffId(index);
        tariff.setTariffName("Tariff" + index);
        tariff.setTariffDeleted(false);
        tariff.setTariffCountClients(index);
        return tariff;
    }
}