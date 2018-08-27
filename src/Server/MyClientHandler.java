package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Algo.Action;
import Algo.BestFirstSearch;
import Algo.PipeGameState;
import Algo.PipeSearchableAdapter;
import Algo.PipeSolver;
import Algo.Solution;
import Algo.Solver;
import Algo.iSearchable;
import Algo.iSearcher;
 

public class MyClientHandler implements iClientHandler {
	
	//private volatile boolean stop;
	private BufferedReader clientInput;
	private Solver ps;
	private CacheManager cm;
	private PrintWriter serverOutput;
	//private HashMap<String, Runnable> myMap;//maps between a command to a method
	
	public MyClientHandler() {
		cm=new MyCacheManager();
		ps=new PipeSolver();
		//stop=false;
		/*this.myMap=new HashMap();
		myMap.put("getScore",()->getScore()); //runnable-like functional interface. lambda expression
		myMap.put("addScore", ()->addScore());
		myMap.put("getSolve", ()->getSolve());*/
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		
		this.clientInput=new BufferedReader(new InputStreamReader(inFromClient));
		//oos=new ObjectOutputStream(i)
		this.serverOutput= new PrintWriter(outToClient);
		readInput();
		
	}
 
	public void readInput() {//get the string with the command and run the needed method with the map
		
		ArrayList<char[]> board=new ArrayList<char[]>();
		String s=new String();
		try {
			s = clientInput.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		//sb.append(s+"\n");
		//System.out.println(s);
		while(!s.equals("done")) {
			board.add(s.toCharArray());
			//stringboard.add(s);
			sb.append(s+"\n");
			try {
				s=clientInput.readLine();
				//System.out.println(stringboard);
				//System.out.println(stringboard);
				//System.out.println(board.toString());
				//System.out.println(s);
				//sb.append(s+"\n");
				
				/*if (myMap.get(s)==null)
					System.out.println("null");
				else {
					myMap.get(s).run();
					//stop();
				}*/
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//stop=true;
			}
		}
		
		char[][] newBoard =new char[board.size()][board.get(0).length];
		for (int i=0; i<board.size(); i++) 
			newBoard[i]=board.get(i);
			
		PipeGameState pgs=new PipeGameState(newBoard);
		int levelHash =pgs.hashcode();
		//System.out.println("hash 1="+levelHash);
		Solution sol=cm.searchLevel(levelHash);
		if(sol!= null) {
			for (Action a : sol.getSolution()) {
				serverOutput.println(a.toString());
				serverOutput.flush();
			}
			serverOutput.println("done");
			serverOutput.flush();
		}
		else getSolve(board);
		
	}
	
	public void getSolve(ArrayList<char[]> board) {
	
		char[][] newBoard =new char[board.size()][board.get(0).length];
		for (int i=0; i<board.size(); i++) 
			newBoard[i]=board.get(i);
		
		
		
		PipeGameState p=new PipeGameState(newBoard);
		int hash=p.hashcode();
		//System.out.println("hash 2="+hash);
		//PipeSearchableAdapter psa=new PipeSearchableAdapter(p);
		//Solution sol=ps.solve(new BFS<PipeSearchableAdapter>(), psa);
		//Solution sol=ps.solve(sear, psa);

		
		
		iSearchable<PipeGameState> papp = new PipeSearchableAdapter(p);
		iSearcher<PipeGameState> sear =new BestFirstSearch<PipeGameState>();
		Solution sol=ps.solve(sear, papp);

		
		cm.saveALevel(hash, sol);//adding the solution to CM
		//if return false so didn't save
		for (Action a : sol.getSolution()) {
			serverOutput.println(a.toString());
			serverOutput.flush();
		}
		serverOutput.println("done");
		serverOutput.flush();
	}

	
	
	/*public void getScore() {}
	public void addScore() {}
	public void getSolve() {
		ArrayList<char[]> board=new ArrayList<char[]>();//holds a level
		String s = "hii";
		while(!s.equals("end")) {
			try {
				s=this.clientInput.readLine();
				//System.out.println(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!s.equals("end"))
				board.add(s.toCharArray());
		}
		char[][] newBoard =new char[board.size()][board.get(0).length];
		for (int i=0; i<board.size(); i++)
			newBoard[i]=board.get(i);
		PipeGameState p=new PipeGameState(newBoard);
		PipeSearchableAdapter psa=new PipeSearchableAdapter(p);
		Solution sol=ps.solve(new BFS(), psa);
		stop=true;
		for (Action a : sol.getSolution()) {
			System.out.println(a.toString());
			serverOutput.println(a.toString());
			serverOutput.flush();
		}
		
		
	}*/

	/*@Override
	public void stop() {
		this.stop=true;
		
	}*/
	
}

