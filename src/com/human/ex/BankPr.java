package com.human.ex;

//계정정보
class Account{
	public static int totalCount=0;
	public int money=0;
	public String id=null;//객체이기 때문에 null로 처리
	public String pw=null;//객체이기 때문에 null로 처리
	
	public Account() {
		totalCount++;
	}
	
	public Account(String id, String pw) {
		this();//생성자 호출
		this.id=id; this.pw=pw;
	}
	
	public boolean isLogin(String id, String pw) {
		boolean returnValue=false;
		
		//기존 이용자
		if(this.id.equals(id) && this.pw.equals(pw)) {
			returnValue=true;
		}
		//신규 이용자
		return returnValue;
	}
	
	//화면출력
	public void display() {
		System.out.println("-----------");
		//static을 사용했기 때문에 클래스명을 붙히면 좋다.
		System.out.println("총 계정의 수 : "+Account.totalCount);
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		System.out.println("money : "+money);
		System.out.println("-----------");
	}
	
	//입금
	public void add(int money) {
		this.money=this.money+money;
	}
	
	//출금
	public void minus(int money) {
		this.money=this.money-money;
	}
	
	
	public void menu() {
		
		java.util.Scanner sc=new java.util.Scanner(System.in);
		String inputString=null;
		
		boolean flag=true;
		
		while(flag) {
			System.out.println("1.입금  2.출금  3.종료");
			inputString=sc.nextLine();
			
			switch(inputString) {
				//입금
				case "1":
					System.out.println("입금을 선택하셨습니다.");
					System.out.println("입금액을 입력하세요.");
					//입금액 입력
					int money=Integer.parseInt(sc.nextLine());
					add(money);
					display();
					break;
					
				//출금
				case "2":
					System.out.println("출금을 선택하셨습니다.");
					System.out.println("출금액을 입력하세요.");
					//출금액 입력
					money=Integer.parseInt(sc.nextLine());
					minus(money);
					display();
					break;			
				
				//종료
				case "3":
					System.out.println("종료를 선택하셨습니다.");
					System.out.println("시스템을 종료합니다.");
					flag=false;
					break;
					
				//잘못된 입력
				default:	
					System.out.println("잘못된 입력입니다.");
			}
		}
	}
}

class Bank{
	public static int totalCount=0;
	//은행이름
	public String name=null;
	//배열생성 : 계정
	public Account [] account=new Account[10];
	//배열의 수
	public int accountCount=0;
	//로그인 하지 않으면 -1, 로그인 하면 해당 인덱스
	public int accountCurrent=-1;
	
	public Bank() {
		totalCount++;
	}
	
	public Bank(String name) {
		totalCount++;
		this.name=name;
	}
	
	//아이디,패스워드 입력
	public void accountAdd() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.println("원하는 아이디를 입력하세요.");
		String id=sc.nextLine();
		
		//아이디 중복 확인
		if(isID(id)) {
			System.out.println("중복된 아이디입니다.");
			return;
		}
		
		System.out.println("원하는 패스워드를 입력하세요.");
		String pw=sc.nextLine();
		account[accountCount++]=new Account(id,pw);
	}
	
	
	//아이디 중복 비교
	private boolean isID(String id) {
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			//기존 배열의 아이디와 새로 입력한 아이디 비교
			if(account[i].id.equals(id)){
				//아이디가 중복
				returnValue=true;
			}				
		}
		//아이디가 중복이 아님
		return returnValue;
	}
	public void logout() {
		accountCurrent=-1;
		System.out.println("로그아웃 됐습니다.");
	}
	
	public boolean Login(String id, String pw) {		
		//아이디와 패스워드가 같은사람이 존재하면 true 아니면 false
		//true이면 로그인 됨, false이면 로그인 안됨.
		//isLogin(id,pw)
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			//기존 배열의 아이디와 새로 입력한 아이디 비교
			if(account[i].isLogin(id,pw)){
				//아이디가 중복
				returnValue=true;
				accountCurrent=i;
				break;
			}				
		}
		//아이디가 중복이 아님
		return returnValue;
	}
	
	//화면출력
	public void displayAll() {
		System.out.println("총 은행의 수 : "+Bank.totalCount);
		System.out.println("현재 은행의 이름 : "+name);
		for(int i=0;i<accountCount;i++) {
			account[i].display();
		}
	}

	public void useAccount() {		
		if(accountCurrent!=-1) {
			account[accountCurrent].menu();
		}else {
			System.out.println("잘못된 로그인 입니다.");
		}
	}
	
	public void menu() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		boolean flag=true;
		//은행의 메뉴
		//1.계정등록  2.모든계정 출력  3.계정 로그인  4.계정 로그아웃  5.종료
		while(flag) {
			
			System.out.println
			("1.계정등록  2.모든계정 출력  3.계정 로그인"
					+ " 4.계정 로그아웃  5.계정삭제  6.종료");
			String stringInput=sc.nextLine();
			
			switch(stringInput) {
				case "1":
					accountAdd();
					displayAll();
					break;
					
				case "2":
					displayAll();
					break;
					
				case "3":
					System.out.println("아이디를 입력하세요.");
					String id=sc.nextLine();
					System.out.println("패스워드를 입력하세요.");
					String pw=sc.nextLine();
					
					if(Login(id,pw)) {
						System.out.println("로그인 하셨습니다.");
						useAccount();
					}else {
						System.out.println("로그인 실패하셨습니다");
					}
					break;
					
				case "4":
					logout();
					break;
					
				case "5":
					if(accountCurrent!=-1) {
						System.out.println("계정삭제");
						//System.out.println("아이디를 입력하세요.");
						//id=sc.nextLine();
						System.out.println("패스워드를 입력하세요.");
						pw=sc.nextLine();					
						delete(pw);
					}else {
						System.out.println("로그인하세요.");
					}
					break;
					
				case "6":
					System.out.println("시스템을 종료합니다.");
					flag=false;
					break;
					
				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}
	}
	
	public void delete(String pw) {
		delete(account[accountCurrent].id,pw);
	}
	
	public void delete(String id, String pw) {
		boolean flag=false;
		for(int i=0;i<accountCount;i++) {
			if(account[i].isLogin(id,pw)) {
				accountCurrent=i;
				flag=true;
				break;	
			}			
		}
		
		if(flag) {//찾으면 삭제
			//삭제작업
			for(int i=accountCurrent;i+1<accountCount;i++) {
				account[i]=account[i+1];
			}
			Account.totalCount--;
			accountCount--;
			accountCurrent=-1;
			System.out.println("삭제하였습니다.");
			
		}else {
			System.out.println("삭제 실패");
		}
	}
}

class ATM{
	Bank shinhan=new Bank("신한은행");
	Bank gookmin=new Bank("국민은행");
	Bank NH=new Bank("농협은행");
	Bank Korea=new Bank("한국은행");
	
	public void menu(){
		boolean flag=true;
		while(flag) {
			
			System.out.println("이용하실 은행을 선택하세요.");
			System.out.println
			("1.신한은행  2.국민은행  3.농협은행  4.한국은행  5.종료");
			java.util.Scanner sc=new java.util.Scanner(System.in);
			String bankchoice=sc.nextLine();
			
			switch(bankchoice) {			
				case "1":					
					System.out.println("신한은행을 선택하셨습니다.");					
					shinhan.menu();
					break;
					
				case "2":					
					System.out.println("국민은행을 선택하셨습니다.");					
					gookmin.menu();
					break;
					
				case "3":					
					System.out.println("농협은행을 선택하셨습니다.");
					NH.menu();
					break;
					
				case "4":					
					System.out.println("한국은행을 선택하셨습니다.");
					Korea.menu();
					break;
					
				case "5":
					System.out.println("이용해주셔서 감사합니다.");
					flag=false;
					break;
					
				default:
					System.out.println("잘 못 입력하셨습니다.");
					System.out.println("다시 입력하십시오.");					
					break;
					
			}
		}
		
	}
}

public class BankPr {

	public static void main(String[] args) {
		
		//Account ac=new Account("park","1234");
		//ac.menu();
		
		ATM atm=new ATM();
		atm.menu();
		
		/*Bank bk=new Bank("한국은행");
		bk.menu();*/
		
		
		
		
		
		//계정등록 및 모든 계정출력 작업
		/*bk.accountAdd();
		bk.displayAll();
		bk.accountAdd();
		bk.displayAll();
		bk.accountAdd();
		bk.displayAll();*/
		
		//로그인
		//1. 사용자에게 id와 pw를 입력받는다.
		//2. 기존 사용자가 맞는지 확인
		//3. 로그인한 사용자가 사용할 수 있도록 설정.
		//account배열 중 어떤 account를 사용할건지 선택
		//4. 로그인 사용자 계정 사용
		
		
		
		
		
		
		
		
		
	}
}
