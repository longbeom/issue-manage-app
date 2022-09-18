package issueservice.web

import issueservice.config.AuthUser
import issueservice.model.CommentRequest
import issueservice.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/issues/{issueId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ) = commentService.create(issueId, authUser.userId, authUser.username, request)

    @PutMapping("/{id}")
    fun edit(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @PathVariable id: Long,
        @RequestBody request: CommentRequest,
    ) = commentService.edit(id, authUser.userId, request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @PathVariable id: Long,
    ) {
        commentService.delete(issueId, id, authUser.userId)
    }
}