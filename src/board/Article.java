package board;

public class Article {
	
	int id;
	String title;
	String body;
	//게시물 표현 데이터가 늘으면 여기에 저장!
	String regDate;
	String writer;
	int hit; //조회수
	
	public Article(int id, String title, String body, String regDate, String writer, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.writer = writer;
		this.hit = hit;
	}
	//eclipse의 Scource > Generate Constructor using Fields로 생성자 생성 가능
	//단축키 : alt + s + a
	

	
}
