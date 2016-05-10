
package oopsnake;
  
import  java.awt.event.KeyListener;
import  java.awt.event.KeyEvent;
import  java.awt.event.ActionListener;
import  java.util.ArrayList;
import  java.awt.Toolkit;
import javax.swing.JFrame;
import  java.awt.Dimension;
import  java.awt.Point;
import  java.awt.event.ActionEvent;
import javax.swing.Timer;
   
   public class Field   implements ActionListener, KeyListener{
   	 private JFrame jframe;
	 private RenderPanel panel;
	 public static Field field;       
	 private Timer timer = new Timer(20, this);;
	 private int tick = 0, score, time;
	 private Dimension dim; 	
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
     private int scale;
     
     public int getScale() {
       return scale;
     }
     
     public void setScale(int scale) {
       this.scale = scale;
     }
     private Snake mSnake;
      
     private Obstacle poison;
      
     private Obstacle cherry;
      
     private ArrayList<Snake> sArr = new ArrayList<Snake>();
     
     public ArrayList<Snake> getSArr() {
     	return this.sArr;
     	}
     private ArrayList<Obstacle> oArr = new ArrayList<Obstacle>();
     
     public ArrayList<Obstacle> getOArr() {
     	return this.oArr;
     	}
     public Field (int width,int height){
     		
     		dim = Toolkit.getDefaultToolkit().getScreenSize();
     		jframe = new JFrame();
     		jframe.setVisible(true);
     		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     		jframe.setSize(width, height);
     		jframe.setResizable(false);
     		jframe.add(panel = new RenderPanel());
     		jframe.addKeyListener(this);
     		scale = 10;
     		tick = 0;
     this.width = width;
     		this.height = height;
     		mSnake =new Snake(0,0);
     		sArr.add(mSnake);
     		  
     		 
     		cherry = new Obstacle (1,width/scale,height/scale);
     		oArr.add(cherry);
     		 
     		poison = new Obstacle (0,width/scale,height/scale);
     		oArr.add(poison);
     		 
     		timer.start();
     	}
     	public static void main(String[] args) {
     		field = new Field(400,400);
     	}
     public ArrayList<Obstacle> getObstacle() {
		return oArr;
	}

	public ArrayList<Snake> getSnake() {
		return sArr;
	}
	
	public int getTime() {
		return time;
	}
		@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i = e.getKeyCode();
		if (sArr.size() == 2) {
			Snake mySnake = sArr.get(0);
			Snake secondSnake = sArr.get(1);
			if (i == KeyEvent.VK_A && mySnake.getDirection() != Action.RIGHT) {
				mySnake.setDirection(Action.LEFT);
			}
			if (i == KeyEvent.VK_D && mySnake.getDirection() != Action.LEFT) {
				mySnake.setDirection(Action.RIGHT);
			}
			if (i == KeyEvent.VK_W && mySnake.getDirection() != Action.DOWN) {
				mySnake.setDirection(Action.UP);
			}
			if (i == KeyEvent.VK_S && mySnake.getDirection() != Action.UP) {
				mySnake.setDirection(Action.DOWN);
			}
			if (i == KeyEvent.VK_U && secondSnake.getDirection() != Action.DOWN) {
				secondSnake.setDirection(Action.UP);
			}
			if (i == KeyEvent.VK_H && secondSnake.getDirection() != Action.RIGHT) {
				secondSnake.setDirection(Action.LEFT);
			}
			if (i == KeyEvent.VK_K && secondSnake.getDirection() != Action.LEFT) {
				secondSnake.setDirection(Action.RIGHT);
			}
			if (i == KeyEvent.VK_J && secondSnake.getDirection() != Action.UP) {
				secondSnake.setDirection(Action.DOWN);
			}
			if (i == KeyEvent.VK_SPACE) {
				if (mySnake.getState() == State.OVER || secondSnake.getState() == State.OVER) {
					Snake mSnake = new Snake(0, 0);
					Snake sSnake = new Snake(width / scale - 2, 0);
					sArr.removeAll(sArr);
					sArr.add(mSnake);
					sArr.add(sSnake);
				}
				if (mySnake.getState() == State.RUNNING && secondSnake.getState() == State.RUNNING) {
					mySnake.setState(State.PAUSE);
				} else {
					mySnake.setState(State.RUNNING);
					secondSnake.setState(State.RUNNING);
				}
			}
		} else {
			Snake mySnake = sArr.get(0);
			if (i == KeyEvent.VK_A && mySnake.getDirection() != Action.RIGHT) {
				mySnake.setDirection(Action.LEFT);
			}
			if (i == KeyEvent.VK_D && mySnake.getDirection() != Action.LEFT) {
				mySnake.setDirection(Action.RIGHT);
			}
			if (i == KeyEvent.VK_W && mySnake.getDirection() != Action.DOWN) {
				mySnake.setDirection(Action.UP);
			}
			if (i == KeyEvent.VK_S && mySnake.getDirection() != Action.UP) {
				mySnake.setDirection(Action.DOWN);
			}
			if (i == KeyEvent.VK_SPACE) {
				if (mySnake.getState() == State.OVER) {
					Snake mSnake = new Snake(0, 0);
					sArr.remove(0);
					sArr.add(mSnake);
				}
				if (mySnake.getState() == State.RUNNING) {
					mySnake.setState(State.PAUSE);
				} else {
					mySnake.setState(State.RUNNING);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(mySnake.getHead().x + " " + mySnake.getHead().y);
		if (sArr.size() == 1 && oArr.size() == 1) {
			Snake mySnake = sArr.get(0);
			Obstacle cherry = oArr.get(0);
			System.out.println(mySnake.getHead().x + " " + mySnake.getHead().y);

			panel.repaint();
			tick++;
			if (tick % 5 == 0 && mySnake.getHead() != null && mySnake.getState() != State.OVER
					&& mySnake.getState() != State.PAUSE) {
				time++;
				mySnake.getSnakePart().add(new Point(mySnake.getHead().x, mySnake.getHead().y));
				if (mySnake.getDirection() == Action.UP)
					if (mySnake.getHead().y - 1 >= 0 && mySnake.noTailAt(mySnake.getHead().x, mySnake.getHead().y - 1))
						mySnake.setHead(new Point(mySnake.getHead().x, mySnake.getHead().y - 1));
					else
						mySnake.setState(State.OVER);

				if (mySnake.getDirection() == Action.DOWN)
					if (mySnake.getHead().y + 1 < height / scale - 3
							&& mySnake.noTailAt(mySnake.getHead().x, mySnake.getHead().y + 1))
						mySnake.setHead(new Point(mySnake.getHead().x, mySnake.getHead().y + 1));
					else
						mySnake.setState(State.OVER);
				if (mySnake.getDirection() == Action.LEFT)
					if (mySnake.getHead().x - 1 >= 0 && mySnake.noTailAt(mySnake.getHead().x - 1, mySnake.getHead().y))
						mySnake.setHead(new Point(mySnake.getHead().x - 1, mySnake.getHead().y));
					else
						mySnake.setState(State.OVER);
				if (mySnake.getDirection() == Action.RIGHT)
					if (mySnake.getHead().x + 1 < width / scale
							&& mySnake.noTailAt(mySnake.getHead().x + 1, mySnake.getHead().y))
						mySnake.setHead(new Point(mySnake.getHead().x + 1, mySnake.getHead().y));
					else
						mySnake.setState(State.OVER);
				if (mySnake.getSnakePart().size() > mySnake.getTailLength())
					mySnake.getSnakePart().remove(0);
				if (cherry != null) {

					if (mySnake.getHead().x == cherry.getLocation().x
							&& mySnake.getHead().y == cherry.getLocation().y) {

						int length = mySnake.getTailLength();
						length++;
						mySnake.setTailLength(length);
						cherry.setLocation();
					}
				}
			}
		}
		else if(sArr.size()==1&&oArr.size()==2){
			Snake mySnake = sArr.get(0);
			Obstacle mCherry = null ;
			Obstacle mPoison= null ;
			for(Obstacle o:oArr){
				if (o.getObstaclePoint()>0)
					mCherry = o;
				else
					mPoison=o;
			}
			
			panel.repaint();
			tick++;
			if (tick % 5 == 0 && mySnake.getHead() != null && mySnake.getState() != State.OVER
					&& mySnake.getState() != State.PAUSE) {
				time++;
				mySnake.getSnakePart().add(new Point(mySnake.getHead().x, mySnake.getHead().y));
				if (mySnake.getDirection() == Action.UP)
					if (mySnake.getHead().y - 1 >= 0 && mySnake.noTailAt(mySnake.getHead().x, mySnake.getHead().y - 1))
						mySnake.setHead(new Point(mySnake.getHead().x, mySnake.getHead().y - 1));
					else
						mySnake.setState(State.OVER);

				if (mySnake.getDirection() == Action.DOWN)
					if (mySnake.getHead().y + 1 < height / scale - 3
							&& mySnake.noTailAt(mySnake.getHead().x, mySnake.getHead().y + 1))
						mySnake.setHead(new Point(mySnake.getHead().x, mySnake.getHead().y + 1));
					else
						mySnake.setState(State.OVER);
				if (mySnake.getDirection() == Action.LEFT)
					if (mySnake.getHead().x - 1 >= 0 && mySnake.noTailAt(mySnake.getHead().x - 1, mySnake.getHead().y))
						mySnake.setHead(new Point(mySnake.getHead().x - 1, mySnake.getHead().y));
					else
						mySnake.setState(State.OVER);
				if (mySnake.getDirection() == Action.RIGHT)
					if (mySnake.getHead().x + 1 < width / scale
							&& mySnake.noTailAt(mySnake.getHead().x + 1, mySnake.getHead().y))
						mySnake.setHead(new Point(mySnake.getHead().x + 1, mySnake.getHead().y));
					else
						mySnake.setState(State.OVER);
				if (mySnake.getSnakePart().size() > mySnake.getTailLength())
					mySnake.getSnakePart().remove(0);
				if (mCherry != null&&mPoison!=null) {

					if (mySnake.getHead().x == mCherry.getLocation().x
							&& mySnake.getHead().y == mCherry.getLocation().y) {

						int length = mySnake.getTailLength();
						length++;
						mySnake.setTailLength(length);
						mCherry.setLocation();
						mPoison.setLocation();
					}
					if (mySnake.getHead().x == mPoison.getLocation().x
							&& mySnake.getHead().y == mPoison.getLocation().y) {

						score += mPoison.getObstaclePoint();
						int length = mySnake.getTailLength();
						length--;
						mySnake.setTailLength(length);
						mySnake.deleteTail();
						mPoison.setLocation();
						mCherry.setLocation();
					}
				}
			}
		}
		else {
			panel.repaint();
			tick++;
			Snake mySnake = sArr.get(0);
			Snake secondSnake = sArr.get(1);
			Obstacle mCherry = null ;
			Obstacle mPoison= null ;
			for(Obstacle o:oArr){
				if (o.getObstaclePoint()>0)
					mCherry = o;
				else
					mPoison=o;
			}
			if (tick % 5 == 0 && mySnake.getHead() != null && mySnake.getState() != State.OVER
					&& mySnake.getState() != State.PAUSE) {
				time++;
				mySnake.getSnakePart().add(new Point(mySnake.getHead().x, mySnake.getHead().y));
				secondSnake.getSnakePart().add(new Point(secondSnake.getHead().x, secondSnake.getHead().y));
				if (mySnake.getDirection() == Action.UP)
					if (mySnake.getHead().y - 1 >= 0 && mySnake.noTailAt(mySnake.getHead().x, mySnake.getHead().y - 1))
						mySnake.setHead(new Point(mySnake.getHead().x, mySnake.getHead().y - 1));
					else
						mySnake.setState(State.OVER);
				if (secondSnake.getDirection() == Action.UP)
					if (secondSnake.getHead().y - 1 >= 0
							&& secondSnake.noTailAt(secondSnake.getHead().x, secondSnake.getHead().y - 1))
						secondSnake.setHead(new Point(secondSnake.getHead().x, secondSnake.getHead().y - 1));
					else
						secondSnake.setState(State.OVER);
				if (mySnake.getDirection() == Action.DOWN)
					if (mySnake.getHead().y + 1 < 57 && mySnake.noTailAt(mySnake.getHead().x, mySnake.getHead().y + 1))
						mySnake.setHead(new Point(mySnake.getHead().x, mySnake.getHead().y + 1));
					else
						mySnake.setState(State.OVER);
				if (secondSnake.getDirection() == Action.DOWN)
					if (secondSnake.getHead().y + 1 < 57
							&& secondSnake.noTailAt(secondSnake.getHead().x, secondSnake.getHead().y + 1))
						secondSnake.setHead(new Point(secondSnake.getHead().x, secondSnake.getHead().y + 1));
					else
						secondSnake.setState(State.OVER);
				if (mySnake.getDirection() == Action.LEFT)
					if (mySnake.getHead().x - 1 >= 0 && mySnake.noTailAt(mySnake.getHead().x - 1, mySnake.getHead().y))
						mySnake.setHead(new Point(mySnake.getHead().x - 1, mySnake.getHead().y));
					else
						mySnake.setState(State.OVER);
				if (secondSnake.getDirection() == Action.LEFT)
					if (secondSnake.getHead().x - 1 >= 0
							&& secondSnake.noTailAt(secondSnake.getHead().x - 1, secondSnake.getHead().y))
						secondSnake.setHead(new Point(secondSnake.getHead().x - 1, secondSnake.getHead().y));
					else
						secondSnake.setState(State.OVER);
				if (mySnake.getDirection() == Action.RIGHT)
					if (mySnake.getHead().x + 1 < 60 && mySnake.noTailAt(mySnake.getHead().x + 1, mySnake.getHead().y))
						mySnake.setHead(new Point(mySnake.getHead().x + 1, mySnake.getHead().y));
					else
						mySnake.setState(State.OVER);
				if (secondSnake.getDirection() == Action.RIGHT)
					if (secondSnake.getHead().x + 1 < 60
							&& secondSnake.noTailAt(secondSnake.getHead().x + 1, secondSnake.getHead().y))
						secondSnake.setHead(new Point(secondSnake.getHead().x + 1, secondSnake.getHead().y));
					else
						secondSnake.setState(State.OVER);
				if (mySnake.getSnakePart().size() > mySnake.getTailLength())
					mySnake.getSnakePart().remove(0);
				if (secondSnake.getSnakePart().size() > secondSnake.getTailLength())
					secondSnake.getSnakePart().remove(0);
				if (mCherry != null && mPoison != null) {
					if (mySnake.getHead().x == mCherry.getLocation().x
							&& mySnake.getHead().y == mCherry.getLocation().y) {
						score += mCherry.getObstaclePoint();
						int length = mySnake.getTailLength();
						length++;
						mySnake.setTailLength(length);
						mCherry.setLocation();
						mPoison.setLocation();
					}
					if (secondSnake.getHead().x == mCherry.getLocation().x
							&& secondSnake.getHead().y == mCherry.getLocation().y) {
						score += mCherry.getObstaclePoint();
						int length = mySnake.getTailLength();
						length++;
						secondSnake.setTailLength(length);
						mCherry.setLocation();
						mPoison.setLocation();
					}
					if (mySnake.getHead().x == mPoison.getLocation().x
							&& mySnake.getHead().y == mPoison.getLocation().y) {

						score += mPoison.getObstaclePoint();
						int length = mySnake.getTailLength();
						length--;
						mySnake.setTailLength(length);
						mySnake.deleteTail();
						mPoison.setLocation();
						mCherry.setLocation();
					}
					 if (secondSnake.getHead().x == mPoison.getLocation().x
								&& secondSnake.getHead().y == mPoison.getLocation().y) {

							score += mPoison.getObstaclePoint();
							int length = secondSnake.getTailLength();
							length--;
							secondSnake.setTailLength(length);
							secondSnake.deleteTail();
							mPoison.setLocation();
							mCherry.setLocation();
						}
				}
			}
		}
	}
    }
