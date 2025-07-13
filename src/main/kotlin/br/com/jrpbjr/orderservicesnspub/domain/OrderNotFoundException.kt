package br.com.jrpbjr.orderservicesnspub.domain

import java.util.UUID

class OrderNotFoundException(id: UUID) :
    RuntimeException("Can't find any order with ID [$id]")