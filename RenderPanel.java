 
 package oopsnake;
   
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JDialog;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.util.ArrayList;
      public class RenderPanel extends JPanel {


	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor((Color.WHITE));
		g.fillRect(0, 0, 600, 600);
		Field field = Field.field;

		fillObject(g, Color.BLUE, Color.YELLOW, field);
	}

	void fillObject(Graphics g, Color snakeColor, Color obstacleColor, Field field) {
		g.setColor(snakeColor);
		if (field.getSnake().size() == 2) {
			for (Point point : field.getSnake().get(0).getSnakePart()) {
				g.fillRect(point.x * field.getScale(), point.y * field.getScale(), field.getScale(), field.getScale());
			}
			for (Point point : field.getSnake().get(1).getSnakePart()) {
				g.fillRect(point.x * field.getScale(), point.y * field.getScale(), field.getScale(), field.getScale());
			}

			String string = "Length: " + field.getSnake().get(0).getTailLength() + " vs "
					+ field.getSnake().get(1).getTailLength() + ", Time: " + field.getTime() / 20;
			g.setColor(Color.WHITE);
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 1.5f), 10);
		}

		if (field.getSnake().size() == 1) {
			for (Point point : field.getSnake().get(0).getSnakePart()) {
				g.fillRect(point.x * field.getScale(), point.y * field.getScale(), field.getScale(), field.getScale());
			}
			String string = "Length: " + field.getSnake().get(0).getTailLength() + ", Time: " + field.getTime() / 20;
			g.setColor(Color.WHITE);
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 1.5f), 10);
		}

		for (int i = 0; i < field.getObstacle().size(); i++) {
			g.setColor(obstacleColor);
			g.fillRect(field.getObstacle().get(i).getLocation().x * field.getScale(),
					field.getObstacle().get(i).getLocation().y * field.getScale(), field.getScale(), field.getScale());
		}

	}
}
