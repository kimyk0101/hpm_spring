package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.ReviewVo;

public interface ReviewMapper {

//	<select id="retrieveAllReviews" resultType="ReviewVo">	//	전체 리뷰 게시글 조회
	List<ReviewVo> retrieveAllReviews();
	
//	<select id="retrieveReviewById" parameterType="Long" resultType="ReviewVo">	//	특정 리뷰 게시글 조회
	ReviewVo retrieveReviewById(Long id);
	
//	<select id="retrieveMyReviews" parameterType="Long" resultType="ReviewVo">	// 	사용자의 리뷰 게시글 조회
	List<ReviewVo> retrieveMyReviews(Long id);
	
//	<insert id="createReview" parameterType="ReviewVo">	//	리뷰 게시글 작성
	int createReview(ReviewVo review);
		
//	<update id="updateReview" parameterType="ReviewVo">	//	리뷰 게시글 일부 수정
	int updateReview(ReviewVo review);
	
//	<update id="replaceReview" parameterType="ReviewVo">	//	기존 리뷰 게시글 전체 수정
//	int replaceReview(ReviewVo review);
	
//	<delete id="deleteReview" parameterType="Long">	//	리뷰 게시글 삭제
	int deleteReview(Long id);
}
