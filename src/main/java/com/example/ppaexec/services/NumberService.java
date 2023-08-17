package com.example.ppaexec.services;

import com.example.ppaexec.ddo.NumberSumObj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NumberService {

    private final List<NumberSumObj> numberSumObjs = Collections.synchronizedList(new ArrayList<>());

    public NumberService() {
    }

    public synchronized NumberSumObj getNumberOfSums(Integer a, Integer b) {
        NumberSumObj numberSumObj = new NumberSumObj(a, b);
        numberSumObjs.add(numberSumObj);
        return numberSumObj;
    }

    public List<NumberSumObj> searchAndGetAllNumbers(Integer num, Integer sort) {
        List<NumberSumObj> searchedNumberSumObjs = this.searchNumbers(num);
        return sortNumbers(searchedNumberSumObjs, sort);
    }

    private List<NumberSumObj> searchNumbers(Integer num) {
        return new ArrayList<>(numberSumObjs.stream().filter(n -> n.searchNumber(num)).toList());
    }

    public List<NumberSumObj> getAllNumbers(Integer sort) {
        return sortNumbers(numberSumObjs, sort);
    }

    private List<NumberSumObj> sortNumbers(List<NumberSumObj> searchedNumberSumObjs, Integer sort) {
        if (sort != 0) searchedNumberSumObjs.sort(Comparator.comparing(NumberSumObj::getSum));
        if (sort < 0) Collections.reverse(searchedNumberSumObjs);
        return searchedNumberSumObjs;
    }

}