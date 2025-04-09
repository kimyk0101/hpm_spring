package himedia.hpm_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.MountainReviewLikeVo;
import himedia.hpm_spring.service.MountainReviewLikeService;

@RestController
@RequestMapping("/api/mountain-reviews/likes/")
public class MountainReviewLikeController {

	@Autowired
	private MountainReviewLikeService mReviewLikeService;

	// 좋아요 토글 (POST 요청)
	@PostMapping("toggle")
	public ResponseEntity<String> toggleLike(@RequestBody MountainReviewLikeVo vo) {
		System.out.println("💬 toggleLike 컨트롤러 호출됨: " + vo); // 이거 찍어봐!
		boolean result = mReviewLikeService.toggleLike(vo);

		return result ? ResponseEntity.ok("좋아요 토글 완료")
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패: 사용자 또는 리뷰 없음");
	}

	// 리뷰에 대한 좋아요 개수 조회
	@GetMapping("/count")
	public ResponseEntity<Integer> getLikeCount(@RequestParam("reviewsId") int reviewsId) {
		int count = mReviewLikeService.getLikeCount(reviewsId);
		return ResponseEntity.ok(count);
	}

	// 사용자가 해당 리뷰에 좋아요 눌렀는지 확인
	@GetMapping("/is-liked")
	public ResponseEntity<Boolean> isLiked(@RequestParam("usersId") int usersId,
			@RequestParam("reviewsId") int reviewsId) {
		MountainReviewLikeVo mLikeVo = new MountainReviewLikeVo();
		mLikeVo.setUsersId(usersId);
		mLikeVo.setReviewsId(reviewsId);

		Boolean isLiked = mReviewLikeService.isLiked(mLikeVo); // Integer로 받아서 null 가능성 있음

		return ResponseEntity.ok(isLiked != null && isLiked); // null이면 false로 처리
	}

}
