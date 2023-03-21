package se.nt1dc.paymentservice.dto

data class CreatePaymentOrderReq(
    var paymentSum: Double,
    var accountId: Int
)