import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;


public class IniswapJ extends JFrame {
	JPanel p1 = new JPanel();
	JTextArea ta1 = new JTextArea();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IniswapJ frame = new IniswapJ();

	}
	
	public IniswapJ(){
		super("Faction set solution for SMAX - version 2.4");
		setSize(700,500);
		setLocation(40,40);
		//setTitle();
		//JPanel p1 = new JPanel();
		ta1.setText("Declared factions in\n'Alpha Centauri.ini' file:");
		ta1.setOpaque(false);
		p1.add(ta1);
		add(p1);
		//frame.add(p1);
		
		setVisible(true);
		
	}

}
