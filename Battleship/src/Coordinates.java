
public class Coordinates {
	//Usable with coordinates like A...Z1... 
	private char line;
	private int column;
	
	
	public char getLine() {
		return line;
	}


	public void setLine(char line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public Coordinates(char line, int column) {
		this.line = line;
		this.column = column;
	}

	public Coordinates(String coord){
		line=coord.toUpperCase().charAt(0);
		column=Integer.parseInt(coord.substring(1));
	}
    
	public static boolean isCorrect(String coord){
		char line;
		int column;
		try{
			line=coord.toUpperCase().charAt(0);
			column=Integer.parseInt(coord.substring(1));
			return true;
		}catch(Exception e){
			System.out.println("Bad coordinates");
		}
		return false;
	}
	
	
	@Override
	public String toString(){
		return line+Integer.toString(column);
	}
    
}
