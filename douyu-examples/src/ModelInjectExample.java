import java.io.PrintWriter;

import douyu.mvc.Controller;
import models.Consumer;

@Controller
public class ModelInjectExample {
	//ModelInjectExample?consumer.name=zhh&consumer.address.country=china&consumer.address.city=hangzhou

	public void index(Consumer consumer, PrintWriter out) {
		out.println("consumer=" + consumer);
	}
}
