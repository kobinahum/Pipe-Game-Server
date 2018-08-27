package Algo;

public class PipeStateGrader implements StateGrader<PipeGameState> {
	
	/*int overAllGrade;
	public int getOverAllGrade() {
		return overAllGrade;
	}
	public void setOverAllGrade(int overAllGrade) {
		this.overAllGrade = overAllGrade;
	}*/
	//public  PipeStateGrader() {}
	@Override
	public int grade(State<PipeGameState> s) {
		//setOverAllGrade((s.getState().getBoard().length*2)+(s.getState().getBoard()[0].length));
		int distance;
		distance=s.getState().howFarFromG(s.getState().getLastIndex());
		return -1*(distance+(int)s.getCost());
	}

}
