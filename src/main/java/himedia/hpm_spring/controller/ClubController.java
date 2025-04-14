/**
 * 기능명: Club
 * 파일명: ClubController.java,  ClubCommentController.java, SendbirdController.java
 * 설명: 등산 모임(클럽) 관련 API 요청을 처리하는 컨트롤러 클래스
 *        	- 등산 모임 게시글에 대한 CRUD(생성, 읽기, 수정, 삭제) 기능 제공
 *        	- 특정 사용자가 만든 모임 조회, 조회수 증가 기능
 *		등산 모임(클럽) 게시글에 달린 댓글 관련 API 요청을 처리하는 컨트롤러 클래스 
 *        	- 특정 게시글에 대한 댓글 CRUD(생성, 읽기, 수정, 삭제) 기능 제공
 *        	- 에러 발생 시 JSON 형태로 응답
 *		Sendbird 채팅 API를 통해 사용자(user)를 생성하는 컨트롤러 클래스
 *       	- 클라이언트에서 userId, nickname을 받아 Sendbird 서버에 사용자 등록 요청
 *       	- 이미 존재하는 사용자일 경우에도 에러 대신 성공처럼 응답하도록 처리
 * 작성자: 서민정
 * 작성일: 2025-04-03 ~ 04-09
 */

package himedia.hpm_spring.controller;

import himedia.hpm_spring.repository.vo.ClubVo;
import himedia.hpm_spring.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ClubVo>> retrieveAllClubs() {
        List<ClubVo> clubs = clubService.findAllClubs();
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubVo> retrieveClubById(@PathVariable Long id) {
        ClubVo club = clubService.findClubById(id);
        return ResponseEntity.ok(club);
    }

    @GetMapping("/my/{usersId}")
    public ResponseEntity<List<ClubVo>> retrieveClubsByUsersId(@PathVariable Long usersId) {
        List<ClubVo> clubs = clubService.findClubsByUsersId(usersId);
        return ResponseEntity.ok(clubs);
    }

    @PostMapping
    public ResponseEntity<ClubVo> createClub(@RequestBody ClubVo club) {
        if (club.getUsersId() == null || club.getUsersId() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        ClubVo savedClub = clubService.createClub(club);
        return ResponseEntity.ok(savedClub);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClubVo> updateClub(@RequestBody ClubVo club, @PathVariable Long id) {
        club.setId(id);
        ClubVo updatedClub = clubService.updateClub(club);
        return ResponseEntity.ok(updatedClub);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long usersId = requestBody.get("usersId");
        clubService.deleteClub(id, usersId);
        return ResponseEntity.ok().<Void>build();
    }

    @PutMapping("/{id}/increment-views")
    public ResponseEntity<Void> incrementViews(@PathVariable("id") Long id) {
    	clubService.incrementViews(id);
        return ResponseEntity.ok().build();
    }
}