package Algo;

public interface Solver {

	//public  <T> Solution solve(iSearcher searcher,iSearchable<T> s);
	public <T> Solution solve(iSearcher<T> searcher,iSearchable<T> s);
	
	
	
}
