package Algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS<T> extends CommonSearcher<T> {

		private Queue<State<T>> q;
		private HashSet<State<T>> visited;
		private HashSet<State<T>> closed;
	@Override
	public Solution search(iSearchable<T> searchable) {
		
		q=new ArrayBlockingQueue<State<T>>(Integer.MAX_VALUE/100);
		visited=new HashSet<State<T>>();
		closed=new HashSet<State<T>>();
		State<T> initialState=searchable.getInitialState();
		initialState.setCameFrom(null);
		q.add(initialState);
		
		
		while(!q.isEmpty()) {
			State<T> s=q.poll();
			this.addEvaluatedNodes();
			//System.out.println(this.getNumberOfNodesEvaluted());
			//if(s.equals(searchable.getGoalState()))
			if(searchable.isGoalState(s))
				return backTrace(s);
			if(!closed.contains(s)) {
				visited.add(s);
				closed.add(s);
				HashMap<Action,State<T>> successors=searchable.getAllPossibleStates(s);
				for (Map.Entry<Action, State<T>> entry : successors.entrySet()) {
					if(!(visited.contains(entry.getValue()))) {//&&!q.contains(entry.getValue()))
						State<T> newNaber =entry.getValue();
						newNaber.setCameFrom(s);
						newNaber.setLastAction(entry.getKey());
						visited.add(newNaber);
						if(!q.contains(newNaber))//is it efficiency?
							q.add(newNaber);
				}
			}
				/*else {//entry is already in q
					for (State<T> state : q) {
						if(state.equals(entry.getValue())) {
							if(entry.getValue().getCost()<s.getCost()) {
								q.remove(state);
								visited.remove(state);
								q.add(entry.getValue());
								visited.add(entry.getValue());
								
							}
						}
						
					}
				}*/
			}
			
			
		}
		
		return null;
	}

}
