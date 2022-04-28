package net.kang.kotlinweb.controller

import net.kang.kotlinweb.domain.Employee
import net.kang.kotlinweb.service.EmployeeService
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
        return ResponseEntity.ok(employeeService.findById(id!!))
    }

    @PostMapping("/employee")
    fun save(@RequestBody employee: Employee?): ResponseEntity<*> {
        return ResponseEntity.ok(
            mutableMapOf("method" to employeeService.save(employee!!))
        )
    }

    @DeleteMapping("/employee/{id}")
    fun deleteById(@PathVariable id: Long?): ResponseEntity<*> {
        return ResponseEntity.ok(
            mutableMapOf("result" to employeeService.deleteById(id!!))
        )
    }
}