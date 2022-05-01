package net.kang.kotlinweb.service

import net.kang.kotlinweb.domain.Department
import net.kang.kotlinweb.repository.DepartmentRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*
import java.util.stream.StreamSupport

@ExtendWith(SpringExtension::class)
@ExtendWith(MockitoExtension::class)
class DepartmentServiceTest {
    @Mock
    private lateinit var departmentRepository: DepartmentRepository

    @InjectMocks
    private lateinit var departmentService: DepartmentServiceImpl

    /**
     * Mockito 를 사용하여 Repository 단위는 가짜로 움직이게끔 하여 Service 단위의 비즈니스 로직을 검토해야 한다.
     * 단위 테스트의 목적은 종속된 개체에 대해 배제하고 해당 단위에 대해 정상 작동 여부를 확인하는 것이다.
     */

    @Test
    fun `부서 목록 정보를 가져오다` () {
        Mockito.`when`(departmentRepository.findAll()).thenReturn(mutableListOf())
        var list: Iterable<Department> = departmentService.findAll()
        Assertions.assertThat(StreamSupport.stream(list.spliterator(), false).count()).isEqualTo(0L)
    }

    @Test
    fun `부서 단일 정보를 가져오다` () {
        val data = Department(1L, "NAME", "PHONE")
        Mockito.`when`(departmentRepository.findById(1L)).thenReturn(Optional.of(data))
        Assertions.assertThat(departmentService.findById(1L)).isEqualTo(data)
    }

    @Test
    fun `부서 정보를 삽입한다` () {
        val data = Department(1L, "NAME", "PHONE")
        Mockito.`when`(departmentRepository.existsById(1L)).thenReturn(false)
        Mockito.`when`(departmentRepository.save(data)).thenReturn(data)

        val result = departmentService.save(data)
        Assertions.assertThat(result["message"]).isEqualTo("CREATED")
        Assertions.assertThat(result["data"]).isEqualTo(data)
    }

    @Test
    fun `부서 정보를 수정한다` () {
        val data = Department(1L, "NAME", "PHONE")
        Mockito.`when`(departmentRepository.existsById(1L)).thenReturn(true)
        Mockito.`when`(departmentRepository.save(data)).thenReturn(data)

        val result = departmentService.save(data)
        Assertions.assertThat(result["message"]).isEqualTo("MODIFIED")
        Assertions.assertThat(result["data"]).isEqualTo(data)
    }

    @Test
    fun `부서 정보를 삭제한다` () {
        val data = Department(1L, "NAME", "PHONE")
        Mockito.`when`(departmentRepository.findById(1L)).thenReturn(Optional.of(data))

        val result = departmentService.deleteById(1L)
        Assertions.assertThat(result["result"]).isEqualTo(true)
    }
}