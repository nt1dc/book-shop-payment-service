package se.nt1dc.paymentservice.service

import org.springframework.stereotype.Service
import se.nt1dc.paymentservice.dto.CreatePaymentOrderReq
import se.nt1dc.paymentservice.dto.CreatePaymentOrderResp
import se.nt1dc.paymentservice.entity.Payment
import se.nt1dc.paymentservice.entity.PaymentStatus
import se.nt1dc.paymentservice.repository.AccountRepository
import se.nt1dc.paymentservice.repository.PaymentRepository

@Service
class PaymentService(val paymentRepository: PaymentRepository, val accountRepository: AccountRepository) {

    fun createPaymentOrder(createPaymentOrderReq: CreatePaymentOrderReq): CreatePaymentOrderResp {
        val acc = accountRepository.findById(createPaymentOrderReq.accountId)
            .orElseThrow { RuntimeException("account not found") }
        val payment =
            Payment(account = acc, status = PaymentStatus.CREATED, paymentAmount = createPaymentOrderReq.paymentSum)
        paymentRepository.saveAndFlush(payment)
        return CreatePaymentOrderResp(payment.id)
    }

    fun payPaymentOrder(paymentId: Int) {
        val payment = paymentRepository.findById(paymentId).orElseThrow { RuntimeException("payment not found") }
        if (payment.status == PaymentStatus.CREATED) {
            payment.status = PaymentStatus.PAYED
            payment.account.balance = payment.paymentAmount + payment.account.balance
            paymentRepository.save(payment)
        }
    }

    fun checkPaymentOrderStatus(paymentId: Int): PaymentStatus {
        return paymentRepository.findById(paymentId).orElseThrow { RuntimeException("payment not found") }.status
    }
}