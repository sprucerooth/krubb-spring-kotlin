package com.fladerkod.krubb.service

import com.fladerkod.krubb.dto.UserCredentials
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService {

    fun createSession(user: UserCredentials): String {
        return Jwts.builder().setSubject(user.username).claim("roles", "user").setIssuedAt(Date()).signWith(SignatureAlgorithm.HS256, "RkT_WNGYtA9Nxl7fYwMtmP-wAWAa6YdoOvZyON72aDc").compact()
    }
}