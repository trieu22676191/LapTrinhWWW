package iuh.fit.se.controller;

import iuh.fit.se.entity.Employee;
import iuh.fit.se.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeRepository.findAll());
        return "employee-list";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "employee-update";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/";
    }
}
