package Algo;


import java.util.HashMap;

public interface iSearchable<T> {
	
		State<T> getInitialState();
		boolean isGoalState(State<T> s);
		HashMap<Action,State<T>> getAllPossibleStates(State<T> s);
		State<PipeGameState> getGoalState();
}
