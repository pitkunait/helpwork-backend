package craft.beer.controllers

import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import craft.beer.payload.responses.SignInResponse
import craft.beer.payload.responses.SignUpResponse
import craft.beer.services.IUserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@CrossOrigin
@RestController
@RequestMapping("/auth")
class AuthController(private val userService: IUserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(SignUpResponse("Success: New user created.", userService.signUp(signUpRequest)))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(SignUpResponse("Error: " + e.message, null))
        }
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(SignInResponse("Success: User logged in.", userService.signIn(signInRequest)))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(SignInResponse("Error: " + e.message, null))
        }
    }

    @PostMapping("/signout")
    fun signOut() {
        // invalidate all tokens
        // close session
    }

    @GetMapping("/refresh-token")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    fun refresh(req: HttpServletRequest): String? {
        return userService.refreshToken(req.remoteUser)
    }
}