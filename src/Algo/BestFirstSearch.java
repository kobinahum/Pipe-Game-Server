package Algo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch<T> extends CommonSearcher<T> {

	@Override
	public Solution search(iSearchable<T> searchAble) {

		//PriorityQueue that takes lowest distance
		PriorityQueue<State<T>> initStates = new PriorityQueue<>(new Comparator<State<T>>() {
			@Override
			public int compare(State<T> o1, State<T> o2) {
				return (int) ((int) o1.getCost() - (int) o2.getCost());
			}
		});
		// OPEN - a set that eventually will contain every visited state
		HashSet<State<T>> visitedStates = new HashSet<>();// CLOSED
		//adding the first state to the queue
		State<T> firstState = searchAble.getInitialState();
		initStates.add(firstState);
		
		while (!initStates.isEmpty()) {

			// polling out the closest state that possible from the queue
			State<T> currentState = initStates.poll();
			//System.out.println(this.getNumberOfNodesEvaluted());

			// checking if the polled state is our goal
			//if (currentState.equals(searchAble.getGoalState())) {
			if ((searchAble.isGoalState(currentState))) {
				return backTrace(currentState);
			}
			//checking if the polled state already exist in our visited set. continue in case it doesn't
			if (!visitedStates.contains(currentState)) {
				// creating a map between the actions and the current state Successor's
				HashMap<Action, State<T>> map = searchAble.getAllPossibleStates(currentState);
				// for each successor:
				for (Action a : map.keySet()) {
					State<T> n = map.get(a);
					if (!visitedStates.contains(n) && !initStates.contains(n)) {
						n.setCost(currentState.getCost() + 1);//getWeightfunc(x,y)
						initStates.add(n);
						n.setCameFrom(currentState);
						n.setLastAction(a);
					}
					else {
						if (!initStates.contains(n)) {
							if (n.getCost() > currentState.getCost() + 1)
								n.setCost(currentState.getCost() + 1);
						}
					}
				}
				visitedStates.add(currentState);
			}

		}

		return null;
	}

}