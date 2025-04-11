package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.RestaurantReviewPhotoVo;

public interface RestaurantReviewPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="rReviewPhotoVo">
    int insertPhoto(RestaurantReviewPhotoVo rReviewPhotoVo);
    
//  <select id="selectAllPhotoByRestauranstId" parameterType="Long" resultType="rReviewPhotoVo">
    List<RestaurantReviewPhotoVo> selectAllPhotoByRestaurantsId(Long restaurantsId);
    
//  <delete id="deletePhotoByRestaurantsId" parameterType="Long">
    int deletePhotoByRestaurantsId(Long restaurantsId);
    
//	<select id="findPhotoById" parameterType="Long" resultType="com.example.model.RestaurantPhoto">
    RestaurantReviewPhotoVo findPhotoById(Long photoId);

//	<delete id="deletePhotoById" parameterType="Long">
    int deletePhotoById(Long photoId);      
}