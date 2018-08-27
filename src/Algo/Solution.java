package Algo;

import java.util.LinkedList;
import java.util.List;


public class Solution {
	private List<Action> solution;
	
	public Solution(List<Action> solution) {
		this.solution = new LinkedList<>();
		this.solution.addAll(solution);
		
	}

	public List<Action> getSolution() {
		return solution;
	}
	
	 
}
