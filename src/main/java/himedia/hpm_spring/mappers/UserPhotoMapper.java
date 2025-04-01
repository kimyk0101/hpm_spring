package himedia.hpm_spring.mappers;

import himedia.hpm_spring.repository.vo.UserPhotoVo;

public interface UserPhotoMapper {
	
//	<insert id="insertOrUpdatePhoto" parameterType="UserPhotoVo">
    int insertOrUpdatePhoto(UserPhotoVo userPhotoVo);
    
//  <select id="selectPhotoByUserId" parameterType="int" resultType="UserPhotoVo">
    UserPhotoVo selectPhotoByUserId(int usersId);
    
//  <delete id="deletePhotoByUserId" parameterType="int">
    int deletePhotoByUserId(int usersId);

}