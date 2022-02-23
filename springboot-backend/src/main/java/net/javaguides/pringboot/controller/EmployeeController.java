package net.javaguides.pringboot.controller;

import net.javaguides.pringboot.model.Employee;
import net.javaguides.pringboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/")
public class EmployeeController {
/*
*                    =====   NOTAS   =====
*
*
* > otro beneficio de usar @RestController es que al tener incluido
* @ResponseBody elimina la necesidad de anotar cada metodo de manejo de
* respuestas de la controlladora con @ResponseBody
*
* > al usar ResponseEntity se puede generar una respuesta como retorno
* de este metodo (ResponseHead y ResponseBody)
*
* >@RequestBody permite tomar el Body de la Request y convertirlo
* automaticamente en un Objeto Java
*
*/

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }


    //construccion de la REST API de Employee
    //
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),
                HttpStatus.CREATED);
    }

    // get all employees REST API
    @GetMapping()
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // buscar employee por ID REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    // update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id),
                HttpStatus.OK);
    }

    // delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("succesfully deleted", HttpStatus.OK);
    }

}
