package br.com.jrpbjr.orderservicesnspub.domain.model

import br.com.jrpbjr.orderservicesnspub.application.payloads.ItemForm
import java.math.BigDecimal

data class Item(
    val description: String,
    val quantity: Int,
    val value: BigDecimal
) {
    companion object {
        fun from(form: ItemForm): Item {
            return Item(
                description = form.description,
                quantity = form.quantity,
                value = form.value
            )
        }
    }
}