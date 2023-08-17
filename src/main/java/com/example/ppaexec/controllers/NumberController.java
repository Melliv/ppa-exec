package com.example.ppaexec.controllers;

import com.example.ppaexec.ddo.NumberSumObj;
import com.example.ppaexec.services.NumberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/")
@Validated
class NumberController {

    @Autowired
    private NumberService calculationService;

    @GetMapping("/number/getSumOfNumbers/{a}/{b}")
    public NumberSumObj getSumOfNumbers(@PathVariable("a") @Valid @Min(value = 0, message = "Number has to be bigger or equal to 0") @Max(value = 100, message = "Number has to be less or equal to 100") Integer a, @PathVariable("b") @Valid @Min(value = 0, message = "Number has to be bigger or equal to 0") @Max(value = 100, message = "Number has to be less or equal to 100") Integer b) {
        return calculationService.getNumberOfSums(a, b);
    }

    @GetMapping("/number/all/{sort}/{num}")
    public List<NumberSumObj> searchAndGetAllNumbers(@PathVariable("sort") Integer sort, @PathVariable("num") @Valid @Min(value = 0, message = "Number has to be bigger or equal to 0") @Max(value = 100, message = "Number has to be less or equal to 100") Integer num) {
        return calculationService.searchAndGetAllNumbers(num, sort);
    }

    @GetMapping("/number/all/{sort}")
    public List<NumberSumObj> getAllNumbers(@PathVariable("sort") Integer sort) {
        return calculationService.getAllNumbers(sort);
    }
}
