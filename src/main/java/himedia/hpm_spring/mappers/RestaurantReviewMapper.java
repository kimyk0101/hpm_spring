package himedia.hpm_spring.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import himedia.hpm_spring.repository.vo.CommunityVo;
import himedia.hpm_spring.repository.vo.RestaurantReviewVo;

public interface RestaurantReviewMapper {

//	<select id="retrieveAllReviews" resultType="rReviewVo">	//	전체 맛집 리뷰 게시글 조회
	List<RestaurantReviewVo> retrieveAllReviews();
	
//	<select id="retrieveReviewById" parameterType="Long" resultType="rReviewVo">	//	특정 맛집 리뷰 게시글 조회
	RestaurantReviewVo retrieveReviewById(Long id);
	
//	<select id="retrieveMyReviews" parameterType="Long" resultType="rReviewVo">	// 	사용자의 맛집 리뷰 게시글 조회
	List<RestaurantReviewVo> retrieveMyReviews(Long id);

//  <select id="retriveReviewsByKeyword" resultType="rReviewVo // 키워드 기반 리뷰 검색 
	List<RestaurantReviewVo> retrieveReviewsByKeyword(@Param("pattern") String pattern);
	
//	<insert id="createReview" parameterType="rReviewVo">	//	맛집 리뷰 게시글 작성
	int createReview(RestaurantReviewVo review);
		
//	<update id="updateReview" parameterType="rReviewVo">	//	맛집 리뷰 게시글 일부 수정
	int updateReview(RestaurantReviewVo review);
	
//	<delete id="deleteReview" parameterType="Map">	//	맛집 리뷰 게시글 삭제
	int deleteReview(Long id, Long usersId);
	
//	<update id="incrementViews" parameterType="Long">	 //	조회수 증가 메서드
    void incrementViews(Long id);
}
