package craft.beer.controllers


import craft.beer.controllers.responses.UserInformationResponse
import craft.beer.core.user.services.IUserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@CrossOrigin
@RestController
@RequestMapping("/user")
class UserController(private val userService: IUserService) {

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun signIn(req: HttpServletRequest): ResponseEntity<UserInformationResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(userService.whoami(req))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(UserInformationResponse())
        }
    }


}