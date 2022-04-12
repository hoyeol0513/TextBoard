package board;

public class Article {
	
	int id;
	String title;
	String body;
	
	//eclipse의 Scource > Generate Constructor using Fields로 생성자 생성 가능
	public Article(int id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}
	

	
}
