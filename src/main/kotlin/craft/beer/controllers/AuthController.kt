package craft.beer.controllers

import craft.beer.model.User
import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import craft.beer.payload.responses.SignInResponse
import craft.beer.payload.responses.SignUpResponse
import craft.beer.services.IUserService
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/auth")
class AuthController(
        private val userService: IUserService,
        private val modelMapper: ModelMapper
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        try {
            val jwtToken: String = userService.signUp(modelMapper.map(signUpRequest, User::class.java))
            return ResponseEntity
                    .ok()
                    .body(SignUpResponse("Success: New user created.", jwtToken))
        } catch (e: Exception) {
            return ResponseEntity
                    .badRequest()
                    .body(SignUpResponse("Error: " + e.message, null))
        }
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        try {
            val jwtToken: String = userService.signIn(signInRequest.username, signInRequest.password)
            return ResponseEntity
                    .ok()
                    .body(SignInResponse("Success: User logged in.", jwtToken))
        } catch (e: Exception) {
            return ResponseEntity
                    .badRequest()
                    .body(SignInResponse("Error: " + e.message, null))
        }
    }

    @PostMapping("/sing-out")
    fun signOut() {
        // close session
    }

    @GetMapping("/refresh-token")
    fun test(): String {
        // close session
        return "huihui"
    }
}