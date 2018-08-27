package Algo;

public class Action {
	private String action;
	
	public Action(String action) {
		this.setAction(action);
	}
	
	@Override
	public String toString() {
		return this.action;
	}
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		return result;
	}*/
}
