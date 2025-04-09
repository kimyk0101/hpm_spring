package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.CommunityPhotoVo;
import himedia.hpm_spring.repository.vo.MountainPhotoVo;

public interface MountainPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="MountainPhotoVo">
    int insertPhoto(MountainPhotoVo mountainPhotoVo);
    
//  <select id="selectAllPhotoByMountainId" parameterType="int" resultType="MountainPhotoVo">
    List<MountainPhotoVo> selectAllPhotoByMountainId(int mountainPhotoVo);
    
//  <delete id="deletePhotoByMountainId" parameterType="int">
    int deletePhotoByMountainId(int mountainPhotoVo);
    
//	<select id="findPhotoById" parameterType="int" resultType="com.example.model.MountainPhoto">
    MountainPhotoVo findPhotoById(int photoId);

//	<delete id="deletePhotoById" parameterType="int">
    int deletePhotoById(int photoId); 
}