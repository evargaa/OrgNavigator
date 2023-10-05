package com.example.orgnavigator.scheduler;

import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledTaskClass {


    @Autowired
    EmployeeRepository employeeRepository;
    @Scheduled(cron = "0 0 */24 * * ?")
    public void checkBirthdaysAndIncreaseAge() {
        LocalDate currentDate = LocalDate.now();
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            if (employee.getBirthDate().getDayOfMonth() == currentDate.getDayOfMonth()
                    && employee.getBirthDate().getMonth() == currentDate.getMonth()) {
                employee.incrementAge();
                employeeRepository.save(employee);
            }
        }
    }
}


