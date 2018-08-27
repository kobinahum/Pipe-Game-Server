package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServer implements iServer {
	
	private ServerSocket server;
	private volatile boolean  stop;
	private iClientHandler myCh;
	private int port;
	private ExecutorService clientsPool;
	private PriorityQueue<Socket> dispatcheQueue;
	
	public ThreadedServer(int port) {
		this.port=port;
		this.stop=false;
		dispatcheQueue=new PriorityQueue<Socket>(stringLenComp);
	}
	
	
	@Override
	public void start(iClientHandler ch) {
		this.runServer(ch);
	}

	@Override
	public void stop() {
		stop=true;

	}
	
	public void runServer(iClientHandler ch) {
		myCh=ch;
		try {
			server=new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			server.setSoTimeout(100000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientsPool=Executors.newFixedThreadPool(4);
		while(!stop) {
			try {
				Socket aClient=server.accept();
				dispatcheQueue.add(aClient);
				clientsPool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							Socket client=dispatcheQueue.poll();
							myCh.handleClient(client.getInputStream(), client.getOutputStream());
							client.getInputStream().close();
							client.getOutputStream().close();
							client.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
			
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(clientsPool.isShutdown())
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	Comparator<Socket> stringLenComp =new Comparator<Socket>() {
		public int compare(Socket clientA, Socket clientB) 
	{
			BufferedReader br1;
			BufferedReader br2;
			int countLen1=0;
			int countLen2=0;
			try {
				br1=new BufferedReader(new InputStreamReader(clientA.getInputStream()));
				br2=new BufferedReader(new InputStreamReader(clientB.getInputStream()));
				return br1.readLine().length()-br2.readLine().length();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
			
	};
	};

}
