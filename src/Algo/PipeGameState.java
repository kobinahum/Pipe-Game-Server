package Algo;

public class PipeGameState {
	
	char[][] board;
	private Position lastIndex;
	
	public Position getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(Position lastIndex) {
		this.lastIndex = lastIndex;
	}
	/*@Override
	public int hashCode() {
		String s=new String(board[0]);
		for(int i=0;i<board.length;i++)
			s=new String(s+new String(board[i]));
		return s.hashCode();
		
	}*/
	public PipeGameState() {
		// TODO Auto-generated constructor stub
	}
	public PipeGameState(char[][] newBoard) {
		setBoard(newBoard);
		/*for(int i=0;i<newBoard.length;i++) {
			for(int j=0;j<newBoard[0].length;j++) {
					if(!(this.pointNoWhere(new Position(i, j), this.getBoard()[i][j]))) 
						newBoard[i][j]=' ';	
			}
		}*/
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board=new char[board.length][board[0].length];
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++)
				this.board[i][j]=board[i][j];
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				str += board[i][j];
			}
			str += "\n";
		}
		return str;	
	}
	
	@Override
	public boolean equals(Object obj) {
		//work!!
		/*PipeGameState state = (PipeGameState)obj;		
		if(this.toString().equals(state.toString()))
			return true;
		return false;
		*/
		return this.toString().equals(obj.toString());
		/*for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != state.board[i][j])
					return false;
			}			
		}*/

	}
	public int hashcode(){
		/*work!!!
		String s=new String(board[0]);
		for(int i=0;i<board.length;i++) {
			s=new String(s+new String(board[i]));
		}
		
		return s.hashCode();
		*/
		return this.toString().hashCode();
	}
	
	
	public void rotate(Position p) {//clockwise
		
		if(p==findStart()||p==findFinish())//p is a start or finish point so can't move.
			return;
		if(p.row>this.board.length||p.col>this.board[0].length||p.col<0||p.row<0)//out of range
			return;
		
		 switch (this.board[p.row][p.col]) { 
		 	  case '-':
		        this.board[p.row][p.col] = 124; break;
		      case '|':
		        this.board[p.row][p.col] = 45; break;
		      case '7':
		        this.board[p.row][p.col] = 74; break;
		      case 'J':
		        this.board[p.row][p.col] = 76; break;
		      case 'L':
		        this.board[p.row][p.col] = 70; break;
		      case 'F':
		        this.board[p.row][p.col] = 55;
		      }
		
		
	}
	
	public boolean checkPostion(int row,int col) {
		if(col>this.board[0].length-1||row>this.board.length-1||row<0||col<0)
			return false;
		return true;
	}
	
	public Position findStart() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				if(board[i][j]=='s')
					return new Position(i, j);
			}
		}
		return null;
	}
	
	public Position findFinish() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				if(board[i][j]=='g')
					return new Position(i, j);
			}
		}
		return null;
	}
	
public boolean isFlowable(Position p) {//is the current char connected to the others around him?
		
		char rightNaber= '\0';
		char leftNaber = '\0';
		char upperNaber= '\0';
		char downerNaber= '\0';
		
		 if((checkPostion(p.row,p.col-1)))
				leftNaber = this.getBoard()[p.row][p.col-1];
		 if((checkPostion(p.row,p.col+1)))
				rightNaber = this.getBoard()[p.row][p.col+1];
		 if((checkPostion(p.row+1,p.col)))
				downerNaber = this.getBoard()[p.row+1][p.col];
		 if((checkPostion(p.row-1,p.col)))
				upperNaber = this.getBoard()[p.row-1][p.col];
				 
		
		
		 switch (this.board[p.row][p.col]) { 
	 	  case '-':{
	 		  if((checkPostion(p.row,p.col-1))){
	 			  if((checkPostion(p.row, p.col+1))) {
	 				  if(((leftNaber=='L')||(leftNaber=='-')||(leftNaber=='F')||(leftNaber=='s')||(leftNaber=='g'))
	 						  &&((rightNaber=='7')||(rightNaber=='-')||(rightNaber=='J')||(rightNaber=='s')||(rightNaber=='g')))
	 					  return true;
	 			  }
	 		  }
	 			  
	 		  }break;//return?
	 		  
	 	 case '|':{
	 		  if((checkPostion(p.row+1,p.col))){
	 			  if((checkPostion(p.row-1, p.col))) {
	 				  if(((upperNaber=='7')||(upperNaber=='|')||(upperNaber=='F')||(upperNaber=='s')||(upperNaber=='g'))
	 						  &&((downerNaber=='J')||(downerNaber=='|')||(downerNaber=='L')||(downerNaber=='s')||(downerNaber=='g')))
	 					  return true;
	 			  }
	 		  }
	 			  
	 		  }break;//return
	 		  
	 	 case '7':{
	 		  if((checkPostion(p.row,p.col-1))){
	 			  if((checkPostion(p.row+1, p.col))) {
	 				  if(((leftNaber=='L')||(leftNaber=='-')||(leftNaber=='F')||(leftNaber=='s')||(leftNaber=='g'))
	 						  &&((downerNaber=='J')||(downerNaber=='|')||(downerNaber=='L')||(downerNaber=='s')||(downerNaber=='g')))
	 					  return true;
	 			  }
	 		  }
	 			  
	 		  }break;//return
		 
	 case 'L':{
		  if((checkPostion(p.row-1,p.col))){
			  if((checkPostion(p.row, p.col+1))) {
				  if(((upperNaber=='F')||(upperNaber=='|')||(upperNaber=='7')||(upperNaber=='s')||(upperNaber=='g'))
						  &&((rightNaber=='J')||(rightNaber=='-')||(rightNaber=='7')||(rightNaber=='s')||(rightNaber=='g')))
					  return true;
			  }
		  }
			  
		  }break;//return
		  
	 case 'J':{
		  if((checkPostion(p.row-1,p.col))){
			  if((checkPostion(p.row, p.col-1))) {
				  if(((upperNaber=='F')||(upperNaber=='|')||(upperNaber=='7')||(upperNaber=='s')||(upperNaber=='g'))
						  &&((leftNaber=='L')||(leftNaber=='-')||(leftNaber=='F')||(leftNaber=='s')||(leftNaber=='g')))
					  return true;
			  }
		  }
			  
		  }break;//return
		  
	 case 'F':{
		  if((checkPostion(p.row+1,p.col))){
			  if((checkPostion(p.row, p.col+1))) {
				  if(((downerNaber=='L')||(downerNaber=='|')||(downerNaber=='J')||(downerNaber=='s')||(downerNaber=='g'))
						  &&((rightNaber=='J')||(rightNaber=='-')||(rightNaber=='7')||(rightNaber=='s')||(rightNaber=='g')))
					  return true;
			  }
		  }
			  
		  }break;//return
		  
	 case 's': return true;
	 case 'g': return true;
	 case ' ': return false;
		  
		  
	  }
	
	 		  
		return false;
		
	}

public boolean strightToFinish(Position p) {
	
	Position start=p;
	Position current;
	Position prev;
	Position next=null;
	char nextCh='\0';
	//start from s then check if there is a path to g
	if(checkPostion(start.row, start.col-1)) {//left
		prev=start;
		current=new Position (start.row, start.col-1);
		nextCh=this.getBoard()[start.row][start.col-1];
		while(isFlowable(current)) {
			if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	if(checkPostion(start.row, start.col+1)) {//right
		prev=start;
		current=new Position (start.row, start.col+1);
		nextCh=this.getBoard()[start.row][start.col+1];
		while(isFlowable(current)) {
			if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	
	if(checkPostion(start.row+1, start.col)) {//down
		prev=start;
		current=new Position (start.row+1, start.col);
		nextCh=this.getBoard()[start.row+1][start.col];
		while(isFlowable(current)) {
			if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
			(nextCh=='s'||nextCh=='g'))
				return false;
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			//case 'F': next=new Position(current.row-(current.col-prev.row),current.row-(current.col-prev.row));break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	if(checkPostion(start.row-1, start.col)) {//up
		prev=start;
		current=new Position (start.row-1, start.col);
		nextCh=this.getBoard()[start.row-1][start.col];
		while(isFlowable(current)) {
			if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	
	
	return false;
}
public boolean isFlowingToS(Position me) {
	Position isLoop=me;
	boolean left=false,right=false,down=false,up=false;
	switch(this.getBoard()[me.row][me.col]) {
	case '-': {left=true;right=true;};break;
	case '|': {up=true;down=true;};break;
	case 'L': {up=true;right=true;};break;
	case '7': {left=true;down=true;};break;
	case 'J': {up=true;left=true;};break;
	case 'F': {down=true;right=true;};break;
	case's':return true;
	}
	
	boolean flag=true;
	Position start=me;
	//Position finish=findFinish();
	Position current;
	Position prev;
	Position next=me;
	char nextCh='\0';
	//start from s then check if there is a path to g
	if(checkPostion(start.row, start.col-1)&&(left==true)) {//left
		prev=start;
		current=new Position (start.row, start.col-1);
		nextCh=this.getBoard()[start.row][start.col-1];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case's':return true;
			case'g':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='s')
				return true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row, start.col+1)&&(right==true)) {//right
		prev=start;
		current=new Position (start.row, start.col+1);
		nextCh=this.getBoard()[start.row][start.col+1];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case's':return true;
			case'g':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='s')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	flag=true;
	if(checkPostion(start.row+1, start.col)&&(down==true)) {//down
		prev=start;
		current=new Position (start.row+1, start.col);
		nextCh=this.getBoard()[start.row+1][start.col];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
			(nextCh=='s'||nextCh=='g'))
				return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case's':return true;
			case'g':flag=false;break;
			//case 'F': next=new Position(current.row-(current.col-prev.row),current.row-(current.col-prev.row));break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='s')
				return true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row-1, start.col)&&(up==true)) {//up
		prev=start;
		current=new Position (start.row-1, start.col);
		nextCh=this.getBoard()[start.row-1][start.col];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case's':return true;
			case'g':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='s')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	
	
	return false;
}
public boolean pointNoWhere(Position p, char ch) {
	boolean up=checkPostion(p.row-1, p.col);
	if(up==true)
		if(this.getBoard()[p.row-1][p.col]==' ')
			up=false;
	boolean down=checkPostion(p.row+1, p.col);
	if(down==true)
		if(this.getBoard()[p.row+1][p.col]==' ')
			down=false;
	boolean left=checkPostion(p.row, p.col-1);
	if(left==true)
		if(this.getBoard()[p.row][p.col-1]==' ')
			left=false;
	boolean right=checkPostion(p.row, p.col+1);
	if(right==true)
		if(this.getBoard()[p.row][p.col+1]==' ')
			right=false;
	
	switch(ch) {
	case '7':
	case 'F':
	case 'J':
	case 'L': {
		if((up==false&&down==false)||(left==false&&right==false))
			return false;	
	};break;
	case '|':
	case '-':{
		if((up==false&&(right==false||left==false))||(down==false&&(right==false||left==false)))
		return false;
	};break;
	}
	return true;
}

/*public boolean pointNoWhere(Position p, char ch) {
	switch(ch) {
	case '7':
	case 'F':
	case 'J':
	case 'L': {
		if((checkPostion(p.row, p.col-1)&&checkPostion(p.row, p.col+1))) {
			if((this.getBoard()[p.row][p.col-1]==' '&&this.getBoard()[p.row][p.col+1]==' '))
			return false;}
		if((checkPostion(p.row+1, p.col)&&checkPostion(p.row-1, p.col))) {
			if((this.getBoard()[p.row+1][p.col]==' '&&this.getBoard()[p.row-1][p.col]==' '))
			return false;
		}
		
	}
	case '-':{if((checkPostion(p.row, p.col-1)&&checkPostion(p.row, p.col+1))) {
		
	}
	}
	return false;
}*/
/*public boolean isConnected(Position me) {
	
	if(!isFlowable(me))
		return false;
	else {
		switch (whereTo(new Position(me.getRow(),me.getCol())) { 
		case leftRight:{
			if(isFlowable(new Position(me.getRow(),me.getCol()-1)))
				if(isFlowable(new Position(me.getRow(),me.getCol()+1)))
					return true;
		}
		}
		
	}
		return true;
}*/

/*public Direction whereTo(Position p) {
	
	switch (this.board[p.row][p.col]) { 
	  case '-': return Direction.leftRight;}
	  
	  switch (this.board[p.row][p.col]) { 
	  case '|': return Direction.upDown;}
	  
	  switch (this.board[p.row][p.col]) { 
	  case 'L': return Direction.upRight;}
	  
	  switch (this.board[p.row][p.col]) { 
	  case 'F': return Direction.rightDown;}
	  
	  switch (this.board[p.row][p.col]) { 
	  case 'J': return Direction.upLeft;}
	  
	  switch (this.board[p.row][p.col]) { 
	  case '7': return Direction.leftDown;}
	  

	  return Direction.all;
}*/

public char nextChar(Position p) {return this.getBoard()[p.col][p.row];}





/*
public boolean isFlowingToG(Position me) {
	
	//boolean madeToS=false;
	//boolean madeToG=false;
	
	
	boolean left=false,right=false,down=false,up=false;
	switch(this.getBoard()[me.row][me.col]) {
	case '-': {left=true;right=true;};break;
	case '|': {up=true;down=true;};break;
	case 'L': {up=true;right=true;};break;
	case '7': {left=true;down=true;};break;
	case 'J': {up=true;left=true;};break;
	case 'F': {down=true;right=true;};break;
	case'g':return true;
	}
	
	boolean flag=true;
	Position start=me;
	//Position finish=findFinish();
	Position current;
	Position prev;
	Position next=me;
	char nextCh='\0';
	//start from s then check if there is a path to g
	if(checkPostion(start.row, start.col-1)&&left) {//left
		prev=start;
		current=new Position (start.row, start.col-1);
		nextCh=this.getBoard()[start.row][start.col-1];
		if(!isFlowable(prev))
			flag=false;
		while(isFlowable(current)&&flag) {
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row, start.col+1)&&right) {//right
		prev=start;
		current=new Position (start.row, start.col+1);
		nextCh=this.getBoard()[start.row][start.col+1];
		if(!isFlowable(prev))
			flag=false;
		while(isFlowable(current)&&flag) {
			
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	flag=true;
	if(checkPostion(start.row+1, start.col)&&down) {//down
		prev=start;
		current=new Position (start.row+1, start.col);
		nextCh=this.getBoard()[start.row+1][start.col];
		if(!isFlowable(prev))
			flag=false;
		while(isFlowable(current)&&flag) {
		
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			//case 'F': next=new Position(current.row-(current.col-prev.row),current.row-(current.col-prev.row));break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row-1, start.col)&&up) {//up
		prev=start;
		current=new Position (start.row-1, start.col);
		nextCh=this.getBoard()[start.row-1][start.col];
		if(!isFlowable(prev))
			flag=false;
		while(isFlowable(current)&&flag) {
			
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			}
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	
	
	return false;
}*/


public boolean StoG(Position me) {
	Position isLoop=me;
	boolean madeToS=false;
	boolean madeToG=false;
	
	
	boolean left=false,right=false,down=false,up=false;
	switch(this.getBoard()[me.row][me.col]) {
	case '-': {left=true;right=true;};break;
	case '|': {up=true;down=true;};break;
	case 'L': {up=true;right=true;};break;
	case '7': {left=true;down=true;};break;
	case 'J': {up=true;left=true;};break;
	case 'F': {down=true;right=true;};break;
	case'g':madeToG=true;
	case's':madeToS=true;
	}
	
	boolean flag=true;
	Position start=me;
	//Position finish=findFinish();
	Position current;
	Position prev;
	Position next=me;
	char nextCh='\0';
	//start from s then check if there is a path to g
	if(checkPostion(start.row, start.col-1)&&left) {//left
		prev=start;
		current=new Position (start.row, start.col-1);
		nextCh=this.getBoard()[start.row][start.col-1];
		if(!isFlowable(prev))
			return false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':{flag=false;madeToG=true;}break;
			case's':{flag=false;madeToS=true;}break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				madeToG= true;
			if(nextCh=='s')
				madeToS= true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row, start.col+1)&&right) {//right
		prev=start;
		current=new Position (start.row, start.col+1);
		nextCh=this.getBoard()[start.row][start.col+1];
		if(!isFlowable(prev))
			flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':{flag=false;madeToG=true;}break;
			case's':{flag=false;madeToS=true;}break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				madeToG= true;
			if(nextCh=='s')
				madeToS= true;
			prev=current;
			current=next;
			
		}
				
	}
	flag=true;
	if(checkPostion(start.row+1, start.col)&&down) {//down
		prev=start;
		current=new Position (start.row+1, start.col);
		nextCh=this.getBoard()[start.row+1][start.col];
		if(!isFlowable(prev))
			flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
			(nextCh=='s'||nextCh=='g'))
				return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':{flag=false;madeToG=true;}break;
			case's':{flag=false;madeToS=true;}break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				madeToG= true;
			if(nextCh=='s')
				madeToS= true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row-1, start.col)&&up) {//up
		prev=start;
		current=new Position (start.row-1, start.col);
		nextCh=this.getBoard()[start.row-1][start.col];
		if(!isFlowable(prev))
			flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':{flag=false;madeToG=true;}break;
			case's':{flag=false;madeToS=true;}break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				madeToG= true;
			if(nextCh=='s')
				madeToS= true;
			prev=current;
			current=next;
			
		}
				
	}
	if(madeToG&&madeToS)
		return true;
	
	return false;
}


public boolean fromStoG(Position me) {
	Position isLoop=me;
	
	
	boolean flag=true;
	Position start=me;
	//Position finish=findFinish();
	Position current;
	Position prev;
	Position next=me;
	char nextCh='\0';
	//start from s then check if there is a path to g
	if(checkPostion(start.row, start.col-1)) {//left
		prev=start;
		current=new Position (start.row, start.col-1);
		nextCh=this.getBoard()[start.row][start.col-1];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row, start.col+1)) {//right
		prev=start;
		current=new Position (start.row, start.col+1);
		nextCh=this.getBoard()[start.row][start.col+1];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	flag=true;
	if(checkPostion(start.row+1, start.col)) {//down
		prev=start;
		current=new Position (start.row+1, start.col);
		nextCh=this.getBoard()[start.row+1][start.col];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
			(nextCh=='s'||nextCh=='g'))
				return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			//case 'F': next=new Position(current.row-(current.col-prev.row),current.row-(current.col-prev.row));break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}flag=true;
	if(checkPostion(start.row-1, start.col)) {//up
		prev=start;
		current=new Position (start.row-1, start.col);
		nextCh=this.getBoard()[start.row-1][start.col];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return true;
			case's':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return false;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return true;
			prev=current;
			current=next;
			
		}
				
	}
	
	
	return false;
}

public int howFarFromG(Position me) {
	Position isLoop=me;
	
	int left;
	int right;
	int up;
	int down;
	int max=1000;
	
	boolean flag=true;
	Position start=me;
	Position finish=findFinish();
	Position current;
	Position prev;
	Position next=me;
	char nextCh='\0';
	//start from s then check if there is a path to g
	if(checkPostion(start.row, start.col-1)) {//left
		prev=start;
		current=new Position (start.row, start.col-1);
		nextCh=this.getBoard()[start.row][start.col-1];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return 0;
			case's':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return Math.abs((finish.row-next.row)+(finish.col-next.col));
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return 0;
			prev=current;
			current=next;
			
		}
			left=Math.abs((finish.row-next.row)+Math.abs(finish.col-next.col));
			max=left;
	}flag=true;
	if(checkPostion(start.row, start.col+1)) {//right
		prev=start;
		current=new Position (start.row, start.col+1);
		nextCh=this.getBoard()[start.row][start.col+1];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return 0;
			case's':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return Math.abs((finish.row-next.row)+Math.abs(finish.col-next.col));
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return 0;
			prev=current;
			current=next;
			
		}right=Math.abs((finish.row-next.row)+Math.abs(finish.col-next.col));
		if(right<max)
			max=right;
				
	}
	flag=true;
	if(checkPostion(start.row+1, start.col)) {//down
		prev=start;
		current=new Position (start.row+1, start.col);
		nextCh=this.getBoard()[start.row+1][start.col];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
			(nextCh=='s'||nextCh=='g'))
				return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return 0;
			case's':flag=false;break;
			//case 'F': next=new Position(current.row-(current.col-prev.row),current.row-(current.col-prev.row));break;
			}
			if(next.equals(isLoop))//////////////////////////
				return Math.abs((finish.row-next.row)+(finish.col-next.col));;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return 0;
			prev=current;
			current=next;
			
		}down=Math.abs((finish.row-next.row)+Math.abs(finish.col-next.col));
		if(down<max)
			max=down;
				
	}flag=true;
	if(checkPostion(start.row-1, start.col)) {//up
		prev=start;
		current=new Position (start.row-1, start.col);
		nextCh=this.getBoard()[start.row-1][start.col];
		//if(!isFlowable(prev))
			//flag=false;
		while(isFlowable(current)&&flag) {
			/*if((this.getBoard()[current.row][current.col]=='s'||this.getBoard()[current.row][current.col]=='g')&&
					(nextCh=='s'||nextCh=='g'))
						return false;*/
			switch(nextCh) {
			case '-': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case '|': next=new Position(current.row+(current.row-prev.row), current.col+(current.col-prev.col));break;
			case 'L': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case '7': next=new Position(current.row+(current.col-prev.col), current.col+(current.row-prev.row));break;
			case 'J': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case 'F': next=new Position(current.row-(current.col-prev.col),current.col-(current.row-prev.row));break;
			case'g':return 0;
			case's':flag=false;break;
			}
			if(next.equals(isLoop))//////////////////////////
				return Math.abs((finish.row-next.row)+(finish.col-next.col));;
			if(!checkPostion(next.row, next.col))
				break;
			nextCh=this.getBoard()[next.row][next.col];
			if(nextCh=='g')
				return 0;
			prev=current;
			current=next;
			
		}up=Math.abs((finish.row-next.row)+(finish.col-next.col));
		if(up<max)
			max=up;
				
	}
	
	if(max==1000)
		return-1;
	return max;
}

}