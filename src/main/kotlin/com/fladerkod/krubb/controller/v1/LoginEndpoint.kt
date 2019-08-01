package com.fladerkod.krubb.controller.v1

import com.fladerkod.krubb.dto.UserCredentials
import com.fladerkod.krubb.service.SessionService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/rest/v1/session")
class LoginEndpoint(val sessionService: SessionService) {

    @PostMapping
    fun createSession(@RequestBody user: UserCredentials): String = sessionService.createSession(user)

    @GetMapping
    fun test(@RequestBody token: String) {
        val claims: Claims = Jwts.parser().setSigningKey("RkT_WNGYtA9Nxl7fYwMtmP-wAWAa6YdoOvZyON72aDc")
                .parseClaimsJws(token)
                .body
    }
}