package himedia.hpm_spring.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import himedia.hpm_spring.repository.vo.CommunityVo;

@Mapper
public interface CommunityMapper {
//	<select id="selectAllCommunities" resultType="CommunityVo">	//	전체 게시물 조회
	List<CommunityVo> selectAllCommunities();
	
//	<select id="selectCommunityById" parameterType="Long" resultType="CommunityVo">	//	특정 게시물 조회
	CommunityVo selectCommunityById(Long id);
	
//	<select id="selectMyCommnunity" parameterType="Long" resultType="CommunityVo">	// 	본인이 작성한 게시물 조회
	CommunityVo selectMyCommnunity(Long id);
	
//	<insert id="insertCommunity" parameterType="CommunityVo">	//	게시물 작성
	int insertCommunity(CommunityVo community);
		
//	<update id="updateCommnunity" parameterType="CommunityVo">	//	게시물 수정
	int updateCommunity(CommunityVo community);
	
//	<delete id="deleteCommnunity" parameterType="Long">	//	게시물 삭제
	int deleteCommunity(Long id);
}
