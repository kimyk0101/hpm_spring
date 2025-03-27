package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.CommunityVo;

public interface CommunityMapper {

//	<select id="retrieveAllCommunities" resultType="CommunityVo">	//	전체 게시글 조회
	List<CommunityVo> retrieveAllCommunities();
	
//	<select id="retrieveCommunityById" parameterType="Long" resultType="CommunityVo">	//	특정 게시글 조회
	CommunityVo retrieveCommunityById(Long id);
	
//	<select id="retrieveMyCommunities" parameterType="Long" resultType="CommunityVo">	// 	사용자의 게시글 조회
	List<CommunityVo> retrieveMyCommunities(Long id);
	
//	<insert id="createCommunity" parameterType="CommunityVo">	//	게시글 작성
	int createCommunity(CommunityVo community);
		
//	<update id="updateCommunity" parameterType="CommunityVo">	//	게시글 일부 수정
	int updateCommunity(CommunityVo community);
	
//	<update id="replaceCommunity" parameterType="CommunityVo">	//	기존 게시글 전체 수정
//	int replaceCommunity(CommunityVo community);
	
//	<delete id="deleteCommunity" parameterType="Map">	//	게시글 삭제
	int deleteCommunity(Long id, Long usersId);
}
