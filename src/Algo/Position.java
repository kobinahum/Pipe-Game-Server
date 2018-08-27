package Algo;

public class Position {
	int row, col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	@Override
	public String toString() {
		return "("+this.row+","+this.col+")";
	}
	public boolean equals(Position p) {
		if(this.col==p.col&&this.row==p.row)
			return true;
		return false;
	}
}

