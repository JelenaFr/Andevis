package com.assignment.andevis.controller;

import com.assignment.andevis.model.User;
import com.assignment.andevis.repository.UserRepository;
import com.assignment.andevis.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
class RestCalculatorController {
    @Autowired
    CalculatorService calculatorService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/calculator")
    public ModelAndView calculatorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("calculator");
        return modelAndView;
    }

    @GetMapping("/calculator/allCodes")
    public ResponseEntity<List<String>> list() {

        return ResponseEntity.ok(calculatorService.findAllCodes());
    }


    @GetMapping("/calculator/result/{amount}/{fromCurrency}/{toCurrency}")
    public ResponseEntity<Double> convertCurrency (@PathVariable("amount") Double amount,
                                                 @PathVariable("fromCurrency") String fromCurrency,
                                                 @PathVariable("toCurrency") String toCurrency) {
        return ResponseEntity.ok(calculatorService.convertCurrency( amount, fromCurrency, toCurrency) );
    }






//    @GetMapping("/registration")
//    public ModelAndView showRegistrationForm() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }




//    @GetMapping("/registration")
//    public String createRegistration(
//        @RequestParam("password") String password,
//        @RequestParam("firstname") String firstname,
//        @RequestParam("lastname") String lastname,
//        @RequestParam("email") String email
//    ) {
//        User newUser = new User(password, email, firstname, lastname);
//        userRepository.save(newUser);
//        return "/signup";
//    }
//
//
//    @PostMapping("/registration")
//    public ModelAndView addUser(@Valid @RequestBody User user) {
//        userRepository.save(user);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("signup");
//        return modelAndView;
//    }
//


}
