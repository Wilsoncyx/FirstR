package mipstomachine;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class OpWindow {

	private JFrame frame;
	private JTextField filepath;
	private JTextField resultpath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpWindow window = new OpWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JButton btnTranslate = new JButton("Translate");
		btnTranslate.setBounds(159, 213, 113, 27);
		frame.getContentPane().add(btnTranslate);
		
		JLabel label = new JLabel("汇编语言文件路径");
		label.setBounds(35, 58, 120, 18);
		frame.getContentPane().add(label);
		
		filepath = new JTextField();
		filepath.setBounds(159, 55, 180, 24);
		frame.getContentPane().add(filepath);
		filepath.setColumns(10);
		
		JLabel label_1 = new JLabel("机器码存放路径");
		label_1.setBounds(35, 121, 120, 18);
		frame.getContentPane().add(label_1);
		
		resultpath = new JTextField();
		resultpath.setBounds(159, 118, 180, 24);
		frame.getContentPane().add(resultpath);
		resultpath.setColumns(10);
		
		//JFileChooser fd = new JFileChooser();
		//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//fd.showOpenDialog(null);
		//File f = fd.getSelectedFile();
		//if(f!=null){}
		
		/*JFileChooser jf = new JFileChooser();
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
		jf.showDialog(null,null);
		File fi =jf.getSelectedFile();
		String f1 = fi.getAbsolutePath()+"\\test.txt";
		System.out.println("save: "+f1);
		try{
			FileWriter out = new FileWriter(f1);
			out.write("successful!!!");
			out.close();
		}
		catch(Exception e){}*/
		
		btnTranslate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				methodbtn(filepath,resultpath);
			}
		});
	}
	
	public static void methodbtn(JTextField tF1,JTextField tF2){
		String tf1=tF1.getText();
		String tf2=tF2.getText();
		change.main(tf1, tf2);
	}
}
