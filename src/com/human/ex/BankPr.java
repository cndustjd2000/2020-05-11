package com.human.ex;

//��������
class Account{
	public static int totalCount=0;
	public int money=0;
	public String id=null;//��ü�̱� ������ null�� ó��
	public String pw=null;//��ü�̱� ������ null�� ó��
	
	public Account() {
		totalCount++;
	}
	
	public Account(String id, String pw) {
		this();//������ ȣ��
		this.id=id; this.pw=pw;
	}
	
	public boolean isLogin(String id, String pw) {
		boolean returnValue=false;
		
		//���� �̿���
		if(this.id.equals(id) && this.pw.equals(pw)) {
			returnValue=true;
		}
		//�ű� �̿���
		return returnValue;
	}
	
	//ȭ�����
	public void display() {
		System.out.println("-----------");
		//static�� ����߱� ������ Ŭ�������� ������ ����.
		System.out.println("�� ������ �� : "+Account.totalCount);
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		System.out.println("money : "+money);
		System.out.println("-----------");
	}
	
	//�Ա�
	public void add(int money) {
		this.money=this.money+money;
	}
	
	//���
	public void minus(int money) {
		this.money=this.money-money;
	}
	
	
	public void menu() {
		
		java.util.Scanner sc=new java.util.Scanner(System.in);
		String inputString=null;
		
		boolean flag=true;
		
		while(flag) {
			System.out.println("1.�Ա�  2.���  3.����");
			inputString=sc.nextLine();
			
			switch(inputString) {
				//�Ա�
				case "1":
					System.out.println("�Ա��� �����ϼ̽��ϴ�.");
					System.out.println("�Աݾ��� �Է��ϼ���.");
					//�Աݾ� �Է�
					int money=Integer.parseInt(sc.nextLine());
					add(money);
					display();
					break;
					
				//���
				case "2":
					System.out.println("����� �����ϼ̽��ϴ�.");
					System.out.println("��ݾ��� �Է��ϼ���.");
					//��ݾ� �Է�
					money=Integer.parseInt(sc.nextLine());
					minus(money);
					display();
					break;			
				
				//����
				case "3":
					System.out.println("���Ḧ �����ϼ̽��ϴ�.");
					System.out.println("�ý����� �����մϴ�.");
					flag=false;
					break;
					
				//�߸��� �Է�
				default:	
					System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}
}

class Bank{
	public static int totalCount=0;
	//�����̸�
	public String name=null;
	//�迭���� : ����
	public Account [] account=new Account[10];
	//�迭�� ��
	public int accountCount=0;
	//�α��� ���� ������ -1, �α��� �ϸ� �ش� �ε���
	public int accountCurrent=-1;
	
	public Bank() {
		totalCount++;
	}
	
	public Bank(String name) {
		totalCount++;
		this.name=name;
	}
	
	//���̵�,�н����� �Է�
	public void accountAdd() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.println("���ϴ� ���̵� �Է��ϼ���.");
		String id=sc.nextLine();
		
		//���̵� �ߺ� Ȯ��
		if(isID(id)) {
			System.out.println("�ߺ��� ���̵��Դϴ�.");
			return;
		}
		
		System.out.println("���ϴ� �н����带 �Է��ϼ���.");
		String pw=sc.nextLine();
		account[accountCount++]=new Account(id,pw);
	}
	
	
	//���̵� �ߺ� ��
	private boolean isID(String id) {
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			//���� �迭�� ���̵�� ���� �Է��� ���̵� ��
			if(account[i].id.equals(id)){
				//���̵� �ߺ�
				returnValue=true;
			}				
		}
		//���̵� �ߺ��� �ƴ�
		return returnValue;
	}
	public void logout() {
		accountCurrent=-1;
		System.out.println("�α׾ƿ� �ƽ��ϴ�.");
	}
	
	public boolean Login(String id, String pw) {		
		//���̵�� �н����尡 ��������� �����ϸ� true �ƴϸ� false
		//true�̸� �α��� ��, false�̸� �α��� �ȵ�.
		//isLogin(id,pw)
		boolean returnValue=false;
		for(int i=0;i<accountCount;i++) {
			//���� �迭�� ���̵�� ���� �Է��� ���̵� ��
			if(account[i].isLogin(id,pw)){
				//���̵� �ߺ�
				returnValue=true;
				accountCurrent=i;
				break;
			}				
		}
		//���̵� �ߺ��� �ƴ�
		return returnValue;
	}
	
	//ȭ�����
	public void displayAll() {
		System.out.println("�� ������ �� : "+Bank.totalCount);
		System.out.println("���� ������ �̸� : "+name);
		for(int i=0;i<accountCount;i++) {
			account[i].display();
		}
	}

	public void useAccount() {		
		if(accountCurrent!=-1) {
			account[accountCurrent].menu();
		}else {
			System.out.println("�߸��� �α��� �Դϴ�.");
		}
	}
	
	public void menu() {
		java.util.Scanner sc=new java.util.Scanner(System.in);
		boolean flag=true;
		//������ �޴�
		//1.�������  2.������ ���  3.���� �α���  4.���� �α׾ƿ�  5.����
		while(flag) {
			
			System.out.println
			("1.�������  2.������ ���  3.���� �α���"
					+ " 4.���� �α׾ƿ�  5.��������  6.����");
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
					System.out.println("���̵� �Է��ϼ���.");
					String id=sc.nextLine();
					System.out.println("�н����带 �Է��ϼ���.");
					String pw=sc.nextLine();
					
					if(Login(id,pw)) {
						System.out.println("�α��� �ϼ̽��ϴ�.");
						useAccount();
					}else {
						System.out.println("�α��� �����ϼ̽��ϴ�");
					}
					break;
					
				case "4":
					logout();
					break;
					
				case "5":
					if(accountCurrent!=-1) {
						System.out.println("��������");
						//System.out.println("���̵� �Է��ϼ���.");
						//id=sc.nextLine();
						System.out.println("�н����带 �Է��ϼ���.");
						pw=sc.nextLine();					
						delete(pw);
					}else {
						System.out.println("�α����ϼ���.");
					}
					break;
					
				case "6":
					System.out.println("�ý����� �����մϴ�.");
					flag=false;
					break;
					
				default:
					System.out.println("�߸��� �Է��Դϴ�.");
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
		
		if(flag) {//ã���� ����
			//�����۾�
			for(int i=accountCurrent;i+1<accountCount;i++) {
				account[i]=account[i+1];
			}
			Account.totalCount--;
			accountCount--;
			accountCurrent=-1;
			System.out.println("�����Ͽ����ϴ�.");
			
		}else {
			System.out.println("���� ����");
		}
	}
}

class ATM{
	Bank shinhan=new Bank("��������");
	Bank gookmin=new Bank("��������");
	Bank NH=new Bank("��������");
	Bank Korea=new Bank("�ѱ�����");
	
	public void menu(){
		boolean flag=true;
		while(flag) {
			
			System.out.println("�̿��Ͻ� ������ �����ϼ���.");
			System.out.println
			("1.��������  2.��������  3.��������  4.�ѱ�����  5.����");
			java.util.Scanner sc=new java.util.Scanner(System.in);
			String bankchoice=sc.nextLine();
			
			switch(bankchoice) {			
				case "1":					
					System.out.println("���������� �����ϼ̽��ϴ�.");					
					shinhan.menu();
					break;
					
				case "2":					
					System.out.println("���������� �����ϼ̽��ϴ�.");					
					gookmin.menu();
					break;
					
				case "3":					
					System.out.println("���������� �����ϼ̽��ϴ�.");
					NH.menu();
					break;
					
				case "4":					
					System.out.println("�ѱ������� �����ϼ̽��ϴ�.");
					Korea.menu();
					break;
					
				case "5":
					System.out.println("�̿����ּż� �����մϴ�.");
					flag=false;
					break;
					
				default:
					System.out.println("�� �� �Է��ϼ̽��ϴ�.");
					System.out.println("�ٽ� �Է��Ͻʽÿ�.");					
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
		
		/*Bank bk=new Bank("�ѱ�����");
		bk.menu();*/
		
		
		
		
		
		//������� �� ��� ������� �۾�
		/*bk.accountAdd();
		bk.displayAll();
		bk.accountAdd();
		bk.displayAll();
		bk.accountAdd();
		bk.displayAll();*/
		
		//�α���
		//1. ����ڿ��� id�� pw�� �Է¹޴´�.
		//2. ���� ����ڰ� �´��� Ȯ��
		//3. �α����� ����ڰ� ����� �� �ֵ��� ����.
		//account�迭 �� � account�� ����Ұ��� ����
		//4. �α��� ����� ���� ���
		
		
		
		
		
		
		
		
		
	}
}
