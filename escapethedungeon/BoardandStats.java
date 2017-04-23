

public class BoardandStats {
	
	private int pos;
	private int[] board;
	
	public BoardandStats(int pos,int length){
		this.pos=pos;
		board = new int[length];
	}
	
	public void update(int num)
	{
		pos += num;
		for(int i=0;i<board.length;i++) {
			if(i==pos)
				board[i]=1;
			else
				board[i]=0;
		}
	}
	
	public int getPosition(){
		return pos;
	}

	public void printBoard() {
		String viewBoard = " _________\n|";
		for(int i=0;i<board.length;i++) {
			if(i==pos)
				viewBoard+="X";
			else
				viewBoard+="_";
		}
		System.out.print(viewBoard);
	}
}