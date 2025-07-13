package br.com.jrpbjr.orderservicesnspub.application.payloads

import java.math.BigDecimal

data class ItemForm(
    val description: String,
    val quantity: Int,
    val value: BigDecimal
) {
    fun calculateItemTotal(): BigDecimal {
        return value.multiply(BigDecimal(quantity))
    }
}