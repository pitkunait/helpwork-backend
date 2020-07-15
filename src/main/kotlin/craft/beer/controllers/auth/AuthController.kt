package craft.beer.controllers.auth

import craft.beer.data.user.IUserService
import craft.beer.data.user.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(val userService: IUserService) {

    @PostMapping("/sign-up")
    fun singUp(@RequestBody user: User) {
        // user registration
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
        val user = User(
                firstName = "Sanjok",
                lastName = "Molodec",
                email = "test@test.com",
                password = "")

        return if (userService.userExists(user)) {
            "users exists"
        } else {
            "user not found"
        }
    }
}