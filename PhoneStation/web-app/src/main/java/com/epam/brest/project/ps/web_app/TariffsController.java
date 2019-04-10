package com.epam.brest.project.ps.web_app;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.service.TariffsService;
import com.epam.brest.project.ps.web_app.validators.TariffValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Tariffs controller.
 */
@Controller
public class TariffsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsController.class);

    @Autowired
    private TariffsService tariffsService;

    @Autowired
    private TariffValidator tariffValidator;

    /**
     * Goto tariffs list page.
     *
     * @return view name
     */
    @GetMapping(value = "/tariffs")
    public final String tariffsList(Model model) {

        LOGGER.debug("findAllStubs()");
        model.addAttribute("tariffs", tariffsService.findAllStubs());
        return "tariffs";
    }

    /**
     * Goto edit tariff page.
     *
     * @return view name
     */
    @GetMapping(value = "/tariff/{tariffId}")
    public final String gotoEditTariffPage(@PathVariable Integer tariffId, Model model) {

        LOGGER.debug("gotoEditTariffPage({})", tariffId);
        Tariff tariff = tariffsService.findById(tariffId);
        model.addAttribute("tariff", tariff);
        return "tariff";
    }

    /**
     * Goto add tariff page.
     *
     * @return view name
     */
    @GetMapping(value = "/tariff")
    public final String gotoAddTariffPage(Model model) {

        LOGGER.debug("gotoAddTariffPage()");
        Tariff tariff = new Tariff();
        model.addAttribute("isNew", true);
        model.addAttribute("tariff", tariff);
        return "tariff";
    }

    /**
     * Update tariff into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/tariff")
    public String updateTariff(@Valid Tariff tariff, BindingResult result) {

        LOGGER.debug("updateTariff({})", tariff);
        tariffValidator.validate(tariff, result);
        if (result.hasErrors()) {
            return "tariff";
        } else {
            this.tariffsService.update(tariff);
            return "redirect:/tariffs";
        }
    }

    /**
     * Persist new tariff into persistence storage.
     *
     * @param tariff new tariff with filled data.
     * @param result binding result.
     * @return view name
     */
    @PostMapping(value = "/tariffAdd")
    public String addTariff(@Valid Tariff tariff, BindingResult result) {

        LOGGER.debug("addTariff({})", tariff);
        tariffValidator.validate(tariff, result);
        if (result.hasErrors()) {
            return "tariff";
        } else {
            this.tariffsService.add(tariff);
            return "redirect:/tariffs";
        }
    }

    /**
     * Delete tariff from persistence storage.
     *
     * @return view name
     */
    @GetMapping(value = "tariff/delete/{tariffId}")
    public void deleteTariffById(@PathVariable Integer tariffId) {
        LOGGER.debug("deleteTariffById({})", tariffId);
        tariffsService.delete(tariffId);
    }
}