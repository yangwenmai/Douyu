import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import douyu.http.Comet;
import douyu.mvc.Context;
import douyu.mvc.Controller;

@Controller
public class CometExample {
	public void index(Context c) {
		c.out("CometExample.html");
	}

	public void join(Context c) {
		c.setComet(new MyComet());
	}

	public static class MyComet implements Comet {
		private final static Set<MyComet> members = new CopyOnWriteArraySet<MyComet>();
		Context context;
		@Override
		public void onConnect(Context context) {
			members.add(this);
			this.context = context;
		}

		@Override
		public void onDisconnect() {
			members.remove(this);
		}

		@Override
		public void onError() {
		}

		@Override
		public void onMessage(String data) {
			if (data.indexOf("disconnect") >= 0)
				context.setComet(null);
			else {
				for (MyComet member : members) {
					try {
						member.context.getHttpResponse().getWriter().print(data);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
