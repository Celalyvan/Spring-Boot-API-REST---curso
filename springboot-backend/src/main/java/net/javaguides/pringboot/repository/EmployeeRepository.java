package net.javaguides.pringboot.repository;

import net.javaguides.pringboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository internamente implementa @Repository, consecuentemente
// no es necesario agregar la anotation y tambien lo hace transaccional
// con @Transactional

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
