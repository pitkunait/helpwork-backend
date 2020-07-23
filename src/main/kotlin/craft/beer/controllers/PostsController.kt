package craft.beer.controllers

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.controllers.requests.SearchPostsRequest
import craft.beer.controllers.responses.ListPostsResponse
import craft.beer.controllers.responses.NewPostResponse
import craft.beer.posts.services.PostsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
class PostsController(private val postsService: PostsService) {

    @PostMapping("/posts")
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
    fun listPosts(): ResponseEntity<ListPostsResponse> {
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

    @GetMapping("/posts", params = ["title"])
    fun searchPosts(@RequestParam(name = "title") searchPostsRequest: SearchPostsRequest): ResponseEntity<ListPostsResponse> {
        return try {
            ResponseEntity
                    .ok()
                    .body(postsService.searchPosts(searchPostsRequest))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(ListPostsResponse(e.message!!))
        }
    }
}