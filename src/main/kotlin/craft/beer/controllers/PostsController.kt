package craft.beer.controllers

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.controllers.responses.ListPostsResponse
import craft.beer.controllers.responses.NewPostResponse
import craft.beer.posts.services.PostsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
@RequestMapping("/posts")
class PostsController(val postsService: PostsService) {

    @PostMapping("/new")
    fun newPost(@RequestBody newPostRequest: NewPostRequest): ResponseEntity<NewPostResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(postsService.newPost(newPostRequest))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(NewPostResponse(e.message!!))
        }
    }

    @GetMapping("/posts")
    fun newPost(): ResponseEntity<ListPostsResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(postsService.listPosts())
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(ListPostsResponse(e.message!!))
        }
    }
}