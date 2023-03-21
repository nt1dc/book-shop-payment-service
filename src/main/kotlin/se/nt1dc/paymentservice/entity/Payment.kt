package se.nt1dc.paymentservice.entity

import jakarta.persistence.*


@Entity
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @ManyToOne
    var account: Account,
    @Enumerated(value = EnumType.STRING)
    var status: PaymentStatus
)