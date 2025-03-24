package himedia.hpm_spring.controller;

import java.util.List;

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

import himedia.hpm_spring.repository.vo.CommunityVo;
import himedia.hpm_spring.repository.vo.ReviewVo;
import himedia.hpm_spring.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
    private ReviewService reviewService;
    
    // GET : /api/reviews -> 모든 리뷰 게시글 조회
    @GetMapping
    public ResponseEntity<List<ReviewVo>> retrieveAllReviews() {
        List<ReviewVo> reviews = reviewService.retrieveAllReviews();
        return ResponseEntity.ok(reviews);
    }
    
    // GET : /api/reviews/{id} -> 특정 리뷰 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReviewVo> retrieveReviewById(@PathVariable Long id) {
    	ReviewVo review = reviewService.retrieveReviewById(id);
        return ResponseEntity.ok(review);
    }
    
    // GET : /api/reviews/my/{id} -> 사용자의 리뷰 게시글 조회
    @GetMapping("/my/{id}")
    public ResponseEntity<List<ReviewVo>> retrieveMyReviews(@PathVariable Long id) {
        List<ReviewVo> reviews = reviewService.retrieveMyReviews(id);
        return ResponseEntity.ok(reviews);
    }
        
    // POST : /api/reviews -> 리뷰 게시글 생성
    @PostMapping
    public ResponseEntity<ReviewVo> createReview(@RequestBody ReviewVo review) {
    	ReviewVo savedReview = reviewService.createReview(review);
        return ResponseEntity.ok(savedReview);
    }

    // PATCH : /api/reviews/{id} -> 리뷰 게시글 일부 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ReviewVo> updateReview(@RequestBody ReviewVo review, @PathVariable Long id) {
        review.setId(id);
        ReviewVo updatedReview = reviewService.updateReview(review);
        return ResponseEntity.ok(updatedReview);
    }
    
    // PUT : /api/reviews/{id} -> 기존 리뷰 게시글 전체 수정
//    @PutMapping("/{id}")
//    public ResponseEntity<ReviewVo> replaceReview(@RequestBody ReviewVo review, @PathVariable Long id) {
//        review.setId(id);
//        ReviewVo updatedReview = reviewService.replaceCommunity(review);
//        return ResponseEntity.ok(updatedReview);
//    }
    
    // DELETE : /api/reviews/{id} -> 리뷰 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
    	reviewService.deleteReview(id);
        return ResponseEntity.ok().<Void>build();
    }
}
