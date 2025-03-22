package himedia.hpm_spring.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.CommunityVo;
import himedia.hpm_spring.service.CommunityService;

@RestController
@RequestMapping("/api/hpmcommunity")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	
	//	GET : /api/hpmcommunity
	@GetMapping
	public ResponseEntity<List<CommunityVo>> getAllCommunities() {
		List<CommunityVo> commnunity = communityService.selectAllCommunities();
		return ResponseEntity.ok(commnunity);
	}
	
	//	GET : /api/hpmcommunity/{id}
	@GetMapping("/{id}")
	public ResponseEntity<CommunityVo> selectCommunityById(@PathVariable Long id) {
		CommunityVo commnunity = communityService.selectCommunityById(id);
		
		return ResponseEntity.ok(commnunity);
	}
	
	// GET : /api/hpmcommunity/my/{id}
	@GetMapping("/my/{id}")
	public ResponseEntity<List<CommunityVo>> selectMyCommnunity(@PathVariable Long id) {
		List<CommunityVo> commnunity = communityService.selectMyCommnunity(id);
		
		return ResponseEntity.ok(commnunity);
	}
		
	//	POST : /api/hpmcommunity ->	게시물 생성
	@PostMapping
	public ResponseEntity<CommunityVo> insertCommunity(@RequestBody CommunityVo commnunity) {
		CommunityVo savedCommnunity = communityService.insertCommunity(commnunity);
		return ResponseEntity.ok(savedCommnunity);	
		//	ResponseEntity.created로 하는 것이 의미상 더 나을 수도 있다.
	}

	//	PATCH : /api/hpmcommunity/{id} -> 게시물 수정
	@PatchMapping("/{id}")
	public ResponseEntity<CommunityVo> updateCommnunity(@RequestBody CommunityVo commnunity, @PathVariable Long id) {
		community.setId(id);
		CommunityVo updatedCommnunity = communityService.updateCommnunity(commnunity);
		return ResponseEntity.ok(updatedCommnunity);
	}
	
	//	PUT : /api/hpmcommunity/{id} -> 기존 게시물 수정
	@PutMapping("/{id}")
	public ResponseEntity<CommunityVo> updateCommnunity(@RequestBody CommunityVo commnunity, @PathVariable Long id) {
		community.setId(id);
		CommunityVo updatedCommnunity = communityService.updateCommnunity(commnunity);
		return ResponseEntity.ok(updatedCommnunity);
	}
	
	//	DELETE : /api/hpmcommunity/{id} -> 기존 게시물 삭제
	@DeleteMapping("/{id}")
	//	Body에 실어 보낼 내용이 없음 -> Void
	public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
		communityService.deleteCommunity(id);
		return ResponseEntity.ok().<Void>build();
	}
}