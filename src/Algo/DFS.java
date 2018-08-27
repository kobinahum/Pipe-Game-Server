package Algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFS<T> extends CommonSearcher<T> {
	
	@Override
	public Solution search(iSearchable<T> s) {
		HashSet<State<T>> visited = new HashSet<>(); 
		
		Stack<State<T>> stack = new Stack<>();
		State<T> state = s.getInitialState();
		stack.push(state);
		
		while (!stack.isEmpty()) {
			State<T> currState = stack.pop();
			this.addEvaluatedNodes();
			//System.out.println(this.getNumberOfNodesEvaluted());
			//if(currState.equals(s.getGoalState()))
			if (s.isGoalState(currState))
				return backTrace(currState);
			
			if (!visited.contains(currState)) {
				visited.add(currState);
				
				HashMap<Action, State<T>> map = s.getAllPossibleStates(currState);							
				
				for (Action a : map.keySet()) {
					State<T> n = map.get(a);
					
					if (!visited.contains(n)) {
						if(!stack.contains(n)) {
						stack.push(n);
						n.setCameFrom(currState);
						n.setLastAction(a);
						}
					}					
				}
			}
		}
		return null;
	}
}

