import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {

    public String[] colors = {"black", "red", "blue", "green", "white"};
    public Color activeColor;
    public Test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 600);
        setVisible(true);
        repaint();

        buildScreen();
        
    }

    public void buildScreen(){
        Points p = new Points();
        p.setBounds(10, 10, 200, 200);
        for(int i = 0; i < 5; i++){
            JButton button = new JButton();
            button.setText(colors[i]);
            button.setBounds(60 + i*102, 300, 70, 70);

            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(button.getText().equals("black")){
                        p.setColor(Color.BLACK);
                    }else if(button.getText().equals("red")){
                        p.setColor(Color.RED);
                    }else if(button.getText().equals("blue")){
                        p.setColor(Color.BLUE);
                    }else if(button.getText().equals("green")){
                        p.setColor(Color.GREEN);
                    }else if(button.getText().equals("white")){
                        p.setColor(Color.WHITE);
                    }
                    
                }
                
            });
            add(button);
        }

        add(p);


    }

    public static void main(String[] args) throws Exception {
        new Test();
    }
}

