package himedia.hpm_spring.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.CommunityVo;
import himedia.hpm_spring.service.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/communities")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	// GET : /api/communities -> 모든 커뮤니티 게시글 조회
	@GetMapping
	public ResponseEntity<List<CommunityVo>> retrieveAllCommunities() {
		List<CommunityVo> communities = communityService.retrieveAllCommunities();
		return ResponseEntity.ok(communities);
	}
	
	// GET : /api/communities/{id} -> 특정 게시글 조회
	@GetMapping("/{id}")
	public ResponseEntity<CommunityVo> retrieveCommunityById(@PathVariable Long id) {
		CommunityVo community = communityService.retrieveCommunityById(id);
		return ResponseEntity.ok(community);
	}

	// GET : /api/communities/my/{id} -> 사용자의 게시글 조회
	@GetMapping("/my/{id}")
	public ResponseEntity<List<CommunityVo>> retrieveMyCommunities(@PathVariable Long id) {
		List<CommunityVo> communities = communityService.retrieveMyCommunities(id);
		return ResponseEntity.ok(communities);
	}
	
	// [경민] GET : /api/communities/search?q=#{keyword} -> 키워드 기반 게시글 조회
		@GetMapping("/search")
		public ResponseEntity<List<CommunityVo>> retrieveCommunitiesByKeyword(@RequestParam("q") String keyword) {
			List<CommunityVo> results = communityService.retrieveCommunitiesByKeyword(keyword);
			return ResponseEntity.ok(results);
		}
	
	// POST : /api/communities/ -> 게시글 생성
	@PostMapping
	public ResponseEntity<CommunityVo> createCommunity(@RequestBody CommunityVo community) {
		 System.out.println("받은 데이터: " + community);
		if (community.getUsersId() == null || community.getUsersId() <= 0) {
			return ResponseEntity.badRequest().body(null); // 유효하지 않은 usersId에 대한 오류 응답
		}

		CommunityVo savedCommunity = communityService.createCommunity(community);
		return ResponseEntity.ok(savedCommunity);
	}

	// PATCH : /api/communities/{id} -> 게시글 일부 수정
	@PatchMapping("/{id}")
	public ResponseEntity<CommunityVo> updateCommunity(@RequestBody CommunityVo community, @PathVariable Long id) {
		community.setId(id);
		CommunityVo updatedCommunity = communityService.updateCommunity(community);
		return ResponseEntity.ok(updatedCommunity);
	}

	// DELETE : /api/communities/{id} -> 게시글 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCommunity(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
		Long usersId = requestBody.get("usersId"); // 클라이언트에서 전달받은 usersId

	    // 삭제 쿼리 실행    
		communityService.deleteCommunity(id, usersId);
		return ResponseEntity.ok().<Void>build();
	}
	
	//	PUT : /api/communities/{id}/increment-views -> 조회수
    @PutMapping("/{id}/increment-views")
    public ResponseEntity<Void> incrementViews(@PathVariable("id") Long id) {
        communityService.incrementViews(id);
        return ResponseEntity.ok().build();
    }
}
