package se.nt1dc.paymentservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.nt1dc.paymentservice.dto.CreatePaymentOrderReq
import se.nt1dc.paymentservice.dto.CreatePaymentOrderResp
import se.nt1dc.paymentservice.entity.PaymentStatus
import se.nt1dc.paymentservice.service.PaymentService

@RestController
@RequestMapping("/payment")
class PaymentController(val paymentService: PaymentService) {

    @PostMapping("/create")
    fun getPaymentOrderLink(createPaymentOrderReq: CreatePaymentOrderReq): CreatePaymentOrderResp {
        return paymentService.createPaymentOrder(createPaymentOrderReq)
    }

    @GetMapping("/pay/{paymentId}")
    fun payPayment(@PathVariable paymentId: Int) {
        paymentService.payPaymentOrder(paymentId)
    }

    @GetMapping("/status/{paymentId}")
    fun getPaymentStatus(@PathVariable paymentId: Int): PaymentStatus {
        return paymentService.checkPaymentOrderStatus(paymentId)
    }
}