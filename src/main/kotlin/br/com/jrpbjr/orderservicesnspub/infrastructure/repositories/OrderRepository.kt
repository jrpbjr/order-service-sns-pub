package br.com.jrpbjr.orderservicesnspub.infrastructure.repositories


import br.com.jrpbjr.orderservicesnspub.domain.model.Order
import org.springframework.data.mongodb.repository.MongoRepository

interface OrderRepository : MongoRepository<Order, String>