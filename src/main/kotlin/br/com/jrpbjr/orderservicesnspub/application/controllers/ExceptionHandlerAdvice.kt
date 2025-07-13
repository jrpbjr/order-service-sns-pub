package br.com.jrpbjr.orderservicesnspub.application.controllers

import br.com.jrpbjr.orderservicesnspub.domain.OrderNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlerAdvice {

    @ExceptionHandler(OrderNotFoundException::class)
    fun handle(ex: OrderNotFoundException): ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.message ?: "Order not found")
    }
}