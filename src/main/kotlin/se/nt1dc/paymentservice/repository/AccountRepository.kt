package se.nt1dc.paymentservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import se.nt1dc.paymentservice.entity.Account

interface AccountRepository : JpaRepository<Account, Int> {
}