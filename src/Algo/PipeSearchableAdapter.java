	package Algo;

import java.util.HashMap;

public class PipeSearchableAdapter implements iSearchable<PipeGameState> {
	
	PipeGameState p;
	State<PipeGameState> goal;
	private boolean toStart;
	private boolean toFinish;
	
	public boolean isToStart() {
		return toStart;
	}


	public void setToStart(boolean toStart) {
		this.toStart = toStart;
	}


	public boolean isToFinish() {
		return toFinish;
	}


	public void setToFinish(boolean toFinish) {
		this.toFinish = toFinish;
	}


	public PipeGameState getP() {
		return p;
		
	}


	public void setP(PipeGameState p) {
		this.p = p;
	}
	
	public PipeSearchableAdapter(PipeGameState p) {
		this.p=p;
		toStart=false;
		toFinish=false;
		goal=null;
	}
	

	@Override
	public HashMap<Action, State<PipeGameState>> getAllPossibleStates(State<PipeGameState> s) {
		
		char[][] newBoard=new char[s.getState().board.length][s.getState().board[0].length];//copying the board
		for(int i=0;i<s.getState().board.length;i++) {
			for(int j=0;j<s.getState().board[0].length;j++) {
				if(!(s.getState().pointNoWhere(new Position(i, j), s.getState().getBoard()[i][j]))) {
					newBoard[i][j]=' ';
				}else
				newBoard[i][j]=s.getState().getBoard()[i][j];
				}
			}
		

		HashMap<Action, State<PipeGameState>> map=new HashMap<Action, State<PipeGameState>>();
			
			int count90=4;
			int count180=2;
			int realCount;
			Position p;
			Action a = null;
			//PipeGameState state=new PipeGameState(newBoard);
			
			for(int i=0;i<s.getState().board.length;i++) {
				for(int j=0;j<s.getState().board[0].length;j++) {
					PipeGameState state=new PipeGameState(newBoard);
					if((state.getBoard()[i][j]!=' ')) {
					if(((state.getBoard()[i][j]=='-')||(state.getBoard()[i][j]=='|'))&&(state.getBoard()[i][j]!=' '))
						realCount=count180;
					else if((state.getBoard()[i][j]=='s')||((state.getBoard()[i][j]=='g')||(state.getBoard()[i][j]==' ')))
							realCount=0;
						else realCount=count90;
						for(int k=0;k<realCount;k++) {
							p=new Position(i, j);
							//PipeGameState state=new PipeGameState(newBoard);
							state.rotate(p);
							if(!state.isFlowingToS(p))
								continue;
							/*if((s.getState().board[i][j]=='g')||(s.getState().board[i][j]=='s')) 
								a=new Action(p.toString()+" 0");
							else a=new Action(p.toString()+" one click");*/
							if((s.getState().board[i][j]=='g')||(s.getState().board[i][j]=='s')) 
								a=new Action(p.toString()+0);
							else a=new Action(p.toString()+k);
							State<PipeGameState> st=new State<PipeGameState>(state);
							st.getState().setLastIndex(p);
							st.setCameFrom(s);
							if(st.getCameFrom()!=null)
								st.setCost(st.getCameFrom().getCost()+(k+1));
							if(map.containsValue(st))
								continue;
							map.put(a,st);
							/*if(st.getState().StoG(p)) {
								this.setGoalState(st);
								break;
							}*/
							break;
						
					}
				}
				}
			}
			return map;
			}

		
	

	@Override
	public State<PipeGameState> getInitialState() {
		State<PipeGameState> s=new State<PipeGameState>(p);
		s.setCost(0);
		return s;
	}

	@Override
	public boolean isGoalState(State<PipeGameState> s) {
		//return s.getState().isFlowing();
		return s.getState().fromStoG((s.getState().findStart()));
	}
	
	public void setGoalState(State<PipeGameState> goal) {
		this.goal=goal;
	}
	public State<PipeGameState> getGoalState(){
		return this.goal;
	}
	
}
