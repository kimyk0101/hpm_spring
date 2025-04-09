package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.CommunityPhotoVo;
import himedia.hpm_spring.repository.vo.RestaurantPhotoVo;

public interface RestaurantPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="RestaurantPhotoVo">
    int insertPhoto(RestaurantPhotoVo restaurantPhotoVo);
    
//  <select id="selectAllPhotoByRestaurantId" parameterType="int" resultType="RestaurantPhotoVo">
    List<RestaurantPhotoVo> selectAllPhotoByRestaurantId(int restaurantsId);
    
//  <delete id="deletePhotoByRestaurantId" parameterType="int">
    int deletePhotoByRestaurantId(int restaurantsId);
    
//	<select id="findPhotoById" parameterType="int" resultType="com.example.model.RestaurantPhoto">
    RestaurantPhotoVo findPhotoById(int photoId);

//	<delete id="deletePhotoById" parameterType="int">
    int deletePhotoById(int photoId);      

}