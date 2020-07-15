package craft.beer.data.mapper

import craft.beer.data.user.User
import craft.beer.data.user.UserEntity

fun User.toUserEntity() = UserEntity(
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = "",
        id = 0
)