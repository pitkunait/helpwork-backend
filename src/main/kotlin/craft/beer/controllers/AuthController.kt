package craft.beer.controllers

import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import craft.beer.payload.responses.SignInResponse
import craft.beer.payload.responses.SignUpResponse
import craft.beer.data.services.IUserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/auth")
class AuthController(private val userService: IUserService) {

    @PostMapping("/sign-up")
    fun singUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {

        if (userService.existsByUsername(signUpRequest.username)) {
            return ResponseEntity
                    .badRequest()
                    .body(SignUpResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.email)) {
            return ResponseEntity
                    .badRequest()
                    .body(SignUpResponse("Error: Email is already in use!"));
        }

        userService.saveUser(signUpRequest)

        return ResponseEntity
                .ok()
                .body(SignUpResponse("Success: New user created."))
    }

    @PostMapping("/sign-in")
    fun singIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        // validation service
        if (!userService.existsByUsername(signInRequest.username)){
            return ResponseEntity
                    .badRequest()
                    .body(SignInResponse("Error: No such username!.", null));
        }
        val jwtToken: String = userService.getJwtToken(signInRequest)

        return ResponseEntity
                .ok()
                .body(SignInResponse("Success: Sign in successful.", jwtToken));
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