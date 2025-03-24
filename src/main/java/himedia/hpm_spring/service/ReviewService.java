package himedia.hpm_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.hpm_spring.mappers.ReviewMapper;
import himedia.hpm_spring.repository.vo.ReviewVo;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    // 모든 리뷰 게시글 조회
    public List<ReviewVo> retrieveAllReviews() {
        return reviewMapper.retrieveAllReviews();
    }

    // 특정 리뷰 게시글 조회
    public ReviewVo retrieveReviewById(Long id) {
        return reviewMapper.retrieveReviewById(id);
    }

    // 사용자의 리뷰 게시글 조회
    public List<ReviewVo> retrieveMyReviews(Long id) {
        return reviewMapper.retrieveMyReviews(id);
    }

    // 리뷰 게시글 생성
    public ReviewVo createReview(ReviewVo review) {
        // 리뷰 게시글 생성
    	reviewMapper.createReview(review);

        // 생성된 리뷰 게시글의 ID를 이용해 리뷰 게시글을 다시 조회하여 반환
        Long id = review.getId();
        return reviewMapper.retrieveReviewById(id);
    }

    // 리뷰 게시글 일부 수정 (PATCH)
    public ReviewVo updateReview(ReviewVo review) {
        // 리뷰 업데이트 (일부 필드 수정)
        int updatedRows = reviewMapper.updateReview(review);
        
        if (updatedRows > 0) {
            return reviewMapper.retrieveReviewById(review.getId());
        } else {
            throw new RuntimeException("Failed to update review");
        }
    }

    // 리뷰 게시글 전체 수정 (PUT)
//    public ReviewVo replaceReview(ReviewVo review) {
//        // 리뷰 게시글 전체 수정
//        int updatedRows = reviewMapper.replaceReview(review);
//        
//        if (updatedRows > 0) {
//            return reviewMapper.retrieveReviewById(review.getId());
//        } else {
//            throw new RuntimeException("Failed to replace review");
//        }
//    }

    // 리뷰 게시글 삭제
    public void deleteReview(Long id) {
        int deletedRows = reviewMapper.deleteReview(id);
        if (deletedRows == 0) {
            throw new RuntimeException("Failed to delete review with ID: " + id);
        }
    }
}
