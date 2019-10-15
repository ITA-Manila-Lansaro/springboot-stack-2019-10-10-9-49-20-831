package com.tw.apistackbase.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeResource {
    private List<Employee> employeeList = new ArrayList<>();


    @PostMapping(value = "/", consumes = "application/json", produces =  "application/json")
    public List<Employee> createEmployee(
            @RequestBody List<Employee> incomingEmployees) {

        for (Employee employee : incomingEmployees){
            employeeList.add(employee);
        }

        return employeeList;
    }

    @GetMapping("/")
    public List<Employee> viewEmployees() {
        return employeeList;
    }

    @GetMapping("/{id}")
    public List<Employee> viewEmployee(@PathVariable int id) {
        return employeeList.stream()
                .filter(a -> id == a.getId())
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/" , consumes = "application/json", produces =  "application/json")
    public List<Employee> editEmployee(@RequestBody Employee editedEmployees ) {

        return employeeList.stream()
                .map(a -> {
                    if (a.getId() == editedEmployees.getId()) {
                        return editedEmployees;
                    }
                    return a;
                }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id){
            employeeList.remove(employeeList.stream().filter(a -> a.getId() == id).findFirst().orElse(null));
            return employeeList;
    }


}
