package Server;

import java.util.HashMap;

import Algo.Solution;

public interface CacheManager {

	public HashMap<Integer, Solution> getMySolves();

	public boolean saveALevel(Integer level, Solution sol);
	public Solution searchLevel(Integer level);

}
