package se.nt1dc.paymentservice.entity

import jakarta.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var email: String,
    var balance: Double,
    @OneToMany()
    var payments: MutableList<Payment>
)