package br.com.jrpbjr.orderservicesnspub.application.payloads

import java.util.UUID

data class OrderForm(
    val number: UUID,
    val requester: String,
    val items: List<ItemForm>
)