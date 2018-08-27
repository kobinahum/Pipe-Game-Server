package Algo;

import java.util.Collections;
import java.util.LinkedList;

public abstract class CommonSearcher<T> implements iSearcher<T> {
	private int evaluatedNodes;

	public CommonSearcher() {
		evaluatedNodes=0;
	}
	
	protected void addEvaluatedNodes() {this.evaluatedNodes++;}
	
	protected  Solution backTrace(State<T> goalState) {
		//System.out.println(goalState.getCost());
		State<T> s=goalState;
		LinkedList<Action> list=new LinkedList<Action>();//linked list or stack
		while(s.getCameFrom()!=null) {
			Action act=s.getLastAction();
			s=s.getCameFrom();
			list.add(act);
		}
		Collections.reverse(list);
		return new Solution(list);
	}
	@Override
	public int getNumberOfNodesEvaluted() {
		return evaluatedNodes;
	}
	
}
