package br.com.jrpbjr.orderservicesnspub.application.controllers

import br.com.jrpbjr.orderservicesnspub.application.payloads.OrderForm
import br.com.jrpbjr.orderservicesnspub.domain.OrderNotFoundException
import br.com.jrpbjr.orderservicesnspub.domain.model.Order
import br.com.jrpbjr.orderservicesnspub.domain.services.CreateOrderService
import br.com.jrpbjr.orderservicesnspub.infrastructure.repositories.OrderRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderRepository: OrderRepository,
    private val createOrderService: CreateOrderService
) {

    @PostMapping
    fun create(@RequestBody form: OrderForm): ResponseEntity<Void> {
        val order = Order.from(form)

        createOrderService.save(order)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(order.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<Order> {
        val order = orderRepository.findById(id.toString())
            .orElseThrow { OrderNotFoundException(id) }

        return ResponseEntity.ok(order)
    }
}