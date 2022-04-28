package net.kang.kotlinweb.domain

import javax.persistence.*

@Entity
@Table(name = "_department")
data class Department (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "name", length = 30)
    val name: String,

    @Column(name = "phone", length = 15)
    val phone: String
) : BaseEntity()