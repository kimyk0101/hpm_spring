package himedia.hpm_spring.mappers;

import himedia.hpm_spring.repository.vo.UserPhotoVo;

public interface UserPhotoMapper {
	
//	<insert id="insertOrUpdatePhoto" parameterType="UserPhotoVo">
    int insertOrUpdatePhoto(UserPhotoVo userPhotoVo);
    
//  <select id="selectPhotoByUserId" parameterType="Long" resultType="UserPhotoVo">
    UserPhotoVo selectPhotoByUserId(Long usersId);
    
//  <delete id="deletePhotoByUserId" parameterType="Long">
    int deletePhotoByUserId(Long usersId);

}