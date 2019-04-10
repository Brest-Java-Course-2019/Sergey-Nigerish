package com.epam.brest.project.ps.web_app;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/root-context.xml", "classpath:rest-spring-test.xml"})
class TariffsControllerTest {

    private static final String TEXT_HTML_UTF8 = "text/html;charset=UTF-8";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private TariffsService tariffsService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(tariffsService);
        Mockito.reset(tariffsService);
    }

    @Test
    void tariffsList() throws Exception {
        Mockito.when(tariffsService.findAllStubs()).thenReturn(arrayListTariffsStubs());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tariffs")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.view().name("tariffs"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString("<title>Client management</title>")))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).findAllStubs();
    }

    @Test
    void gotoEditTariffPage() throws Exception {
        Mockito.when(tariffsService.findById(Mockito.anyInt())).thenReturn(createTariff(ONE));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tariff/3")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(">Edit tariff</span>")))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).findById((Mockito.anyInt()));
    }

    @Test
    void gotoAddTariffPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/tariff")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_HTML_UTF8))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.
                        containsString(">Add tariff</span>")))
        ;
    }

    @Test
    void updateTariff() throws Exception {
        Mockito.doNothing().when(tariffsService).add(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tariff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("tariffName", "Tariff1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tariffs"))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).update(Mockito.any());
    }

    @Test
    void addTariff() throws Exception {

        Mockito.doNothing().when(tariffsService).add(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tariffAdd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("tariffId", "3")
                        .param("tariffName", "Tariff1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tariffs"))
        ;

        Mockito.verify(tariffsService, Mockito.times(ONE)).add(Mockito.any());
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