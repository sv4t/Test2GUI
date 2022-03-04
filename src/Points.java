import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

public class Points extends JComponent{
	private double[] points = new double[6];
	private int index;

	private int moveToPointX = -1;
	private int moveToPointY = -1;

	private Color color;

	public void setColor(Color color){
		this.color = color;
	}

	public Color getColor(){
		return this.color;
	}


	public Points(){
		super();
		setBackground(Color.WHITE);
		addMouseMotionListener(new MouseMotionListener(){
				@Override
				public void mouseDragged(MouseEvent e) {
					if(moveToPointX != -1 && moveToPointY != -1) {
						points[moveToPointX] = e.getX();
						points[moveToPointY] = e.getY();	
						repaint();
					}
				}

				@Override
				public void mouseMoved(MouseEvent e) {}
		});

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(index <points.length){
					points[index] = e.getX();
					points[index+1] = e.getY();
					index += 2;
					repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				int currentX = e.getX();
				int currentY = e.getY();
				for(int i = 0; i<index; i+=2){
					if( points[i]-5 < currentX && currentX < points[i]+5 && points[i+1]-5 < currentY && currentY < points[i+1]+5){
						moveToPointX = i;
						moveToPointY = i+1;
						break;
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				moveToPointX = -1;
				moveToPointY = -1;
			}
			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getBackground());
		g2d.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
		if(index > 0)
		{
			for(int i = 0; i<index; i+=2)
			{
				g2d.setColor(getColor());
				Ellipse2D ellipse = new Ellipse2D.Double(points[i]-5, points[i+1]-5, 10, 10);
				g2d.draw(ellipse);
				g2d.fill(ellipse);
			}
		}
		if(index == 6)
		{
			g2d.setColor(getColor());
			Ellipse2D ellipse = new Ellipse2D.Double((points[0] + points[2] + points[4])/3-5, (points[1] + points[3] + points[5])/3-5, 10, 10);
			g2d.draw(ellipse);
			g2d.fill(ellipse);

			g2d.setColor(Color.BLACK);
			Line2D line1 = new Line2D.Double(points[0], points[1], points[2], points[3]);
			Line2D line2 = new Line2D.Double(points[2], points[3], points[4], points[5]);
			Line2D line3 = new Line2D.Double(points[4], points[5], points[0], points[1]);
			Line2D lineMedian = new Line2D.Double(points[0], points[1], (points[2]+ points[4])/2, (points[3]+ points[5])/2);
			Line2D lineMedian1 = new Line2D.Double(points[2], points[3], (points[0]+ points[4])/2, (points[1]+ points[5])/2);
			Line2D lineMedian2 = new Line2D.Double(points[4], points[5], (points[0]+ points[2])/2, (points[1]+ points[3])/2);

			g2d.draw(lineMedian);
			g2d.draw(lineMedian1);
			g2d.draw(lineMedian2);
			g2d.draw(line1);
			g2d.draw(line2);
			g2d.draw(line3);		}
	}
}
