package com.fladerkod.krubb.service.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(DocumentNotFoundException::class)
    protected fun handleNotFound(ex: DocumentNotFoundException) = ResponseEntity(ex.message, HttpStatus.NOT_FOUND)

}