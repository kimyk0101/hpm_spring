package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.CommunityPhotoVo;
import himedia.hpm_spring.repository.vo.RestaurantReviewPhotoVo;

public interface RestaurantReviewPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="RestaurantPhotoVo">
    int insertPhoto(RestaurantReviewPhotoVo restaurantPhotoVo);
    
//  <select id="selectAllPhotoByRestaurantId" parameterType="int" resultType="RestaurantPhotoVo">
    List<RestaurantReviewPhotoVo> selectAllPhotoByRestaurantId(int restaurantsId);
    
//  <delete id="deletePhotoByRestaurantId" parameterType="int">
    int deletePhotoByRestaurantId(int restaurantsId);
    
//	<select id="findPhotoById" parameterType="int" resultType="com.example.model.RestaurantPhoto">
    RestaurantReviewPhotoVo findPhotoById(int photoId);

//	<delete id="deletePhotoById" parameterType="int">
    int deletePhotoById(int photoId);      

}