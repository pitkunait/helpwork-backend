package craft.beer.securiry.auth

import craft.beer.securiry.services.UserDetailsSecured
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class AuthJwtService {
    @Value("\${helpwork.jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${helpwork.jwt.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetailsSecured = authentication.principal as UserDetailsSecured

        val keyBytes = Decoders.BASE64.decode(jwtSecret)
        val key: Key = Keys.hmacShaKeyFor(keyBytes)

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact()
    }

    fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJwt(token)
                .body
                .subject
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJwt(authToken)
            return true
        } catch (e: Exception) {
            // throw error or/and log
        }
        return false
    }
}