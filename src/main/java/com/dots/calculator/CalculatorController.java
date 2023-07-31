package com.dots.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CalculatorController {

    private final CalculatorRepository calculatorRepository;
    private final CalculatorService calculatorService;

    CalculatorController(CalculatorRepository calculatorRepository, CalculatorService calculatorService) {
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
        Calculator calculator = new Calculator(name, doubleTotal, doubleWeight);

        calculatorRepository.save(calculator);
        return "redirect:/add/user";
    }
    @GetMapping("/getAll")
    public String displayTable(Model model){
        List<Calculator> calculatorList = calculatorService.getAllItems();
        model.addAttribute("items", calculatorList);
        return "tableDisplay";
    }

}
