package Algo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class HillClimbing<T> extends CommonSearcher<T> {

    private long timeToRun;
    private StateGrader<T> grader;
    private ArrayList<State<T>> result;

    


    HillClimbing(StateGrader<T> grader, long timeToRun) {
        this.grader = grader;
        this.timeToRun = timeToRun;
        result=new ArrayList<State<T>>();
        
    }


    @Override
    public Solution search(iSearchable<T> searchable) {
        //Define the current state as an initial state
        State<T> next = searchable.getInitialState();
        result.add(next);
        //List<Action> list=new LinkedList<Action>();
        //Solution result = new Solution(list);
        
        long time0 = System.currentTimeMillis();


        //Loop until the goal state is achieved or no more operators can be applied on the current state:
        while (System.currentTimeMillis() - time0 < timeToRun) {
            HashMap<Action, State<T>> neighbors = searchable.getAllPossibleStates(next);

            if (Math.random() < 0.7) { // with a high probability
                // find the best one
                int grade = 0;
               for (Map.Entry<Action, State<T>> entry : neighbors.entrySet()) {
              //  for (State<T> t : array) {
                	//State<T> t=entry.getValue();
                	//int g=grader.grade(t);
            	  State<T> sun= entry.getValue();
                	int g=grader.grade(sun);
                    if (g < grade) {
                        grade = g;
                        sun.setLastAction(entry.getKey());
                        sun.setCameFrom(next);
                        next = sun;
                        result.add(sun);
                        if(searchable.isGoalState(sun))
                        	return backTrace(sun);
                        
                        //result.put(entry.getKey(), entry.getValue());
                        //list.add(entry.getKey());
                        //State<T> bestGuess=entry.getValue();
                        //add this step to the solution
                        //result.add
                        //result.add(step[0]);

                    }
                }
            } else {
            	Random       random    = new Random();
            	List<Action> keys      = new ArrayList<Action>(neighbors.keySet());
            	Action       randomKey = keys.get( random.nextInt(keys.size()) );
            	next     = neighbors.get(randomKey);
            	//int randomNum = ThreadLocalRandom.current().nextInt(0, neighbors.size());
               // next = array.get(new Random().nextInt(array.size()));
            	//next = neighbors.get(randomNum);
            	//next=neighbors.get
            }
        }
        //result=new Solution(list);
        //return result;
        return this.backTrace(result.get(result.size()-1));

    }
}