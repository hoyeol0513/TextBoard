
package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	
	public void runBoard() {
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> titles = new ArrayList<>();
		ArrayList<String> bodies = new ArrayList<>();
		ArrayList<Integer> numbers = new ArrayList<>(); //게시물 번호 저장소
		int no = 1; //게시물 번호
		
		while(true) {
			System.out.println("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("add")) {
				//중복되지 않는 게시물 번호가 필요함.
				//프로그램이 자동으로 만들어줘야 한다. (데이터베이스에도 필요함.)
				numbers.add(no);
				
				System.out.print("제목을 입력해주세요 : ");
				String title = sc.nextLine();
				System.out.print("내용을 입력해주세요 : ");
				String body = sc.nextLine();
				
				titles.add(title);
				bodies.add(body);
				
				System.out.print("게시물이 저장되었습니다.");
				no++; //게시물 번호 자동 증가
			} else if(cmd.equals("list")){
				for(int i =0; i<titles.size();i++) {
					int ano = numbers.get(i);
					String title = titles.get(i);
					String body = bodies.get(i);
					
					System.out.println("번호 : " + ano);
					System.out.println("제목 : " + title);
					System.out.println("====================================");				
					}
			} else if (cmd.equals("update")) {
				System.out.print("수정할 게시물 번호 : ");
				int targetNo = Integer.parseInt(sc.nextLine()); //게시물 번호
				int targetIndex = -1;//게시물번호 저장소 인덱스
				//0으로 지정하면 ArrayList 인덱스에 포함되기 때문에 안됨. -1이 적합.
				for(int i = 0; i<numbers.size(); i++) {
					int currentNo = numbers.get(i);
					if(targetNo == currentNo) {
						targetIndex = i; //i의 값이 중요함. 이걸 찾으면 해당 내용, 제목을 알 수 있음.
						break; //i의 값을 았기 때문에 종료.
					}
				}
				
				if(targetIndex == -1) { //게시물 번호를 발견하지 못했을 경우엔 -1 그대로이기 때문에
					System.out.println("없는 게시물입니다.");
				}else {
					System.out.print("새제목 : ");
					String title = sc.nextLine();
					System.out.print("새내용 : ");
					String body = sc.nextLine();
					
					titles.set(targetIndex,title); //게시물 제목 수정
					bodies.set(targetIndex,body); //게시물 내용 수정		
					System.out.println("수정이 완료되었습니다.");
				}
				
			}
		}
	}
}
