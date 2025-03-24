package himedia.hpm_spring.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentVo {
	
	private Long id;				// 	댓글 고유번호
	private String content;			// 	댓글 내용
	private String upload_date;		//	댓글 게시 시간
	private String review_id;		//	산후기 게시글 작성자 아이디
	private String retaurant_id;	// 	맛집후기 게시글 작성자 아이디
	private String community_id;	// 	자유게시판 게시글 작성자 아이디
	private String users_id;			//	댓글 작성자 아이디
}
