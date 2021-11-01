package com.assignment.andevis.controller;

import com.assignment.andevis.model.History;
import com.assignment.andevis.service.RateConverterService;
import com.assignment.andevis.service.HistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
class RestCalculatorController {
    @Autowired
    RateConverterService rateConverterService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/calculator")
    public ModelAndView calculatorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("calculator");
        return modelAndView;
    }

    @GetMapping("/calculator/allCodes")
    public ResponseEntity<List<String>> list() {

        return ResponseEntity.ok(rateConverterService.findAllCodes());
    }

    @GetMapping("/calculator/{amount}/{fromCurrency}/{toCurrency}")
    public ResponseEntity<Double> convertCurrency(@PathVariable("amount") Double amount,
                                                  @PathVariable("fromCurrency") String fromCurrency,
                                                  @PathVariable("toCurrency") String toCurrency, Principal principal) throws JsonProcessingException {
        rateConverterService.checkDatabaseActuality();
        return ResponseEntity.ok(rateConverterService.convertCurrency(amount, fromCurrency, toCurrency, principal));
    }

    @GetMapping("/calculator/history")
    public ResponseEntity<List<History>> showUserHistory(Principal principal) {
        return ResponseEntity.ok(historyService.findUserHistory(principal));
    }

    @DeleteMapping("/calculator/delete/{param}")
    public void deleteFirstLevel(@PathVariable("param") Long id) {
        historyService.deleteEntry(id);
    }
}
