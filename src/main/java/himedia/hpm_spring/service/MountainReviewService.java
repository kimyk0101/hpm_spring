package himedia.hpm_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.hpm_spring.mappers.MountainReviewMapper;
import himedia.hpm_spring.repository.vo.CommunityVo;
import himedia.hpm_spring.repository.vo.MountainReviewVo;

@Service
public class MountainReviewService {

    @Autowired
    private MountainReviewMapper mReviewMapper;
    
    @Autowired
	private MountainPhotoService mountainPhotoService;

    // 모든 리뷰 게시글 조회
    public List<MountainReviewVo> retrieveAllReviews() {
        return mReviewMapper.retrieveAllReviews();
    }

    // 특정 리뷰 게시글 조회
    public MountainReviewVo retrieveReviewById(Long id) {
        return mReviewMapper.retrieveReviewById(id);
    }

    // 사용자의 리뷰 게시글 조회
    public List<MountainReviewVo> retrieveMyReviews(Long id) {
        return mReviewMapper.retrieveMyReviews(id);
    }
    
    // 키워드 기반 게시글 조회
 	public List<MountainReviewVo> retrieveReviewsByKeyword(String keyword) {
 		String pattern = "(^|[^가-힣a-zA-Z0-9])" + keyword + "([^가-힣a-zA-Z0-9]|$)";
 		return mReviewMapper.retrieveReviewsByKeyword(pattern);
 	}


    // 리뷰 게시글 생성
    public MountainReviewVo createReview(MountainReviewVo review) {
        // 리뷰 게시글 생성
    	mReviewMapper.createReview(review);

        // 생성된 리뷰 게시글의 ID를 이용해 리뷰 게시글을 다시 조회하여 반환
        Long id = review.getId();
        return mReviewMapper.retrieveReviewById(id);
    }

    // 리뷰 게시글 일부 수정 (PATCH)
    public MountainReviewVo updateReview(MountainReviewVo review) {
        // 리뷰 업데이트 (일부 필드 수정)
        int updatedRows = mReviewMapper.updateReview(review);
        
        if (updatedRows > 0) {
            return mReviewMapper.retrieveReviewById(review.getId());
        } else {
            throw new RuntimeException("Failed to update review");
        }
    }

    // 리뷰 게시글 삭제
    public void deleteReview(Long id, Long usersId) {
    	
    	// 게시글 이미지 먼저 삭제
    	mountainPhotoService.deletePhotoByMountainId(id.intValue());
    	
        int deletedRows = mReviewMapper.deleteReview(id, usersId);
        
        if (deletedRows == 0) {
            throw new RuntimeException("Failed to delete review with ID: " + id + " for user ID: " + usersId);
        }
    }
    
}
