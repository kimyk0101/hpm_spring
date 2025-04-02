package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.RestaurantPhotoVo;

public interface RestaurantPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="RestaurantPhotoVo">
    int insertPhoto(RestaurantPhotoVo restaurantPhotoVo);
    
//  <select id="selectAllPhotoByRestaurantId" parameterType="int" resultType="RestaurantPhotoVo">
    List<RestaurantPhotoVo> selectAllPhotoByRestaurantId(int restaurantsId);
    
//  <delete id="deletePhotoByRestaurantId" parameterType="int">
    int deletePhotoByRestaurantId(int restaurantsId);

}