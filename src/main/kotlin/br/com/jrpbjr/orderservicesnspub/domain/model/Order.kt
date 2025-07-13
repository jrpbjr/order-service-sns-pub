package br.com.jrpbjr.orderservicesnspub.domain.model

import br.com.jrpbjr.orderservicesnspub.application.payloads.OrderForm
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal
import java.util.UUID

@Document(Order.COLLECTION_NAME)
data class Order(
    @Id
    @Field(targetType = FieldType.STRING)
    val id: UUID,

    @Field(targetType = FieldType.STRING)
    val number: UUID,

    val requester: String,
    val total: BigDecimal,
    val items: List<Item>
) {
    companion object {
        const val COLLECTION_NAME = "orders"

        fun from(form: OrderForm): Order {
            val total = form.items
                .map { it.calculateItemTotal() }
                .fold(BigDecimal.ZERO, BigDecimal::add)

            val orderItems = form.items
                .map { Item.from(it) }

            return Order(
                id = UUID.randomUUID(),
                number = form.number,
                requester = form.requester,
                total = total,
                items = orderItems
            )
        }
    }
}