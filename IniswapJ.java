import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.Writer;
import java.nio.file.Paths;



public class IniswapJ extends JFrame {
	public static final String VERSION = "2.4";
	public static final String STARS_IN_INI = "********************";
	JPanel mainPanel;
	
	static JTextArea ta1,ta2,ta3,ta4;
	static JList jl1, jl2;
	//Color bg = new Color();
	
	static ArrayList<String> inifile = new ArrayList<String>();
	static ArrayList<String> inifacset = new ArrayList<String>();
	static ArrayList<String[]> facsets = new ArrayList<String[]>();
	
	static Path dir1;
	static File dir2,file1,file2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IniswapJ frame = new IniswapJ();
		checknewinstal();
		readini();
		filldata();

	}// end of main
	
	private static void checknewinstal(){
		//test
		//JOptionPane.showMessageDialog(null, "Test");
		//PrintWriter out1;
		
		// check Iniswap3 existence
		//dir1 = new File(new File(".").getAbsolutePath());
		Path path;
		try {
			path = Paths.get(IniswapJ.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			file1 = new File (path.toFile(),"Alpha Centauri.ini");
			//JOptionPane.showMessageDialog(null, "file1: " + file1);
			dir2 = new File(path.toFile(), "Iniswap3");
			dir2.mkdir();
			//JOptionPane.showMessageDialog(null, dir2);
//			if (dir2.exists() && dir2.isDirectory()) {
//				JOptionPane.showMessageDialog(null, dir2);
//			}
			file2 = new File (dir2,"ini_factions_sets.txt");
			try {
				file2.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		if (file2.length()==0){
			//JOptionPane.showMessageDialog(null, "file length 0");
			try{
			PrintWriter out1
			   = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
			// writing first lines
			out1.println("Iniswap faction sets");
			out1.println(STARS_IN_INI);
			out1.println("standard");
			out1.println("GAIANS");
			out1.println("HIVE");
			out1.println("UNIV");
			out1.println("MORGAN");
			out1.println("SPARTANS");
			out1.println("BELIEVE");
			out1.println("PEACE");
			out1.println(STARS_IN_INI);
			out1.println("new factions");
			out1.println("CYBORG");
			out1.println("PIRATES");
			out1.println("DRONE");
			out1.println("ANGELS");
			out1.println("FUNGBOY");
			out1.println("CARETAKE");
			out1.println("USURPER");
			out1.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}//end of private checknewinstal()
	
	 
	 
	private static void readini(){
		try {
			BufferedReader in1
			   = new BufferedReader(new FileReader(file2));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null, "file2: " + file2);
		
		BufferedReader in = null;
		try {   
		    in = new BufferedReader(new FileReader(file2));
		    String str;
		    while ((str = in.readLine()) != null) {
		    	inifacset.add(str);
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (in != null) {
		        try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		// show lines in arraylist - TESTING !!!!!!!!!!!!!!
//		for (String temp : inifacset) {
//			System.out.println(temp);
//		}
		// 
		
		try {   
		    in = new BufferedReader(new FileReader(file1));
		    String str;
		    while ((str = in.readLine()) != null) {
		    	inifile.add(str);
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (in != null) {
		        try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		// show lines in arraylist - TESTING !!!!!!!!!!!!!!
//		for (String temp : inifile) {
//			System.out.println(temp);
//		}
		//
		
	}
	
	private static void filldata(){
		//
		String strb = "";
		
		ta3.setText("");
		for (String temp : inifile) {
			try {
				strb = temp.substring(0,9);
				//System.out.println("strb: " + strb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}
			switch (strb){
			case "Faction 1":
				ta3.append(temp + '\n');
				break;
			case "Faction 2":
				ta3.append(temp + '\n');
				break;
			case "Faction 3":
				ta3.append(temp + '\n');
				break;
			case "Faction 4":
				ta3.append(temp + '\n');
				break;
			case "Faction 5":
				ta3.append(temp + '\n');
				break;
			case "Faction 6":
				ta3.append(temp + '\n');
				break;
			case "Faction 7":
				ta3.append(temp);
				break;
			}
			//System.out.println(temp);
		}// for inifile
		// Creating sets of 7 factions:
		
		String[] str1 = new String[8];
		String str2 = "";
		String str3 = "";
		//for ()
//		for (int i = 0; i < inifacset.size(); i++) {
//            System.out.println(inifacset.get(i));
//        }
		// special while loop
		int i = 1;
		//str2 = inifacset.get(i);
		while (i<inifacset.size()-8){
			//System.out.println("i=" + i + " "+"starting while");
			str2 = inifacset.get(i);
//			System.out.println("str2: "+str2);
//			str3 = STARS_IN_INI;
//			System.out.println("str3: "+str3);
			if(str2.equals("********************")){
				//System.out.println("i=" + i + " "+"yes, equal");
				++i;
				str2 = inifacset.get(i);
				str1[0] = str2;
				//System.out.println("i=" + i + " "+"0=" + " "+str2);
				for(int j=1;j<8;++j){				
					str2 = inifacset.get(i+j);
					str1[j] = str2;
					//System.out.println("i=" + i + " "+"j=" + j + " "+str2);
				}
			}
			i+=8;
			facsets.add(str1);
		}
		//System.out.println("i=" + i + " "+"end, while");
			
		// List box with faction sets


		
	}// end of private static void filldata()
	
	public IniswapJ(){
		super("Faction set solution for SMAX - version " + VERSION);
		this.setSize(700,500);
		//this.setLocation(40,40);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		//setTitle();
		//JPanel p1 = new JPanel();

		ta1 = new JTextArea();
		ta1.setText("Declared factions in 'ini' file:");
		ta1.setEditable(false);
		ta1.setOpaque(false);
		//ta1.setSize(200, 50);
		addcomponent(mainPanel, ta1, 0,0,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		ta2 = new JTextArea();
		ta2.setText("Factions to insert into 'ini' file:");
		ta2.setEditable(false);
		ta2.setOpaque(false);
		addcomponent(mainPanel, ta2, 2,0,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		ta3 = new JTextArea(7,25);
		ta3.setBackground(new Color(250,250,250));
		ta3.setText("1\n2\n3\n4\n5\n6\n7");
		ta3.setEditable(false);
		//ta3.setOpaque(false);
		addcomponent(mainPanel, ta3, 0,1,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		ta4 = new JTextArea(7,25);
		ta4.setBackground(new Color(250,250,250));
		ta4.setText("1\n2\n3\n4\n5\n6\n7");
		ta4.setEditable(false);
		//ta3.setOpaque(false);
		addcomponent(mainPanel, ta4, 2,1,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		jl1 = new JList();

		
		this.add(mainPanel);
//		p1.add(ta1);
//		add(p1);
//		p1.setBounds(15,15,180, 200);
//		p1.setBackground(new Color(250,250,250));
		
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		
	}// end of constructor public IniswapJ()
	
	private void addcomponent(JPanel pn, JComponent cmp, int xpos, int ypos, int w, int h, int place, int stretch){
		GridBagConstraints gridcns = new GridBagConstraints();
		gridcns.gridx = xpos;
		gridcns.gridy = ypos;
		gridcns.gridwidth = w;
		gridcns.gridheight = h;
		gridcns.weightx = 100;
		gridcns.weighty = 100;
		gridcns.insets = new Insets(5,5,5,5);
		gridcns.anchor = place;
		gridcns.fill = stretch;
		
		pn.add(cmp, gridcns);
	} // end private void addcomponent

}
