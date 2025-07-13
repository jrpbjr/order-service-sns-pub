package br.com.jrpbjr.orderservicesnspub.infrastructure.migrations

import br.com.jrpbjr.orderservicesnspub.domain.model.Order
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(id = "create_collections", order = "1", author = "jose.roberto")
class ChangeUnit001(private val mongoTemplate: MongoTemplate) {

    @Execution
    fun apply() {
        val collections = listOf(
            Order.COLLECTION_NAME
        )

        collections.forEach { collection ->
            val exists = mongoTemplate.collectionExists(collection)
            if (!exists) {
                mongoTemplate.createCollection(collection)
            }
        }
    }

    @RollbackExecution
    fun rollback() {
        // do nothing
    }
}