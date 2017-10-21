
import java.util.Objects;
import java.util.Scanner;

import com.appollo.book.IBookService;
import com.appollo.book.imp.BookService;
import com.appollo.employee.IUserinfoService;
import com.appollo.employee.imp.UserinfoService;
import com.appollo.util.PropertiesUtil;

/**
 * 程序入口类
 * @author 谭洋
 *
 */
public class Main {

	private static Scanner scanner = new Scanner (System.in);
	private static IUserinfoService userinfoService=new UserinfoService();
	private static IBookService bookService=new BookService();
	
	/**
	 * 程序入口
	 * @author 谭洋
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {

		System.out.println("欢迎使用图书系统");
		int loginchance=Integer.parseInt(PropertiesUtil.get("login.chance"));
		
		
		int q_timer=0;
		String username=null;
		String password=null;
		int timer=0;
		while(true) {
			if(q_timer>=loginchance) {
				System.out.println("超出输入次数");
				return;
			}
			System.out.println("请输入用户名称");
			username=scanner.next();
			System.out.println("请输入密码");
			password=scanner.next();
			timer=userinfoService.getTimerByUserName(username);
			if(timer==loginchance) {
				System.out.println("账号已经锁定");
				return;
			}
			if(Objects.equals(password, userinfoService.getPasswordByUserName(username))) {
				userinfoService.resetTimerByUserName(username);
				System.out.println("登陆成功");
				break;
			}
			System.out.println("账号或密码错误");
			userinfoService.addTimerByUserName(username);
			q_timer++;
		}
		
		System.out.println("欢迎进入系统");
		System.out.println("1.员工管理系统");
		System.out.println("2.书籍管理系统");
		
			
		int choice=scanner.nextInt();
		if(choice==1) {
			UserinfoOperate(username);
		}else if(choice==2){
			BookOperate();
		}
	}
	
	
	/**
	 * 书籍操作
	 * @author 谭洋
	 * @return void
	 */
	private static void BookOperate() {
		System.out.println("1.查询书籍");
		System.out.println("2.修改书籍");
		System.out.println("3.删除书籍");
		System.out.println("4.添加书籍");
		
		int choice = scanner.nextInt();
		
		if(choice==1) {
			System.out.println("请输入要查询的书名");
			String name=scanner.next();
			System.out.println("请输入要查询的书的作者");
			String author=scanner.next();
			bookService.findByNameAndAuthor(name, author);
		}else if(choice==2) {
			bookService.updatePriceAndTotalByID();
		}else if(choice==3) {
			System.out.println("请输入要删除的书籍的id");
			String id=scanner.next();
			bookService.deleteByID(id);
			
		}else if(choice==4){
			bookService.insert();
		}else {
			System.out.println("输入代码错误将终止");
		}
		
	}

	/**
	 * 用户信息操作
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	private static void UserinfoOperate(String username) {
		
		System.out.println("1.更改密码");
		System.out.println("2.查看个人信息");
		System.out.println("3.添加用户");
		
		Scanner scanner=new Scanner(System.in);
		int choice=scanner.nextInt();
		if(choice==1) {
			System.out.println("请输入旧密码");
			String oldpassword=scanner.next();
			System.out.println("请输入新密码");
			String newpassword=scanner.next();
			System.out.println("请输入重复密码");
			String repeatpassword=scanner.next();
			System.out.println(oldpassword+"   "+newpassword+"  "+repeatpassword);
			userinfoService.updatePasswordByUserName(username, oldpassword, newpassword, repeatpassword);			
		}else if(choice==2){
			userinfoService.getByUserName(username);
		}else if(choice==3) {
			userinfoService.insert();
		}else {
			System.out.println("输入选项错误，系统将终止");
		}
		scanner.close();
	}

}






