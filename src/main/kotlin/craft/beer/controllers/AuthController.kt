package craft.beer.controllers

import craft.beer.controllers.requests.RefreshTokenRequest
import craft.beer.controllers.requests.SignInRequest
import craft.beer.controllers.requests.SignUpRequest
import craft.beer.controllers.responses.RefreshTokenResponse
import craft.beer.controllers.responses.SignInResponse
import craft.beer.controllers.responses.SignUpResponse
import craft.beer.core.user.services.IUserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
@RequestMapping("/auth")
class AuthController(private val userService: IUserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(userService.signUp(signUpRequest))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(SignUpResponse("Error: " + e.message))
        }
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(userService.signIn(signInRequest))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(SignInResponse("Error: " + e.message))
        }
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest): ResponseEntity<RefreshTokenResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(userService.refreshToken(refreshTokenRequest))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(RefreshTokenResponse("Error: " + e.message))
        }
    }
}