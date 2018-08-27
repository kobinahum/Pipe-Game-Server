package Algo;

public interface iSearcher<T> {
	public Solution search(iSearchable<T> searchable);
	
	public int getNumberOfNodesEvaluted();
	
	//init 
}
