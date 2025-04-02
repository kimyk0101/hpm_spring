package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.ReviewPhotoVo;

public interface ReviewPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="ReviewPhotoVo">
    int insertPhoto(ReviewPhotoVo reviewPhotoVo);
    
//  <select id="selectAllPhotoByReviewId" parameterType="int" resultType="ReviewPhotoVo">
    List<ReviewPhotoVo> selectAllPhotoByReviewId(int reviewsId);
    
//  <delete id="deletePhotoByReviewId" parameterType="int">
    int deletePhotoByReviewId(int reviewsId);

}