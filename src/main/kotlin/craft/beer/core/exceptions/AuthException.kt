package craft.beer.core.exceptions

import org.springframework.http.HttpStatus


class AuthException(override val message: String,
                    val httpStatus: HttpStatus) : RuntimeException() {

    companion object {
        private const val serialVersionUID = 1L
    }

}
