package net.javaguides.pringboot.service.impl;

import net.javaguides.pringboot.exception.ResourceNotFoundException;
import net.javaguides.pringboot.model.Employee;
import net.javaguides.pringboot.repository.EmployeeRepository;
import net.javaguides.pringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
/*
* Setter-based inyeccion de dependenncia => cuando tenemos parametros opcionales
* Constructor-based inyeccion de dependenncia => cuando tenemos parametros mandatorios
*
* A partir de spring 4.3 su una clase es setteada como Java Bean
* tiene solo un constructor, la anotacion @Autowired puede ser omitida
* y Spring va a usar ese constructor e inyectar las dependencias necesarias
*/


    private EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()){
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee","Id", id);
        }


        /*
        * o sino como funcion lambda
        *  return employeeRepository.findById(id).orElseThrow(() ->
        *                  new ResourceNotFoundException("Employee","Id", id));
        * */

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // validamos q exista en la DB
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        // guardamos el empleado actualizado
        employeeRepository.save(existingEmployee);

        return existingEmployee;

    }

    @Override
    public void deleteEmployee(long id){
        employeeRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }


}
