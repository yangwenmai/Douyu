import java.io.PrintWriter;
import douyu.mvc.Controller;

@Controller
public class HelloWorld {
	public void index(PrintWriter out) {
		out.println("Hello Douyu World!");
	}
}