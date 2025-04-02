package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.CommunityPhotoVo;

public interface CommunityPhotoMapper {
	
//	<insert id="insertPhoto" parameterType="CommunityPhotoVo">
    int insertPhoto(CommunityPhotoVo communityPhotoVo);
    
//  <select id="selectAllPhotoByCommunityId" parameterType="int" resultType="CommunityPhotoVo">
    List<CommunityPhotoVo> selectAllPhotoByCommunityId(int communitiesId);
    
//  <delete id="deletePhotoByCommunityId" parameterType="int">
    int deletePhotoByCommunityId(int communitiesId);

}