package craft.beer.controllers.auth

import craft.beer.data.user.IUserService
import craft.beer.data.user.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(val userService: IUserService) {

    @PostMapping("/sign-up")
    fun singUp() {

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