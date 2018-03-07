package Main;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame{
    
    
    public Window(){
        super("Pokemon");
        setLayout(new BorderLayout());
        setSize(32*25, 32*17);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                int input = p.showConfirmDialog("Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//                if (input == JOptionPane.YES_OPTION){
                    System.exit(0);
//                }
            }
        });
    }
    
    public void Switch(JPanel panel1, JPanel panel2){
        panel1.setVisible(false);
        remove(panel1);
        add(panel2, BorderLayout.CENTER);
        panel2.setVisible(true);
    }

    
    
}
