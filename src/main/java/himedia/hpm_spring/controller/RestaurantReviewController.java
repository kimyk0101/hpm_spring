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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.RestaurantReviewVo;
import himedia.hpm_spring.repository.vo.CommunityVo;
import himedia.hpm_spring.repository.vo.MountainReviewVo;
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
    
	// GET : /api/communities/search?q=#{keyword} -> 키워드 기반 게시글 조회
	@GetMapping("/search")
	public ResponseEntity<List<RestaurantReviewVo>> retrieveReviewsByKeyword(@RequestParam("q") String keyword) {
		List<RestaurantReviewVo> results = rReviewService.retrieveReviewsByKeyword(keyword);
		return ResponseEntity.ok(results);
	}

        
    // POST : /api/restaurant-reviews -> 맛집 리뷰 게시글 생성
    @PostMapping
    public ResponseEntity<RestaurantReviewVo> createReview(@RequestBody RestaurantReviewVo review) {
    	RestaurantReviewVo savedReview = rReviewService.createReview(review);
        return ResponseEntity.ok(savedReview);
    }

    // PATCH : /api/restaurant-reviews/{id} -> 맛집 리뷰 게시글 일부 수정
    @PatchMapping("/{id}")
    public ResponseEntity<RestaurantReviewVo> updateReview(@RequestBody RestaurantReviewVo review, @PathVariable Long id) {
        review.setId(id);
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
    
	//	PUT : /api/restaurant-reviews/{id}/increment-views -> 조회수
    @PutMapping("/{id}/increment-views")
    public ResponseEntity<Void> incrementViews(@PathVariable("id") Long id) {
    	rReviewService.incrementViews(id);
        return ResponseEntity.ok().build();
    }
}
