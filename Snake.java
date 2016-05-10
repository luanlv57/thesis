  package oopsnake;
import java.awt.Point;
import java.util.ArrayList;
    
    public class Snake {
   
      private String name;
      
      public String getName() {
        return name;
      }
      
      public void setName(String name) {
        this.name = name;
      }
      private int tailLength;
      
      public int getTailLength() {
        return tailLength;
      }
      
      public void setTailLength(int tailLength) {
        this.tailLength = tailLength;
      }
      private State state;
      
      public State getState() {
        return state;
      }
      
      public void setState(State state) {
        this.state = state;
      }
      private Action direction;
      
      public Action getDirection() {
        return direction;
      }
      
      public void setDirection(Action direction) {
        this.direction = direction;
      }
      private ArrayList<Point> snakePart = new ArrayList<Point>();
      
      public ArrayList<Point> getSnakePart() {
      	return this.snakePart;
      	}
      private Point head;
      
      public Point getHead() {
        return head;
      }
      
      public void setHead(Point head) {
        this.head = head;
      }
      public Snake (int x,int y){
      		name = " Black Snake";
      		tailLength = 2;
      		head = new Point(x, y);
      		state = State.RUNNING;
      		snakePart.clear();
      		for (int i = 0; i < tailLength; i++) {
      			snakePart.add(new Point(head.x, head.y));
      
      		}
      		direction = Action.DOWN;
      	}
      
      public boolean noTailAt(int x, int y) {
	for (Point point : snakePart) {
		if (point.equals(new Point(x, y))) {
			return false;
		}
	}
	return true;
  }
  public void deleteTail(){
	snakePart.remove(0);
}
    }
