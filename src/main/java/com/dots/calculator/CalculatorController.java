package com.dots.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class CalculatorController {
    private final RestTemplate restTemplate;
    private final CalculatorRepository calculatorRepository;
    private final CalculatorService calculatorService;
    @Autowired
    CalculatorController(RestTemplate restTemplate, CalculatorRepository calculatorRepository, CalculatorService calculatorService) {
        this.restTemplate = restTemplate;
        this.calculatorRepository = calculatorRepository;
        this.calculatorService = calculatorService;
    }
    @GetMapping("/add/user")
    public String showForm(){
        return "home";
    }
    @PostMapping("/add/user")
    public String addToDatabase(@RequestParam("fullName")String name, @RequestParam("total") String total, @RequestParam("bodyWeight") String weight){
        Double doubleTotal = Double.valueOf(total);
        Double doubleWeight = Double.valueOf(weight);


        String apiUrl = "http://localhost:1234/dots-api"; // Replace with your actual API URL
        HttpHeaders headers = new HttpHeaders();
        headers.set("weight", String.valueOf(weight));
        headers.set("total", String.valueOf(total));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Double> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Double.class);

        Double dots = response.getBody();
        if (dots == null) {
            throw new IllegalStateException("Failed to retrieve dots from the API");
        }


        Calculator calculator = new Calculator(name, doubleTotal, doubleWeight, dots);

        calculatorRepository.save(calculator);
        return "redirect:/add/user";
    }
    @GetMapping("/getAll")
    public String displayTable(Model model){
        List<Calculator> calculatorList = calculatorService.getAllItems();
        model.addAttribute("items", calculatorList);
        return "tableDisplay";
    }
    @GetMapping("/calculate-dots")
    public Dots calculateDots(@RequestParam("weight") double weight, @RequestParam("total") double total) {
        String apiUrl = "http://localhost:1234/dots-api"; // Replace with your actual API URL
        HttpHeaders headers = new HttpHeaders();
        headers.set("weight", String.valueOf(weight));
        headers.set("total", String.valueOf(total));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Dots> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Dots.class);
        return response.getBody();
    }


}
