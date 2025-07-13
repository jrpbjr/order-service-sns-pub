package br.com.jrpbjr.orderservicesnspub.domain.services

import br.com.jrpbjr.orderservicesnspub.domain.model.Order
import br.com.jrpbjr.orderservicesnspub.infrastructure.repositories.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class CreateOrderService(
    private val eventPublisher: ApplicationEventPublisher,
    private val orderRepository: OrderRepository
) {

    private val log = LoggerFactory.getLogger(CreateOrderService::class.java)

    fun save(order: Order) {
        log.debug("Trying to register order [{}]", order.number)
        orderRepository.save(order)
        log.info("Saved order [{}]", order.number)

        eventPublisher.publishEvent(OrderCreatedEvent(order))
        log.info("Internal event published for order [{}]", order.number)
    }

    data class OrderCreatedEvent(val order: Order)
}