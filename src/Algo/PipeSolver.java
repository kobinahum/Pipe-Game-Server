package Algo;

import java.util.LinkedList;
import java.util.List;

public class PipeSolver implements Solver {
	
	
	/*PipeGameState p;
	
	public PipeSolver(PipeGameState p) {
		this.p=p;
	}*/

	public PipeSolver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Solution solve(iSearcher<T> searcher, iSearchable<T> s) {
		PipeSearchableAdapter psa=(PipeSearchableAdapter)s;
		int [][] changes=new int[psa.getP().getBoard().length][psa.getP().getBoard()[0].length];
		//char[][] original=new char[psa.getP().getBoard().length][psa.getP().getBoard()[0].length];
		//Solution sol=searcher.search(psa);
		Solution sol=searcher.search(s);
		
		List<Action> list=new LinkedList<Action>();
		
		for(int i=0;i<sol.getSolution().size();i++) {
			Action a=sol.getSolution().get(i);
			String str=new String(a.toString());
			int x=str.charAt(1)-48;
			int y=str.charAt(3)-48;
			int count=str.charAt(5)-48;
			changes[x][y]+=count+1;
			
		}
		
		/*for(int i=0;i<sol.getSolution().size();i++) {
			Action a=sol.getSolution().get(i);
			String str=new String(a.toString());
			int x=str.charAt(1)-48;
			int y=str.charAt(3)-48;
			changes[x][y]++;
			
		}*/
		Position start=psa.p.findStart();
		Position finsih=psa.p.findFinish();
		changes[start.row][start.col]=0;
		changes[finsih.row][finsih.col]=0;
		Action act;
		String str;
		StringBuilder sb=new StringBuilder();
		for(int k=0;k<changes.length;k++) {
			for(int t=0;t<changes[0].length;t++) {
				if(changes[k][t]!=0) {
					sb.append(k+","+t+","+changes[k][t]);
					str=new String(sb);
					act=new Action(str);
					list.add(act);
					sb.setLength(0);
				}
			}
			
		}
		Solution finalSol=new Solution(list);
		return finalSol;
	}
	

	
}
