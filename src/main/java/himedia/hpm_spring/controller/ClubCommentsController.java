package himedia.hpm_spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.ClubCommentsVo;
import himedia.hpm_spring.service.ClubCommentsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/club-comments")
public class ClubCommentsController {

    @Autowired
    private ClubCommentsService clubCommentsService;

    @GetMapping("/clubs/{clubsId}")
    public ResponseEntity<List<ClubCommentsVo>> retrieveCommentsByClubsId(@PathVariable Long clubsId) {
        List<ClubCommentsVo> comments = clubCommentsService.findCommentsByClubsId(clubsId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<ClubCommentsVo> createComment(@RequestBody ClubCommentsVo comment) {
        ClubCommentsVo savedComment = clubCommentsService.createComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateComment(@RequestBody ClubCommentsVo comment, @PathVariable Long id) {
        try {
            comment.setId(id);
            ClubCommentsVo updatedComment = clubCommentsService.updateComment(comment);
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            // 오류 발생 시 JSON 형식으로 오류 메시지 반환
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "공지사항 수정 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        clubCommentsService.deleteComment(id);
        return ResponseEntity.ok().<Void>build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        log.error("공지사항 등록 중 오류 발생:", e);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "공지사항 등록 중 오류가 발생했습니다: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}