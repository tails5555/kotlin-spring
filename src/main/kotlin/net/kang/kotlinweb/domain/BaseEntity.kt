package net.kang.kotlinweb.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

// CreatedDate, LastModifiedDate 같은 데이터는 이렇게 공통으로 묶어서 사용하면 좋을 거 같다.
// 왜 이걸 사용하면, 죄 없는 다른 테이블들이 id 값이 연달아 증가하는 현상이 일어난다. 조치해야 할 거 같다.
@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {
    // lateinit 는 null 로 초기 설정하고, 나중에 값이 대입될 때 사용될 것이라 명한 예약어.
    // 객체 등 참조 값에만 해당되기 때문에, Int, Double 등에는 불가능하다.
    // https://kapentaz.github.io/jpa/kotlin/Spring-Data-JPA-Audit-in-Kotlin/# 참조

    @CreatedDate
    @Column(name = "create_date", insertable = false, updatable = false)
    private lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "last_modified_date", insertable = false, updatable = false)
    private lateinit var lastModifiedDate: LocalDateTime
}