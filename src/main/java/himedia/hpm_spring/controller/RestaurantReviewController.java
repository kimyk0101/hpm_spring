/**
 * 기능명: RestaurantReview
 * 파일명: RestaurantReviewController.java, RestaurantReviewCommentController.java, RestaurantReviewLikeController.java
 * 설명: 맛집 후기 게시글 관련 API 요청을 처리하는 컨트롤러 클래스
 * 			- 맛집 리뷰 게시글에 대한 CRUD(생성, 읽기, 수정, 삭제) 기능 제공
 *         	- 특정 사용자 게시글 조회
 *    	맛집 후기 게시글에 달린 댓글 및 대댓글 관련 API 요청을 처리하는 컨트롤러 클래스
 *        	- 특정 게시글에 대한 댓글 및 대댓글 CRUD(생성, 읽기, 수정, 삭제) 기능 제공
 *        	- 댓글과 대댓글의 계층 구조를 관리
 *		맛집 후기 게시글에 좋아요 관련 API 요청을 처리하는 컨트롤러 클래스
 *			- 게시글에 대한 좋아요 개수 조회, 좋아요 토글
 * 작성자: 김연경
 * 작성일: 2025-04-14
 */

package himedia.hpm_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.RestaurantReviewVo;
import himedia.hpm_spring.service.RestaurantReviewService;

@RestController
@RequestMapping("/api/restaurant-reviews")
public class RestaurantReviewController {

	@Autowired
	private RestaurantReviewService rReviewService;

	// GET : /api/restaurant-reviews -> 모든 맛집 리뷰 게시글 조회
	@GetMapping
	public ResponseEntity<List<RestaurantReviewVo>> retrieveAllReviews() {
		List<RestaurantReviewVo> reviews = rReviewService.retrieveAllReviews();
		return ResponseEntity.ok(reviews);
	}

	// GET : /api/restaurant-reviews/{id} -> 특정 맛집 리뷰 게시글 조회
	@GetMapping("/{id}")
	public ResponseEntity<RestaurantReviewVo> retrieveReviewById(@PathVariable Long id) {
		RestaurantReviewVo review = rReviewService.retrieveReviewById(id);
		return ResponseEntity.ok(review);
	}

	// GET : /api/restaurant-reviews/my/{id} -> 사용자의 맛집 리뷰 게시글 조회
	@GetMapping("/my/{id}")
	public ResponseEntity<List<RestaurantReviewVo>> retrieveMyReviews(@PathVariable Long id) {
		List<RestaurantReviewVo> reviews = rReviewService.retrieveMyReviews(id);
		return ResponseEntity.ok(reviews);
	}

	// POST : /api/restaurant-reviews -> 맛집 리뷰 게시글 생성
	@PostMapping
	public ResponseEntity<RestaurantReviewVo> createReview(@RequestBody RestaurantReviewVo review) {
		System.out.println("받은 데이터: " + review);

		if (review.getUsersId() == null || review.getUsersId() <= 0) {
			return ResponseEntity.badRequest().body(null); // 유효하지 않은 usersId에 대한 오류 응답
		}

		RestaurantReviewVo savedReview = rReviewService.createReview(review);
		System.out.println("저장된 리뷰: " + savedReview);
		return ResponseEntity.ok(savedReview);
	}

	// PATCH : /api/restaurant-reviews/{id} -> 맛집 리뷰 게시글 일부 수정
	@PatchMapping("/{id}")
	public ResponseEntity<RestaurantReviewVo> updateReview(@RequestBody RestaurantReviewVo review, @PathVariable Long id) {
		review.setId(id);
		System.out.println("🧪 수정된 데이터: " + review);
		
		RestaurantReviewVo updatedReview = rReviewService.updateReview(review);
		return ResponseEntity.ok(updatedReview);
	}

	// DELETE : /api/restaurant-reviews/{id} -> 맛집 리뷰 게시글 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
		Long usersId = requestBody.get("usersId"); // 클라이언트에서 전달받은 usersId

		rReviewService.deleteReview(id, usersId);
		return ResponseEntity.ok().<Void>build();
	}
	
}
