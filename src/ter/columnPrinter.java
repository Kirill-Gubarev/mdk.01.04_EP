package ter;

public class columnPrinter{
	private Point columnPos = new Point(0, 0);
	private Point nextColumnPos = new Point(0, 0);

	public columnPrinter(){
		this(terminal.getCursorPos());
	}
	public columnPrinter(Point p){
		columnPos = new Point(p);
		nextColumnPos = new Point(p);
	}

	public void setColumnOutputX(int x){
		columnPos.x = x;
	}
	public void setColumnOutputY(int y){
		columnPos.y = y;
	}

	public void clear(){
		columnPos = new Point(0, 0);
		nextColumnPos = new Point(0, 0);
	}

	public void print(String str){
		terminal.setCursorPos(columnPos);
		System.out.print(str);
		int x = terminal.getCursorPos().x;
		if(nextColumnPos.x < x)
			nextColumnPos.x = x;
		columnPos.y++;
	}
	public void next(){
		columnPos.x = nextColumnPos.x;
		columnPos.y = nextColumnPos.y;
	}
}
