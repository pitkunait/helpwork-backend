package craft.beer.controllers

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController() {

    @PostMapping("/sign-up")
    fun singUp() {
        // check if user exits
        // save user
    }

    @PostMapping("/sign-in")
    fun singIn(@RequestBody email: String, @RequestBody password: String) {
        // validation service
        // return jwt
    }

    @PostMapping("/sing-out")
    fun signOut() {
        // close session
    }

    @GetMapping("/test")
    fun test(): String {
        return "it works !"
    }
}