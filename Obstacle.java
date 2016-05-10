
package oopsnake;
  
import  java.util.Random;
import  java.awt.Point;
   
   public class Obstacle {
  	 private Random random;	
     private int pointOfObstacle;
     
     public int getPointOfObstacle() {
       return pointOfObstacle;
     }
     
     public void setPointOfObstacle(int pointOfObstacle) {
       this.pointOfObstacle = pointOfObstacle;
     }
     private int width;
     
     public int getWidth() {
       return width;
     }
     
     public void setWidth(int width) {
       this.width = width;
     }
     private int height;
     
     public int getHeight() {
       return height;
     }
     
     public void setHeight(int height) {
       this.height = height;
     }
     private Point point;
     
     public Point getPoint() {
       return point;
     }
     
     public void setPoint(Point point) {
       this.point = point;
     }
     public Obstacle (int pointOfObstacles,int width,int height){
     	random = new Random();
     	point = new Point(random.nextInt(width-2), random.nextInt(height-3));
     	pointOfObstacle = pointOfObstacles;
     	this.width=width;
     	this.height=height;
     }
       
       
    public void setLocation() {
		// random = new Random();
		point.setLocation(random.nextInt(width-2), random.nextInt(height-3));
	}
	 public Point getLocation() {
		return point;
	}
	public int getObstaclePoint() {
		return pointOfObstacle;
	}
   }
