import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import douyu.http.WebSocket;
import douyu.mvc.Context;
import douyu.mvc.Controller;

@Controller
public class WebSocketExample {
	public void index(Context c) {
		c.out("WebSocketExample.html");
	}

	public void join(Context c) {
		c.setWebSocket(new MyWebSocket());
	}

	public static class MyWebSocket implements WebSocket {
		private final static Set<MyWebSocket> members = new CopyOnWriteArraySet<MyWebSocket>();

		private Outbound outbound;

		@Override
		public void onConnect(Outbound outbound) {
			this.outbound = outbound;
			members.add(this);
		}

		@Override
		public void onDisconnect() {
			members.remove(this);
		}

		@Override
		public void onMessage(int type, String data) {
			if (data.indexOf("disconnect") >= 0)
				outbound.close();
			else {
				for (MyWebSocket member : members) {
					try {
						member.outbound.send(type, data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		@Override
		public void onMessage(int type, byte[] data) {
		}
	}
}
