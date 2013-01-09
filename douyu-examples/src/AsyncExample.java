import java.io.PrintWriter;
import java.util.Date;

import douyu.mvc.Async;
import douyu.mvc.Context;
import douyu.mvc.Controller;

@Controller
public class AsyncExample {
	@Async
	public void asyncAction(Context c, PrintWriter out) {
		out.println("before invokeLongtimeService..."+Thread.currentThread());
		invokeLongtimeService(c, out);
		out.println("after invokeLongtimeService...");
	}

	private void invokeLongtimeService(Context c, PrintWriter out) {
		out.println("invokeLongtimeService...");
		out.println("at "+ new Date());
		
		int seconds = 2;
		out.println("sleep "+seconds+" seconds...");
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		out.println("at "+ new Date());
	}
	
	@Async
	public void asyncAction2(PrintWriter out) {
		out.println("invoke asyncAction2..."+Thread.currentThread());
	}
	
	public void action3(PrintWriter out) {
		out.println("invoke action3..."+Thread.currentThread());
	}
}
