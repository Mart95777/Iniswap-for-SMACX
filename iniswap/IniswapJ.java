package iniswap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;




public class IniswapJ extends JFrame {
	public static final String VERSION = "2.4";
	public static final String STARS_IN_INI = "********************";
	JPanel mainPanel;
	
	/**
	 * Text areas
	 * ta1 - 
	 * ta2 - 
	 * ta3 - initial faction set
	 * ta4 - 
	 * ta5 - new set name
	 * ta6 - label for new set name
	 */
	static JTextArea ta1,ta2,ta3,ta4,ta5,ta6;
	static DefaultListModel model, model1;
	static JList jl1, jl2;
	
	static JButton butAddSet;
	static JButton butPlay;
	
	// strings for launching
	String sSet = "";
	String sExe = "";
	static String str1g = ""; //path to game folder
	//Color bg = new Color();
	
	static ArrayList<String> inifile = new ArrayList<String>();
	static ArrayList<String> inifacset = new ArrayList<String>();
	static ArrayList<ArrayList<String>> facsets = new ArrayList<ArrayList<String>>();
	static ArrayList<String> inifacsetX = new ArrayList<String>();
	/**
	 * exesFiles - takes all files from the folder
	 * exes - lists only executables, which is index of exesFiles
	 */
	static File[] exesFiles;
	//static ArrayList<Integer> exes = new ArrayList<Integer>();
	
	static Path dir1;
	/**
	 dir 2 - 
	 file1 - Alpha Centauri.ini
	 file2 - ini_factions_sets.txt file
	 */
	static File dir2,file1,file2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IniswapJ frame = new IniswapJ();
		//JOptionPane.showMessageDialog(null, "after constructor");
		checknewinstal();
		readini();
		filldata();
		// testing
		//System.out.println("facsets: "+ facsets.get(0).get(0));

	}// end of main
	
	private static void checknewinstal(){
		/**
		 * This method is intended to make all necessary settings when used in the game folder
		 * It performs the following tasks:
		 * 1) gets the folder in which the jar is started
		 * 2) lists exe files, inserted to the executables list
		 * 3) checks for existence of Alpha Centauri.ini
		 * 4) checks for Iniswap3 folder
		 * 5) checks for ini_factions_sets.txt
		 * 6) if the file in (5) is not existing, creates the default one.
		 */
		//Getting path to folder
		StringBuilder str1 = new StringBuilder();
		StringBuilder str2 = new StringBuilder();
		str1.append(new File(".").getAbsolutePath());
		File runningFolder = null;
		runningFolder = new File(str1.toString()).getParentFile();
					
		str2.setLength(0);
		str2.append(runningFolder.toString());
		str2.append('\\');
			
		str1g = str2.toString();
		//str1g now holds string to the jar file folder
		// list of exe files. They also need to start with terranx
		try {
			exesFiles = new File(str1g).listFiles();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Problem with listing exe files");
			e1.printStackTrace();
		}
		// checking executables
		int j;
		char c;
		//int k = 0;
		for (File temp : exesFiles){
			str1.setLength(0);
			str1.append(temp.getName().toString());
			if(str1.toString().length()>0 && str1.toString().endsWith("exe") && str1.toString().startsWith("terranx")){
				model1.addElement(str1.toString());
			}
			// check if there is only one element, if so, make it selected
			if(model1.length()==1){
				
			}
		};
		
		// Alpha Centauri.ini
		file1 = new File (str1g,"Alpha Centauri.ini");
		if(file1.length()==0){
			JOptionPane.showMessageDialog(null, 
					"Alpha Centauri.ini file does not exist, or is zero length."
					+"\nIs jar file in the game folder?"
					+"\nCheck SMACX installation or start the game to create ini file."
					+"\nQuiting...");
			System.exit(0);
		}
		// Iniswap3 folder
		dir2 = new File(str1g, "Iniswap3");
		dir2.mkdir();
		file2 = new File (dir2,"ini_factions_sets.txt");
		try {
			file2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Problem with checking existence of ini_factions_sets.txt file");
			e.printStackTrace();
		}
			
		if (file2.length()==0){
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
				JOptionPane.showMessageDialog(null, "Problem with setting ini_factions_sets.txt file");
				e.printStackTrace();
			}	
		}
		
	}//end of private checknewinstal()

	private static void readini(){
		/**
		 * This method:
		 * 1) opens ini_factions_sets.txt file (file2)
		 * 2) loads lines to inifacset ArrayList
		 * 3) opens Alpha Centauri.ini file
		 * 4) loads lines to inifile ArrayList
		 */
		try {
			BufferedReader in1 = new BufferedReader(new FileReader(file2));
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
		for (String temp : inifacset) {
			System.out.println(temp);
		}
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
		/**
		 * This method:
		 * 1) Inserts the factions from current ini file to ta3
		 * 2) 
		 */
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
		
		//ArrayList<String> str1 = new ArrayList<String>();
		String str2 = "";
		String str3 = "";
		//for ()
//		for (int i = 0; i < inifacset.size(); i++) {
//            System.out.println(inifacset.get(i));
//        }
		
		// special while loop
		int i = 1;
		int i1 = 0;
		//str2 = inifacset.get(i);
		while (i<inifacset.size()-8){
			//System.out.println("i=" + i + " "+"starting while");
			facsets.add(new ArrayList<String>());
			str2 = inifacset.get(i);
//			System.out.println("str2: "+str2);
//			str3 = STARS_IN_INI;
//			System.out.println("str3: "+str3);
			if(str2.equals("********************")){
				//System.out.println("i=" + i + " "+"yes, equal");
				++i;
				str2 = inifacset.get(i);
				facsets.get(i1).add(str2);
				for(int j=1;j<8;++j){				
					str2 = inifacset.get(i+j);
					facsets.get(i1).add(str2);
				}
			}
			i+=8;
			i1++;
			
		}//while
		//JOptionPane.showMessageDialog(null, "end of while loop");
		// List box with faction sets
		// actual faction sets 
		for (int k=0;k<facsets.size();k++){
			model.addElement(facsets.get(k).get(0));
		}
		//jl1.setVisibleRowCount(8);
		//JOptionPane.showMessageDialog(null, "model - elements");
		// the right list of exe
		// ... done in checknewinstall
//		for (File temp : exes ){
//			model1.addElement(temp.toString());
//		}


		
	}// end of private static void filldata()
	
	private static void launchExe(String str){

		  try {
			Process process = new ProcessBuilder(str1g+str).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void saveFactionSets(){
		try{
			PrintWriter out1
			   = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
			// writing first lines
			out1.println("Iniswap faction sets");
			for (ArrayList<String> temp : facsets){
				out1.println(STARS_IN_INI);
				for (String temp1 : temp){
					out1.println(temp1);
				}
			}
			out1.close();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null, "Problem with setting ini_factions_sets.txt file");
				e.printStackTrace();
			}	
	}
	
	private static void replaceFactionSet(){
		String strb = "";
		for (int i = 0; i < inifile.size(); ++i) {
			try {
				if (inifile.get(i).length()>9){
				strb = inifile.get(i).substring(0,9);
				}
				//JOptionPane.showMessageDialog(null, "strb: " + strb);
				//System.out.println("strb: " + strb);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problem with reading a line in ArrayList of Alpha Centauri ini file");
				continue;
			}
			switch (strb){
			case "Faction 1":
				inifile.set(i,"Faction 1="+inifacsetX.get(1));
				break;
			case "Faction 2":
				inifile.set(i,"Faction 2="+inifacsetX.get(2));
				break;
			case "Faction 3":
				inifile.set(i,"Faction 3="+inifacsetX.get(3));
				break;
			case "Faction 4":
				inifile.set(i,"Faction 4="+inifacsetX.get(4));
				break;
			case "Faction 5":
				inifile.set(i,"Faction 5="+inifacsetX.get(5));
				break;
			case "Faction 6":
				inifile.set(i,"Faction 6="+inifacsetX.get(6));
				break;
			case "Faction 7":
				inifile.set(i,"Faction 7="+inifacsetX.get(7));
				break;
			}
		}
	}
	
	private static void saveAlphaCenIni(){
		try{
			PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(file1)));
			for (int i = 0; i < inifile.size(); ++i){
				out1.println(inifile.get(i));
			}
			out1.close();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null, "Problem with saving Alpha Centauri.ini file");
				e.printStackTrace();
			}	
	}
	
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
		addcomponent(mainPanel, ta1, 0,0,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		ta2 = new JTextArea();
		ta2.setText("Factions to insert into 'ini' file:");
		ta2.setEditable(false);
		ta2.setOpaque(false);
		addcomponent(mainPanel, ta2, 2,0,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		ta3 = new JTextArea(7,20);
		ta3.setBackground(new Color(250,250,250));
		ta3.setText("1\n2\n3\n4\n5\n6\n7");
		ta3.setEditable(false);
		//ta3.setOpaque(false);
		addcomponent(mainPanel, ta3, 0,1,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		ta4 = new JTextArea(7,20);
		ta4.setBackground(new Color(250,250,250));
		ta4.setText("1\n2\n3\n4\n5\n6\n7");
		ta4.setEditable(false);
		//ta3.setOpaque(false);
		addcomponent(mainPanel, ta4, 2,1,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		ta6 = new JTextArea();
		ta6.setText("Give name for the new set:");
		ta6.setEditable(false);
		ta6.setOpaque(false);
		addcomponent(mainPanel, ta6, 1,2,2,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		model = new DefaultListModel();
		jl1 = new JList(model);
		JScrollPane jscrl = new JScrollPane(jl1);
		jscrl.setPreferredSize(new Dimension(200,120));
		model.addElement(null);
		// test 15 elements
//		for (int i = 0; i < 15; i++)
//		      model.addElement("Element " + i);
		
		jl1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addcomponent(mainPanel, jscrl, 0,2,1,4, GridBagConstraints.WEST, GridBagConstraints.NONE);
		jl1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lsEvent) {
				if (! lsEvent.getValueIsAdjusting())
				{
					inifacsetX = facsets.get(jl1.getSelectedIndex()-1);
					StringBuilder strB1 = new StringBuilder("");
					//strB1 = "";
					for(int j=1;j<7;++j){
						strB1.append(inifacsetX.get(j));
						strB1.append("\n");
					}
					strB1.append(inifacsetX.get(7));
					ta4.setText(strB1.toString());
					
					//System.out.println("Selected: " + (jl1.getSelectedIndex()-1));
					//ta4.setText("1x\n2\n3\n4\n5\n6\n7");
					//System.out.println(lsEvent.getSource());
				}
				
			}
		});

		model1 = new DefaultListModel();
		jl2 = new JList(model1);
		JScrollPane jscrl1 = new JScrollPane(jl2);
		jscrl1.setPreferredSize(new Dimension(200,120));
		model1.addElement(null);
		// test 15 elements
//		for (int i = 0; i < 15; i++)
//		      model.addElement("Element " + i);
		
		jl2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addcomponent(mainPanel, jscrl1, 2,2,1,4, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		// adding new set
		ta5 = new JTextArea(1,17);
		ta5.setBackground(new Color(250,250,250));
		ta5.setText("");
		ta5.setEditable(true);
		addcomponent(mainPanel, ta5, 1,3,1,1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		
		butAddSet = new JButton("Add existing set to the list");
		butAddSet.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  /////////////////////////////
				  String stra = ta5.getText();
					String strb = "";
					ArrayList<String> nextFactionSet = new ArrayList<String>();
					nextFactionSet.add(stra);
					//i1 = facsets.size();
					
					for (String temp : inifile) {
						try {
							stra = temp.substring(10,temp.length());
							strb = temp.substring(0,9);
							//System.out.println("strb: " + strb);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							continue;
						}
						switch (strb){
						case "Faction 1":
							nextFactionSet.add(stra);
							break;
						case "Faction 2":
							nextFactionSet.add(stra);
							break;
						case "Faction 3":
							nextFactionSet.add(stra);
							break;
						case "Faction 4":
							nextFactionSet.add(stra);
							break;
						case "Faction 5":
							nextFactionSet.add(stra);
							break;
						case "Faction 6":
							nextFactionSet.add(stra);
							break;
						case "Faction 7":
							nextFactionSet.add(stra);
							break;
						}
						//System.out.println(temp);
					}// for inifile
					facsets.add(nextFactionSet);
					//int k = facsets.size()-1;
					model.addElement(facsets.get(facsets.size()-1).get(0));
			  }
		});
		addcomponent(mainPanel, butAddSet, 1,4,1,1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		
		butPlay = new JButton("Play selected set / exe");
		butPlay.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  int selected1 = jl1.getSelectedIndex();
				  if (selected1>-1){
					  sSet = jl1.getModel().getElementAt(selected1).toString();
					  // exe
					  int selected2 = jl2.getSelectedIndex();
					  if (selected2>-1){
						  sExe = jl2.getModel().getElementAt(selected2).toString();
						  
						  /** Set of events during launching
						   * 1 - saving the sets file.
						   * 2 - saving new ini file
						   */
						  saveFactionSets();
						  replaceFactionSet();
						  saveAlphaCenIni();
						  
						  //int m = jl2.getSelectedIndex();
						  //JOptionPane.showMessageDialog(null, "m: " + m);
						  //JOptionPane.showMessageDialog(null, "get m: " + exes.get(m) );
						  //m = exes.get(m);
						  //sExe = exesFiles[m].toString();
						  JOptionPane.showMessageDialog(null, sExe);
						  // LAUNCHING !!!
						  JOptionPane.showMessageDialog(null, "LAUNCHING !!!" + " " + sExe);
						  
						  
						  //launchExe("terranx.exe");
						  launchExe(sExe);
						  
						  
						  
						  
						  //Process process = new ProcessBuilder(str1.toString()+"terranx.exe").start();
						  System.exit(0);
					  }else{
						  JOptionPane.showMessageDialog(null, "Please select executable file!");
					  }
				  }else{
					  JOptionPane.showMessageDialog(null, "Please select faction set!");
				  }
			  }
			});
		addcomponent(mainPanel, butPlay, 1,5,1,1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		
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
