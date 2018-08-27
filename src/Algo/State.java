package Algo;

/** use equals of T */
public class State<T> {
	private T state;
	private State<T> cameFrom;
	private double cost;
	private Action lastAction; 
	
	public State(T s) {
		this.state=s;
	}

@Override
public int hashCode() {
	return state.hashCode();
}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0==null)
			return false;
		@SuppressWarnings("unchecked")
		State<T> newState=(State<T>) arg0;
		return this.state.equals(newState.state);
	}
	
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> comeFrom) {
		this.cameFrom = comeFrom;
	}


	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}

	public Action getLastAction() {
		return lastAction;
	}

	public void setLastAction(Action lastAction) {
		this.lastAction = lastAction;
	}

}
