package sanson.yvan;
import java.util.ArrayList;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (getLine() != that.getLine()) return false;
        return getColumn() == that.getColumn();
    }

    public ArrayList<Coordinates>findNeighbors(){
	    ArrayList<Coordinates> neighbors = new ArrayList<>();
	    Coordinates up = new Coordinates((char)(this.getLine()-1), this.getColumn());
	    Coordinates down = new Coordinates((char)(this.getLine()+1), this.getColumn());
	    Coordinates left = new Coordinates(this.getLine(), this.getColumn()-1);
	    Coordinates right = new Coordinates(this.getLine(), this.getColumn()+1);
	    if(up.isCorrect())
	        neighbors.add(up);
	    if(down.isCorrect())
	        neighbors.add(down);
	    if(left.isCorrect())
	        neighbors.add(left);
	    if(right.isCorrect())
	        neighbors.add(right);
        return neighbors;
    }

    public ArrayList<Coordinates>findNeighborsInline(){
	    ArrayList<Coordinates> neighbors = new ArrayList<>();
	    Coordinates left = new Coordinates(this.getLine(), this.getColumn()-1);
	    Coordinates right = new Coordinates(this.getLine(), this.getColumn()+1);
        if(left.isCorrect())
            neighbors.add(left);
        if(right.isCorrect())
            neighbors.add(right);
        return neighbors;
    }

    public ArrayList<Coordinates>findNeighborsInColumn(){
        ArrayList<Coordinates> neighbors = new ArrayList<>();
        Coordinates up = new Coordinates((char)(this.getLine()-1), this.getColumn());
        Coordinates down = new Coordinates((char)(this.getLine()+1), this.getColumn());
        if(up.isCorrect())
            neighbors.add(up);
        if(down.isCorrect())
            neighbors.add(down);
        return neighbors;
    }

    public boolean isCorrect(){
	    return line>='A' && line<='J' && column>=1 && column<=10;
    }
    
	public static boolean isCorrect(String coord){
		char line;
		int column;
		try{
			line=coord.toUpperCase().charAt(0);
			column=Integer.parseInt(coord.substring(1));
            return line>='A' && line<='J' && column>=1 && column<=10;
		}catch(Exception e){
			System.out.println("Bad coordinates");
		}
		return false;
	}

	public static boolean hitsAreNeighbors(Coordinates c1, Coordinates c2){
		ArrayList<Coordinates> neighC1 = c1.findNeighbors();
		return neighC1.contains(c2);
	}
	
	
	@Override
	public String toString(){
		return line+Integer.toString(column);
	}
    
}
