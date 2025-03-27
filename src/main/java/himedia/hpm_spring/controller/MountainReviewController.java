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

import himedia.hpm_spring.repository.vo.MountainReviewVo;
import himedia.hpm_spring.service.MountainReviewService;

@RestController
@RequestMapping("/api/mountain-reviews")
public class MountainReviewController {

	@Autowired
    private MountainReviewService mReviewService;
    
    // GET : /api/mountain-reviews -> 모든 리뷰 게시글 조회
    @GetMapping
    public ResponseEntity<List<MountainReviewVo>> retrieveAllReviews() {
        List<MountainReviewVo> reviews = mReviewService.retrieveAllReviews();
        return ResponseEntity.ok(reviews);
    }
    
    // GET : /api/mountain-reviews/{id} -> 특정 리뷰 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<MountainReviewVo> retrieveReviewById(@PathVariable Long id) {
    	MountainReviewVo review = mReviewService.retrieveReviewById(id);
        return ResponseEntity.ok(review);
    }
    
    // GET : /api/mountain-reviews/my/{id} -> 사용자의 리뷰 게시글 조회
    @GetMapping("/my/{id}")
    public ResponseEntity<List<MountainReviewVo>> retrieveMyReviews(@PathVariable Long id) {
        List<MountainReviewVo> reviews = mReviewService.retrieveMyReviews(id);
        return ResponseEntity.ok(reviews);
    }
        
    // POST : /api/mountain-reviews -> 리뷰 게시글 생성
    @PostMapping
    public ResponseEntity<MountainReviewVo> createReview(@RequestBody MountainReviewVo review) {
    	System.out.println("받은 데이터: " + review);
    	if (review.getUsersId() == null || review.getUsersId() <= 0) {
			return ResponseEntity.badRequest().body(null); // 유효하지 않은 usersId에 대한 오류 응답
		}
    	
    	MountainReviewVo savedReview = mReviewService.createReview(review);
        return ResponseEntity.ok(savedReview);
    }

    // PATCH : /api/mountain-reviews/{id} -> 리뷰 게시글 일부 수정
    @PatchMapping("/{id}")
    public ResponseEntity<MountainReviewVo> updateReview(@RequestBody MountainReviewVo review, @PathVariable Long id) {
        review.setId(id);
        MountainReviewVo updatedReview = mReviewService.updateReview(review);
        return ResponseEntity.ok(updatedReview);
    }
    
    // PUT : /api/mountain-reviews/{id} -> 기존 리뷰 게시글 전체 수정
//    @PutMapping("/{id}")
//    public ResponseEntity<ReviewVo> replaceReview(@RequestBody ReviewVo review, @PathVariable Long id) {
//        review.setId(id);
//        ReviewVo updatedReview = mReviewService.replaceCommunity(review);
//        return ResponseEntity.ok(updatedReview);
//    }
    
    // DELETE : /api/mountain-reviews/{id} -> 리뷰 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
    	Long usersId = requestBody.get("usersId"); // 클라이언트에서 전달받은 usersId

	    // 삭제 쿼리 실행 
    	mReviewService.deleteReview(id, usersId);
        return ResponseEntity.ok().<Void>build();
    }
}
