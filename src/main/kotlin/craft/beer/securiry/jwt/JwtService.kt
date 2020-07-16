package craft.beer.securiry.jwt

import craft.beer.securiry.UserDetailsSecured
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import java.security.Key
import java.util.*


class JwtService {
    @Value("\${helpwork.app.secret")
    private val jwtSecret: String? = null

    @Value("\${helpwork.app.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    fun generateJwtToken(authentication: Authentication): String? {
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

    fun getUserNameFromJwtToken(token: String?): String? {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build()
                .parseClaimsJwt(token).body.subject
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJwt(authToken)
            return true
        } catch (e: Exception) {
            // throw error or/and log
        }
        return false
    }
}