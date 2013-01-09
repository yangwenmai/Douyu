//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadFactory;
//
//import javax.servlet.AsyncContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;
//
///**
// * 模拟Facebook BigPipe异步展现页面
// * @author yongboy
// * @date 2011-2-21
// * @version 1.0
// */
//@douyu.mvc.Controller
//public class BigPipeTest {
//	private static final long serialVersionUID = 14526556595656565L;
//	private static final Log log = LogFactory.getLog(BigPipeTest.class);
//	private static final Executor executor;
//
//	static {
//		executor = Executors.newFixedThreadPool(500, new ThreadFactory() {
//			public Thread newThread(Runnable r) {
//				Thread thread = new Thread(r);
//				thread.setName("BigPipe Thread " + thread.getId());
//				thread.setPriority(Thread.MAX_PRIORITY);
//
//				return thread;
//			}
//		});
//	}
//
//	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setHeader("Connection", "Keep-Alive");
//		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//
//		PrintWriter out = response.getWriter();
//
//		out.println(docType);
//		out.println(headPart);
//		out.println(bodyPart);
//
//		out.flush();
//
//		// 这里模拟把页面组件部分计算工作交由异步线程完成
//		executor.execute(new AsyncRunnable(request.startAsync(request, response)));
//	}
//
//	/**
//	 * 定义工作线程用于处理异步的内容输出
//	 * @author yongboy
//	 * @date 2011-2-21
//	 * @version 1.0
//	 */
//	private static class AsyncRunnable implements Runnable {
//		private final AsyncContext asyncContext;
//		private int times = 1;
//
//		public AsyncRunnable(final AsyncContext asyncContext) {
//			this.asyncContext = asyncContext;
//		}
//
//		public void run() {
//			try {
//				// 设置超时为20s，系统默认超时时间为10s
//				asyncContext.setTimeout(20L * 1000L);
//				PrintWriter out = asyncContext.getResponse().getWriter();
//
//				// 模拟按照页面组件重要程度按照顺序计算
//				bussizeMethod(out, "header", "这里是主页LOGO区域");
//				bussizeMethod(out, "left", genStrings("这里是内容文字", 160, false));
//				bussizeMethod(out, "right", genStrings("肯德基在百事可乐杯子添加其他品牌可乐", 41, true));
//				bussizeMethod(out, "leftLeft", genStrings("肯德基在百事可乐杯子添加其他品牌可乐", 10, true));
//				bussizeMethod(out, "leftRight", genStrings("肯德基在百事可乐杯子添加其他品牌可乐", 10, true));
//
//				// 补齐页面标签
//				outFinish(out);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			// 结束当前异步请求
//			try {
//				asyncContext.complete();
//			} catch (Exception e) {
//				// 可能因为出现超时（大于默认的超时时间10s）异常
//				e.printStackTrace();
//			}
//		}
//
//		private void bussizeMethod(PrintWriter writer, String id, String content) {
//			//模拟耗时
//			try {
//				Thread.sleep(1000 * (times++));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//			pagelet(writer, id, content);
//		}
//
//		private void pagelet(PrintWriter writer, String id, String content) {
//			if (writer.checkError())
//				return;
//			writer.write("<script>" + "show(\"" + id + "\", \"" + content + "\");" + "</script>\n");
//			writer.flush();
//		}
//
//		private void outFinish(PrintWriter writer) {
//			if (writer.checkError())
//				return;
//			writer.println("</body>");
//			writer.println("</html>");
//			writer.flush();
//			writer.close();
//		}
//
//		private String genStrings(String ori, int num, boolean line) {
//			if (num < 1)
//				return ori;
//			StringBuilder sb = new StringBuilder();
//
//			for (int i = 0; i < num; i++) {
//				sb.append(ori);
//				if (line) {
//					sb.append("<br/>");
//				}
//			}
//
//			return sb.toString();
//		}
//	}
//
//	private static final String docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
//	private static final String headPart = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n"
//			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"
//			+ "<title>Bigpipe Demo</title>\n"
//			+ "<link href=\"css/grid.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen\"/>\n"
//			+ "<script type=\"text/javascript\">function show(id,text) { var b=document.getElementById(id); b.innerHTML = text; }</script>\n"
//			+ "</head>";
//	private static final String bodyPart = "<body>\n" + "<div class='row'>\n"
//			+ "<div class='column grid_12'><div class='container' id='header'>loading ...</div></div>\n" + "</div>\n"
//
//			+ "<div class='row'>\n" + "	<div class='column grid_8'><div class='container' id='left'>loading ...</div>\n"
//			+ "		<div class='row'>\n"
//			+ "			<div class='column grid_4'><div class='container' id='leftLeft'>loading ...</div></div>\n"
//			+ "		<div class='column grid_4'><div class='container' id='leftRight'>loading ...</div></div>\n" + "	</div>\n"
//			+ "	</div>\n" + "	<div class='column grid_4'><div class='container' id='right'>loading ...</div></div>\n" + "</div>";
//}
