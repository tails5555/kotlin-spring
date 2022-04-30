package net.kang.kotlinweb.controller

import net.kang.kotlinweb.domain.Employee
import net.kang.kotlinweb.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController (
    private var employeeService: EmployeeService
) {
    @GetMapping("/employees")
    fun findAll(): ResponseEntity<*> {
        return ResponseEntity.ok(employeeService.findAll())
    }

    @GetMapping("/employee/{id}")
    fun findById(@PathVariable id: Long?): ResponseEntity<*> {
        return if (id == null) {
            ResponseEntity<Void>(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(employeeService.findById(id!!))
        }
    }

    @PostMapping("/employee")
    fun save(@RequestBody employee: Employee?): ResponseEntity<*> {
        return if (employee == null) {
            ResponseEntity<Void>(HttpStatus.BAD_REQUEST)
        } else ResponseEntity.ok(employeeService.save(employee!!))
    }

    @DeleteMapping("/employee/{id}")
    fun deleteById(@PathVariable id: Long?): ResponseEntity<*> {
        return if (id == null) {
            ResponseEntity<Void>(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(employeeService.deleteById(id!!))
        }
    }
}