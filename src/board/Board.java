
package board;

import java.util.ArrayList;

import java.util.Scanner;

import board.util.MyUtil;

public class Board {

	ArrayList<Article> articles = new ArrayList<>();
	int no = 1; //게시물 번호
	Scanner sc = new Scanner(System.in);
	
	public Board() { //Board의 생성자 (나올 때 처음에 테스트 데이터를 갖고 있어라)
		makeTextdata();
	}


	public void runBoard() {
		
		while(true) {
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("help")) {
				printHelp();
				
			} else if(cmd.equals("add")) {
				//중복되지 않는 게시물 번호가 필요함.
				//프로그램이 자동으로 만들어줘야 한다. (데이터베이스에도 필요함.)
				addArticle();	
			} else if(cmd.equals("list")){
				ShowList(articles);	
			} else if (cmd.equals("update")) {
				updateArticle();
			} else if (cmd.equals("delete")) {
				deleteArticle();
			} else if (cmd.equals("search")) {
				searchArticle();
			} else if (cmd.equals("read")) {
				readArticle();
			}
		}
	}
	
	private void readArticle() {
		System.out.print("상세보기할 게시물 번호 : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		int targetIndex = getIndexofArticleNo(targetNo);
		
		if(targetIndex == -1) {
			System.out.println("없는 게시물입니다.");
		} else {
			Article article = articles.get(targetIndex);
			
			System.out.println("==== " + article.id +"번 게시물 ====");
			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("-------------------");
			System.out.println("내용 : " + article.body);
			System.out.println("-------------------");
			System.out.println("작성자 : " + article.writer);
			System.out.println("등록날짜 : " + article.regDate);
			System.out.println("===================");
		}   
		
	}


	private void makeTextdata() {
		//테스트 데이터 3개 미리 저장하기
		articles.add(new Article(1,"안녕하세요","내용1입니다.",MyUtil.getCurrentDate("yyyy년 MM월 dd일"),"익명",0));
		articles.add(new Article(2,"반갑습니다","내용2입니다.",MyUtil.getCurrentDate("yyyy년 MM월 dd일"),"익명",0));
		articles.add(new Article(3,"안녕안녕","내용3입니다.",MyUtil.getCurrentDate("yyyy년 MM월 dd일"),"익명",0));
	} //add하면 게시물 번호 4번부터 시작함.
	
	private void searchArticle() {
		System.out.print("검색 키워드를 입력해주세요 : ");
		String keyword = sc.nextLine();
				
		ArrayList<Article> searchedArticles = new ArrayList<>();
		
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).title.contains(keyword)) { //검색조건 : 문자가 포함되어있다면
				searchedArticles.add(articles.get(i)); //검색된 것만 담기
			}
		}
		
		ShowList(searchedArticles);
	}

	private void deleteArticle() {
		System.out.print("삭제할 게시물 번호 : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		int targetIndex = getIndexofArticleNo(targetNo);
		
		if(targetIndex == -1) {
			System.out.println("없는 게시물입니다.");
		}else {
			
			articles.remove(targetIndex);
			//번호도 지워야함.
			System.out.println("삭제가 완료되었습니다.");
			no--;
			ShowList(articles);
		}
		
	}
	private void updateArticle() {
		System.out.print("수정할 게시물 번호 : ");
		int targetNo = Integer.parseInt(sc.nextLine()); //게시물 번호
		
		int targetIndex = getIndexofArticleNo(targetNo);
		//게시물 번호를 발견했을 경우엔 targetIndex를 i로 지정
		
		if(targetIndex == -1) { //게시물 번호를 발견하지 못했을 경우엔 -1 그대로이기 때문에
			System.out.println("없는 게시물입니다.");
		}else {
			System.out.print("새제목 : ");
			String title = sc.nextLine();
			System.out.print("새내용 : ");
			String body = sc.nextLine();
			
			Article article = new Article(targetNo, title, body,"2021.11.11","익명",0);
			articles.set(targetIndex,article); //게시물 제목,내용 (article안에) 수정		
			
			System.out.println("수정이 완료되었습니다.");
			ShowList(articles);
		}
	}
	
	private void addArticle() {
		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = sc.nextLine();	
		String currentDate = MyUtil.getCurrentDate("yyyy년 MM월 dd일"); //현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		
		Article article = new Article(no, title, body,currentDate,"익명",0);
		articles.add(article);
		
		System.out.println("게시물이 저장되었습니다.");
		no++; //게시물 번호 자동 증가
		
	}
	private void printHelp() {
		System.out.println("add : 게시물 등록");
		System.out.println("list : 게시물 목록 조회");
		System.out.println("update : 게시물 수정");
		System.out.println("delete : 게시물 삭제");
		System.out.println("search : 게시물 검색");
		
	}
	public int getIndexofArticleNo(int targetNo) {
		int targetIndex = -1;//게시물번호 저장소 인덱스
		//0으로 지정하면 ArrayList 인덱스에 포함되기 때문에 안됨. -1이 적합.
		for(int i = 0; i<articles.size(); i++) {
			Article currentArticle = articles.get(i);
			if(targetNo == currentArticle.id) {
//				targetIndex = i; //i의 값이 중요함. 이걸 찾으면 해당 내용, 제목을 알 수 있음.
//				break; //i의 값을 았기 때문에 종료.
				return i;
			}
		}
		
		//return targetIndex;
		return -1; //for문을 다 돌았을 때까지 없으면 targetIndex를 -1로 반환
	}
	
	public void ShowList(ArrayList<Article> list) { 
		//메서드화 시킬 때 출력할 목록이 다를 수 있어서 매개변수가 필요하다는 것 인지하기
		//list는 전체 article일 수도 있고, 검색된 article만 일 수도 있고, 정렬된 article일 수도 있음.
		
		for(int i =0; i < list.size();i++) {
			Article article = list.get(i);
						
			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("작성자 : " + article.writer);
			System.out.println("등록날짜 : " + article.regDate);
			System.out.println("조회수 : " + article.hit);
			System.out.println("=====================");									
		}
	}
}
