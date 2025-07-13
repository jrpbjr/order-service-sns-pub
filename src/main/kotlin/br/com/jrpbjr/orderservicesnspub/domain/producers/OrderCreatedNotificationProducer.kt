package br.com.jrpbjr.orderservicesnspub.domain.producers

import br.com.jrpbjr.orderservicesnspub.domain.services.CreateOrderService
import io.awspring.cloud.sns.core.SnsNotification
import io.awspring.cloud.sns.core.SnsTemplate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class OrderCreatedNotificationProducer(
    @Value("\${sns.order.notification-topic}")
    private val topic: String,
    private val snsTemplate: SnsTemplate
) {

    private val log = LoggerFactory.getLogger(OrderCreatedNotificationProducer::class.java)

    @Async
    @EventListener
    fun listen(event: CreateOrderService.OrderCreatedEvent) {
        val order = event.order
        log.debug("Trying to send notification about order [{}]", order.number)
        snsTemplate.sendNotification(topic, SnsNotification.of(order))
        log.info("Notification about order [{}] sent", order.number)
    }
}