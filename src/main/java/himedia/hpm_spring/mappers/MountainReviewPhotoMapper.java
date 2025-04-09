package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.CommunityPhotoVo;
import himedia.hpm_spring.repository.vo.MountainReviewPhotoVo;

public interface MountainReviewPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="MountainPhotoVo">
    int insertPhoto(MountainReviewPhotoVo mountainPhotoVo);
    
//  <select id="selectAllPhotoByMountainId" parameterType="int" resultType="MountainPhotoVo">
    List<MountainReviewPhotoVo> selectAllPhotoByMountainId(int mountainPhotoVo);
    
//  <delete id="deletePhotoByMountainId" parameterType="int">
    int deletePhotoByMountainId(int mountainPhotoVo);
    
//	<select id="findPhotoById" parameterType="int" resultType="com.example.model.MountainPhoto">
    MountainReviewPhotoVo findPhotoById(int photoId);

//	<delete id="deletePhotoById" parameterType="int">
    int deletePhotoById(int photoId); 
}