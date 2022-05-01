package net.kang.kotlinweb.repository

import net.kang.kotlinweb.domain.Department
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import java.util.stream.StreamSupport

// JUnit5 에서는 의존성 주입을 @Autowired 로 해야 한다.
// https://pinokio0702.tistory.com/189?category=414017
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DepartmentRepositoryTest {
    @Autowired
    private lateinit var departmentRepository: DepartmentRepository

    /**
     * JUnit5 에선 단위 테스트 별 DB CRUD 에 대한 롤백이 자동으로 진행되는 것을 알 수 있다.
     * 단위 테스트를 진행하는 경우에 참조할 것. (단위 테스트는 우선 Department 객체에 대해서만 진행한다.)
     */

    @Test
    @Order(1)
    @Rollback(value = false)
    fun `부서 정보를 삽입한다` () {
        var department: Department = Department(0L, "NAME", "PHONE")
        departmentRepository.save(department)
        Assertions.assertThat(department.id).isGreaterThan(0L)
    }

    @Test
    @Order(2)
    fun `부서 단일 정보를 가져오다` () {
        var department: Department? = departmentRepository.findById(1L).orElse(null)
        Assertions.assertThat(department!!.id).isEqualTo(1L)
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    fun `부서 정보를 수정한다` () {
        var department: Department = Department(1L, "name", "phone")
        departmentRepository.save(department)
        Assertions.assertThat(department.name).isEqualTo("name")
        Assertions.assertThat(department.phone).isEqualTo("phone")
    }

    @Test
    @Order(4)
    fun `부서 목록 정보를 가져오다` () {
        var departments: Iterable<Department> = departmentRepository.findAll()
        Assertions.assertThat(StreamSupport.stream(departments.spliterator(), false).count()).isGreaterThan(0L)
    }

    @Test
    @Order(5)
    fun `부서 존재 여부를 확인한다` () {
        Assertions.assertThat(departmentRepository.existsById(1L)).isTrue
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    fun `부서 정보를 삭제한다` () {
        departmentRepository.deleteById(1L)
        Assertions.assertThat(departmentRepository.existsById(1L)).isFalse
    }
}