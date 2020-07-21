package craft.beer.core.security

import craft.beer.core.exceptions.CustomException
import craft.beer.user.model.Role
import craft.beer.user.model.Token
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(private val myUserDetails: MyUserDetails) {

    @Value("\${security.jwt.token.access-token-expiration}")
    private val accessTokenExpiration: Long? = null

    @Value("\${security.jwt.token.refresh-token-expiration}")
    private val refreshTokenExpiration: Long? = null

    private var secretKey: Key? = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    fun createAccessJwtToken(username: String?, roles: Set<Role>): String {
        val claims = Jwts.claims().setSubject(username)
        claims["auth"] = roles.stream()
                .map { s: Role -> SimpleGrantedAuthority(s.name) }
                .filter { obj: SimpleGrantedAuthority? -> Objects.nonNull(obj) }
                .collect(Collectors.toList())
        val now = Date()
        val validity = Date(now.time + accessTokenExpiration!!)
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact()
    }

    fun createRefreshJwtToken(username: String?): String {
        val claims = Jwts.claims().setSubject(username)
        claims["auth"] = listOf(Token.REFRESH_TOKEN.name)
        val now = Date()
        val validity = Date(now.time + refreshTokenExpiration!!)
        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact()
    }


    fun getAuthentication(token: String?): Authentication {
        val userDetails = myUserDetails.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String?): String {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
                .subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            throw CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: IllegalArgumentException) {
            throw CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}