package PersonMessage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import PersonMessage.Staff;

import javax.swing.*;

public class Test3{
	private JFrame f = new JFrame();
	private PLand pLand = new PLand();
	private PChoose pChoose = new PChoose();
	private PMessage pMessage;
	
	public Test3(){
		f.add(pLand);
		f.setSize(800,600);
		f.setIconImage(new ImageIcon("D:/Important/EXE/"
				+ "PersonMessage/ͼ��/logo.png").getImage());
		f.setTitle("MessageCharge");
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class PLand extends JPanel{
		
		private JLabel lblUsername = new JLabel("�û���(Admin):");
		private JLabel lblPassword = new JLabel("����:");
		
		private JLabel jlbUser = new JLabel("�û�������Ϊ��");
		
		
		private TextField txtUsername = new TextField("",15);
		private TextField txtPassword = new TextField("",15);
		
		private JButton jbtLand = new JButton("��½");
		private JButton jbtExit = new JButton("�˳�");
		
		public PLand(){
			
			add(lblUsername);
			add(txtUsername);
			add(lblPassword);
			add(txtPassword);
			add(jbtLand);
			add(jbtExit);
			add(jlbUser);
			
			lblUsername.setFont(new Font("����",Font.PLAIN,20));
			txtUsername.setFont(new Font("����",Font.PLAIN,20));
			
			lblPassword.setFont(new Font("����",Font.PLAIN,20));
			txtPassword.setFont(new Font("����",Font.PLAIN,20));
			
			jbtLand.setFont(new Font("����",Font.PLAIN,20));
			jbtExit.setFont(new Font("����",Font.PLAIN,20));
			
			jlbUser.setFont(new Font("����",Font.PLAIN,20));
			
			jlbUser.setVisible(false);
			
			txtPassword.setEchoChar('��');				//����������ʾ
			
			jbtLand.addActionListener(new jbtActionListener());
			jbtExit.addActionListener(new jbtActionListener());
		}
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(new ImageIcon
				("D:/Important/EXE/PersonMessage/ͼ��/����.jpg").getImage(),
				0,0,null);
			
			g.setFont(new Font("�п�",Font.PLAIN,40));
			g.setColor(Color.red);
			g.drawString("��Ա��Ϣ����ϵͳ",240,80);
			
			lblUsername.setLocation(225,200);
			txtUsername.setLocation(365,200);
			
			lblPassword.setLocation(270,260);
			txtPassword.setLocation(365,260);
			
			jbtLand.setLocation(300,320);
			jbtExit.setLocation(420,320);
			
			jlbUser.setLocation(225,380);
		}
		
		public void isLand(){

			if(txtUsername.getText().equals("")){
				jlbUser.setVisible(true);
			}
			else if(txtPassword.getText().equals("")){
				jlbUser.setText("���벻��Ϊ��");
				jlbUser.setVisible(true);
			}
			else if(txtUsername.getText().equals("����") &&
					txtPassword.getText().equals("LF20forYY")){
				jlbUser.setVisible(false);
				f.remove(pLand);
				f.repaint();
				f.add(pChoose);
				f.validate();
			}
			else{
				jlbUser.setText("�����������Ա�����ڣ�������");
				jlbUser.setVisible(true);
			}
		}
		
		private class jbtActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = pLand.getGraphics();
				g.setFont(new Font("����",Font.PLAIN,20));
				g.setColor(Color.red);
				
				if(e.getSource() == jbtLand){
					isLand();
				}
				else if(e.getSource() == jbtExit){
					System.exit(0);
				}
			}
		}
	}
	
	private class PChoose extends JPanel{
		private JButton jbtPerson = new JButton("Person");
		private JButton jbtEmployee = new JButton("Employee");
		private JButton jbtFaculty = new JButton("Faculty");
		private JButton jbtStaff = new JButton("Staff");
		private JButton jbtStudent = new JButton("Student");
		private JButton jbtPostGraduate = new JButton("PostGraduate");
		
		public PChoose(){
			add(jbtPerson);
			add(jbtEmployee);
			add(jbtFaculty);
			add(jbtStaff);
			add(jbtStudent);
			add(jbtPostGraduate);
			
			jbtPerson.setFont(new Font("����",Font.PLAIN,20));		//��������
			jbtEmployee.setFont(new Font("����",Font.PLAIN,20));
			jbtFaculty.setFont(new Font("����",Font.PLAIN,20));
			jbtStaff.setFont(new Font("����",Font.PLAIN,20));
			jbtStudent.setFont(new Font("����",Font.PLAIN,20));
			jbtPostGraduate.setFont(new Font("����",Font.PLAIN,20));
			
			jbtPerson.addActionListener(new jbtActionLisener());	//������
			jbtEmployee.addActionListener(new jbtActionLisener());
			jbtFaculty.addActionListener(new jbtActionLisener());
			jbtStaff.addActionListener(new jbtActionLisener());
			jbtStudent.addActionListener(new jbtActionLisener());
			jbtPostGraduate.addActionListener(new jbtActionLisener());
			
		}
		
		private class jbtActionLisener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == jbtPerson){
					pMessage = new PMessage("Person");
				}
				else if(e.getSource() == jbtEmployee){
					pMessage = new PMessage("Employee");
				}
				else if(e.getSource() == jbtFaculty){
					pMessage = new PMessage("Faculty");
				}
				else if(e.getSource() == jbtStaff){
					pMessage = new PMessage("Staff");
				}
				else if(e.getSource() == jbtStudent){
					pMessage = new PMessage("Student");
				}
				else if(e.getSource() == jbtPostGraduate){
					pMessage = new PMessage("PostGraduate");
				}
				
				f.remove(pChoose);
				f.repaint();
				f.add(pMessage);
				f.validate();
			}
		}
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(new ImageIcon
				("D:/Important/EXE/PersonMessage/ͼ��/����.jpg").getImage(),
				0,0,null);
			
			g.setFont(new Font("�п�",Font.PLAIN,40));
			g.setColor(Color.red);
			g.drawString("ѡ��Ҫ��ѯ������",240,80);

			jbtPerson.setLocation(350,140);
			jbtEmployee.setLocation(220,250);
			jbtFaculty.setLocation(160,360);
			jbtStaff.setLocation(300,360);
			jbtStudent.setLocation(460,250);
			jbtPostGraduate.setLocation(435,360);

			((Graphics2D)g).setStroke(new BasicStroke(3));
			
			g.drawLine(jbtPerson.getX() + jbtPerson.getWidth() / 2,
					jbtPerson.getY() + jbtPerson.getHeight(),
					jbtEmployee.getX() + jbtEmployee.getWidth() / 2,
					jbtEmployee.getY());
			
			g.drawLine(jbtEmployee.getX() + jbtEmployee.getWidth() / 2,
					jbtEmployee.getY() + jbtEmployee.getHeight(),
					jbtFaculty.getX() + jbtFaculty.getWidth() / 2,
					jbtFaculty.getY());
			
			g.drawLine(jbtEmployee.getX() + jbtEmployee.getWidth() / 2,
					jbtEmployee.getY() + jbtEmployee.getHeight(),
					jbtStaff.getX() + jbtStaff.getWidth() / 2,
					jbtStaff.getY());
			
			g.drawLine(jbtPerson.getX() + jbtPerson.getWidth() / 2,
					jbtPerson.getY() + jbtPerson.getHeight(),
					jbtStudent.getX() + jbtStudent.getWidth() / 2,
					jbtStudent.getY());
			
			g.drawLine(jbtStudent.getX() + jbtStudent.getWidth() / 2,
					jbtStudent.getY() + jbtStudent.getHeight(),
					jbtPostGraduate.getX() + jbtPostGraduate.getWidth() / 2,
					jbtPostGraduate.getY());
			
		}
	}
	
	private class PMessage extends JPanel{
		
		private JButton jbtReturn = new JButton("Return");
		private JButton jbtAdd = new JButton("Add");
		private JButton jbtSA = new JButton("SaveA");
		private JButton jbtChange = new JButton("Change");
		private JButton jbtSave = new JButton("SaveC");
		private JButton jbtDel = new JButton("Del");
		
		private JLabel jlbRemind = new JLabel("");				//��ʾ��
		
		//JLabel
		private JLabel jlbName = new JLabel("����");			//Person
		private JLabel jlbAddress = new JLabel("��ַ");
		private JLabel jlbPhone = new JLabel("�绰����");
		private JLabel jlbEmail = new JLabel("����");
		
		private JLabel jlbOffice = new JLabel("�칫��");		//Employee
		private JLabel jlbPay = new JLabel("����");
		private JLabel jlbMyDate = new JLabel("��Ƹ����");
		
		private JLabel jlbTime = new JLabel("����ʱ��");			//Faculty
		private JLabel jlbLevel = new JLabel("�����ȼ�");
		
		private JLabel jlbWorkName = new JLabel("��������");		//Staff
		
		private JLabel jlbGrade = new JLabel("�꼶");			//Student
		
		private JLabel jlbTeacher = new JLabel("ָ����ʦ");		//Post
		private JLabel jlbTarget = new JLabel("�о�����");
		
		//JTextFiled
		private JTextField jtfName = new JTextField(20);			//Person
		private JTextField jtfAddress = new JTextField(20);
		private JTextField jtfPhone = new JTextField(20);
		private JTextField jtfEmail = new JTextField(20);
		
		private JTextField jtfOffice = new JTextField(20);		//Employee
		private JTextField jtfPay = new JTextField(20);
		private JTextField jtfMyDate = new JTextField(20);
		
		private JTextField jtfTime = new JTextField(20);			//Faculty
		private JTextField jtfLevel = new JTextField(20);
		
		private JTextField jtfWorkName = new JTextField(20);		//Staff
		
		private JTextField jtfGrade = new JTextField(20);			//Student
		
		private JTextField jtfTeacher = new JTextField(20);		//Post
		private JTextField jtfTarget = new JTextField(20);
		
		private JRadioButton jrbPerson,jrbEmployee,jrbFaculty,		//��ť
								jrbStaff,jrbStudent,jrbPost;
		private ButtonGroup group = new ButtonGroup();			//��ťȺ
		
		private ArrayList<Person> p = new ArrayList<>();		//������
		
		private ArrayList<Person> PERSON = new ArrayList<>();		
		private ArrayList<Employee> EMPLOYEE = new ArrayList<>();
		private ArrayList<Faculty> FACULTY = new ArrayList<>();
		private ArrayList<Staff> STAFF = new ArrayList<>();
		private ArrayList<Student> STUDENT = new ArrayList<>();
		private ArrayList<PostGraduate> POST = new ArrayList<>();
		
		private Person[] person;
		private Employee[] employee;
		private Faculty[] faculty;
		private Staff[] staff;
		private Student[] student;
		private PostGraduate[] post;
		
		private String[] name;
		private JComboBox jcName = new JComboBox();
		
		private String whichButton = "";
		
		public PMessage(){
			
			add(jcName);
			jcName.setPreferredSize(new Dimension(250,30));
			
										//������
			//JButton
			add(jbtAdd);
			add(jbtSA);
			add(jbtDel);
			add(jbtChange);
			add(jbtSave);
			add(jbtReturn);
			
			//JLabel			
			add(jlbName);				//Person
			add(jlbAddress);
			add(jlbPhone);
			add(jlbEmail);
			
			add(jlbOffice);				//Employee
			add(jlbPay);
			add(jlbMyDate);
			
			add(jlbTime);				//Faculty
			add(jlbLevel);
			
			add(jlbWorkName);			//Staff
			
			add(jlbGrade);				//Student
			
			add(jlbTeacher);			//Post
			add(jlbTarget);
			
			//JTextFiled
			add(jtfName);				//Person
			add(jtfAddress);
			add(jtfPhone);
			add(jtfEmail);
			
			add(jtfOffice);				//Employee
			add(jtfPay);
			add(jtfMyDate);
			
			add(jtfTime);				//Faculty
			add(jtfLevel);
			
			add(jtfWorkName);			//Staff
			
			add(jtfGrade);				//Student
			
			add(jtfTeacher);			//Post
			add(jtfTarget);
			
			add(jrbPerson = new JRadioButton("Person",true));	//��Ӱ�ť
			add(jrbEmployee = new JRadioButton("Employee"));
			add(jrbFaculty = new JRadioButton("Faculty"));
			add(jrbStaff = new JRadioButton("Staff"));
			add(jrbStudent = new JRadioButton("Student"));
			add(jrbPost = new JRadioButton("PostGraduate"));
			
			group.add(jrbPerson);		//��Ӱ�ť����ťȺ
			group.add(jrbEmployee);
			group.add(jrbFaculty);
			group.add(jrbStaff);
			group.add(jrbStudent);
			group.add(jrbPost);
			
			jrbGroupSetVeiw(false);			//��ť���ɼ�
			
			
			add(jlbRemind);
			jbtSA.setVisible(false);
			jbtSave.setVisible(false);
			
			setEdit(false);				//���ò����޸�
			
			set();						//��������
			
			//��ť��Ӽ�����
			jbtReturn.addActionListener(new jbtActionListener());
			jbtSA.addActionListener(new jbtActionListener());
			jbtAdd.addActionListener(new jbtActionListener());
			jbtDel.addActionListener(new jbtActionListener());
			jbtChange.addActionListener(new jbtActionListener());
			jbtSave.addActionListener(new jbtActionListener());
			
			//��ťȺ��Ӽ�����
			jrbPerson.addActionListener(new jrbActionListener());
			jrbEmployee.addActionListener(new jrbActionListener());
			jrbFaculty.addActionListener(new jrbActionListener());
			jrbStaff.addActionListener(new jrbActionListener());
			jrbStudent.addActionListener(new jrbActionListener());
			jrbPost.addActionListener(new jrbActionListener());
		}
		
		public void jrbGroupSetVeiw(boolean b){

			jrbPerson.setVisible(b);				//���ð�ť�ɼ�
			jrbEmployee.setVisible(b);
			jrbFaculty.setVisible(b);
			jrbStaff.setVisible(b);
			jrbStudent.setVisible(b);
			jrbPost.setVisible(b);
		}
		
		public void setEdit(boolean b){
			jtfName.setEditable(b);
			jtfAddress.setEditable(b);
			jtfPhone.setEditable(b);
			jtfEmail.setEditable(b);
			jtfOffice.setEditable(b);
			jtfPay.setEditable(b);
			jtfMyDate.setEditable(false);
			jtfTime.setEditable(b);
			jtfLevel.setEditable(b);
			jtfWorkName.setEditable(b);
			jtfGrade.setEditable(false);
			jtfTeacher.setEditable(b);
			jtfTarget.setEditable(b);
		}
		
		public void set(){
			//JButton
			jbtReturn.setFont(new Font("����",Font.PLAIN,20));
			jbtSA.setFont(new Font("����",Font.PLAIN,20));
			jbtAdd.setFont(new Font("����",Font.PLAIN,20));
			jbtDel.setFont(new Font("����",Font.PLAIN,20));
			jbtChange.setFont(new Font("����",Font.PLAIN,20));
			jbtSave.setFont(new Font("����",Font.PLAIN,20));
			
			//JTabel
			jlbName.setFont(new Font("����",Font.PLAIN,30));
			jlbAddress.setFont(new Font("����",Font.PLAIN,30));
			jlbPhone.setFont(new Font("����",Font.PLAIN,30));
			jlbEmail.setFont(new Font("����",Font.PLAIN,30));
			jlbOffice.setFont(new Font("����",Font.PLAIN,30));
			jlbPay.setFont(new Font("����",Font.PLAIN,30));
			jlbMyDate.setFont(new Font("����",Font.PLAIN,30));
			jlbTime.setFont(new Font("����",Font.PLAIN,30));
			jlbLevel.setFont(new Font("����",Font.PLAIN,30));
			jlbWorkName.setFont(new Font("����",Font.PLAIN,30));
			jlbGrade.setFont(new Font("����",Font.PLAIN,30));
			jlbTeacher.setFont(new Font("����",Font.PLAIN,30));
			jlbTarget.setFont(new Font("����",Font.PLAIN,30));
			
			//JTextFiled
			jtfName.setFont(new Font("����",Font.PLAIN,20));
			jtfAddress.setFont(new Font("����",Font.PLAIN,20));
			jtfPhone.setFont(new Font("����",Font.PLAIN,20));
			jtfEmail.setFont(new Font("����",Font.PLAIN,20));
			jtfOffice.setFont(new Font("����",Font.PLAIN,20));
			jtfPay.setFont(new Font("����",Font.PLAIN,20));
			jtfMyDate.setFont(new Font("����",Font.PLAIN,20));
			jtfTime.setFont(new Font("����",Font.PLAIN,20));
			jtfLevel.setFont(new Font("����",Font.PLAIN,20));
			jtfWorkName.setFont(new Font("����",Font.PLAIN,20));
			jtfGrade.setFont(new Font("����",Font.PLAIN,20));
			jtfTeacher.setFont(new Font("����",Font.PLAIN,20));
			jtfTarget.setFont(new Font("����",Font.PLAIN,20));
			jlbRemind.setFont(new Font("����",Font.PLAIN,20));
			
			jrbPerson.setFont(new Font("����",Font.PLAIN,20));
			jrbEmployee.setFont(new Font("����",Font.PLAIN,20));
			jrbFaculty.setFont(new Font("����",Font.PLAIN,20));
			jrbStaff.setFont(new Font("����",Font.PLAIN,20));
			jrbStudent.setFont(new Font("����",Font.PLAIN,20));
			jrbPost.setFont(new Font("����",Font.PLAIN,20));
		}

		public PMessage(String which){
			this();
			this.whichButton = which;
			try {
				readFile();					//���ļ�
				addObject();				//��Ӷ���
			} catch (Exception e) {
				// TODO Auto-generated catch block��Ӷ���
				jlbRemind.setVisible(true);
				jlbRemind.setText("���������������Ϣ���ļ����𻵻򲻴��ڣ�����ӱ����ļ�"
						+ "���½���Ա��Ϣ���������ļ�");
			}
		}
		
		public void addObject(){							//��Ӷ���
			if(whichButton == "Person" && person != null){
				for(int i = 0;i < person.length;i++){
					p.add(person[i]);
					PERSON.add(person[i]);
				}
				if(employee != null){
					for(int i = 0;i < employee.length;i++){
						p.add(employee[i]);
						EMPLOYEE.add(employee[i]);
					}
				}
				
				if(faculty != null){
					for(int i = 0;i < faculty.length;i++){
						p.add(faculty[i]);
						FACULTY.add(faculty[i]);
					}
				}
				
				if(staff != null){
					for(int i = 0;i < staff.length;i++){
						p.add(staff[i]);
						STAFF.add(staff[i]);
					}
				}
				
				if(student != null){
					for(int i = 0;i < student.length;i++){
						p.add(student[i]);
						STUDENT.add(student[i]);
					}
				}
				
				if(post != null){
					for(int i = 0;i < post.length;i++){
						p.add(post[i]);
						POST.add(post[i]);
					}
				}
			}
			else if(whichButton == "Employee" && employee != null){
				for(int i = 0;i < employee.length;i++){
					p.add(employee[i]);
					EMPLOYEE.add(employee[i]);
				}
				
				if(faculty != null){
					for(int i = 0;i < faculty.length;i++){
						p.add(faculty[i]);
						FACULTY.add(faculty[i]);
					}
				}
				
				if(staff != null){
					for(int i = 0;i < staff.length;i++){
						p.add(staff[i]);
						STAFF.add(staff[i]);
					}
				}
			}
			else if(whichButton == "Faculty" && faculty != null){
				for(int i = 0;i < faculty.length;i++){
					p.add(faculty[i]);
					FACULTY.add(faculty[i]);
				}
			}
			else if(whichButton == "Staff" && staff != null){
				for(int i = 0;i < staff.length;i++){
					p.add(staff[i]);
					STAFF.add(staff[i]);
				}
			}
			else if(whichButton == "Student" && student != null){
				for(int i = 0;i < student.length;i++){
					p.add(student[i]);
					STUDENT.add(student[i]);
				}
				
				if(post != null){
					for(int i = 0;i < post.length;i++){
						p.add(post[i]);
						POST.add(post[i]);
					}
				}
			}
			else if(whichButton == "PostGraduate" && post != null){
				for(int i = 0;i < post.length;i++){
					p.add(post[i]);
					POST.add(post[i]);
				}
			}
			
			name = new String[p.size()];
			
			if(p.size() != 0){							//����ļ��Ƿ�ɹ�����
				
				for(int i = 0;i < name.length;i++){
					name[i] = p.get(i).getName();
					
					jcName.addItem(p.get(i).getName());			//�����б�
	//����̨���				
					display(0);
				}
				
				jcName.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						int n = jcName.getSelectedIndex();
						display(n);
					}
				});
			}
			else{
				jlbRemind.setText("��ǰû�и�����Ա¼�룬���ֶ�������Ա��Ϣ");
				jlbRemind.setVisible(true);
			}
		}
		
		public void readPersonFile() throws Exception{
			
			try(
				ObjectInputStream inputPerson = new ObjectInputStream
						(new FileInputStream("D:/Important/EXE/"
								+ "PersonMessage/txt/Person.dat"));
			){
				person = (Person[])(inputPerson.readObject());
			}
		}
		
		public void readEmployeeFile() throws Exception{
			try(
					
				ObjectInputStream inputEmployee = new ObjectInputStream
						(new FileInputStream("D:/Important/EXE/"
								+ "PersonMessage/txt/Employee.dat"));
			){
				employee = (Employee[])(inputEmployee.readObject());
			}
		}
		
		public void readFacultyFile() throws Exception{
			try(
				ObjectInputStream inputFaculty = new ObjectInputStream
						(new FileInputStream("D:/Important/EXE/"
								+ "PersonMessage/txt/Faculty.dat"));
			)
			{
					faculty = (Faculty[])(inputFaculty.readObject());
			}
		}
		
		public void readStaffFile() throws Exception{
			try(
				ObjectInputStream inputStaff = new ObjectInputStream
						(new FileInputStream("D:/Important/EXE/"
								+ "PersonMessage/txt/Staff.dat"));
			)
			{
				staff = (Staff[])(inputStaff.readObject());
			}
		}
		
		public void readStudentFile() throws Exception{
			try(
				ObjectInputStream inputStudent = new ObjectInputStream
						(new FileInputStream("D:/Important/EXE/"
								+ "PersonMessage/txt/Student.dat"));
			){
				student = (Student[])(inputStudent.readObject());
			}
			
		}
		
		public void readPostFile() throws Exception{
			try(
				ObjectInputStream inputPost = new ObjectInputStream
						(new FileInputStream("D:/Important/EXE/"
								+ "PersonMessage/txt/PostGraduate.dat"));
			){
				post = (PostGraduate[])(inputPost.readObject());
			}
		}
		
		public void readFile() throws Exception{
			//���ļ�
			if(whichButton == "Person"){
				readPersonFile();
				readEmployeeFile();
				readStaffFile();
				readFacultyFile();
				readStudentFile();
				readPostFile();
			}
			else if(whichButton == "Employee"){
				readEmployeeFile();
				readStaffFile();
				readFacultyFile();
			}
			else if(whichButton == "Faculty"){
				readFacultyFile();
			}
			else if(whichButton == "Staff"){
				readStaffFile();
			}
			else if(whichButton == "Student"){
				readStudentFile();
				readPostFile();
			}
			else if(whichButton == "PostGraduate"){
				readPostFile();
			}
		}
		
		public void personSetVeiw(boolean b){
			//JLabel���ɼ�
			jlbOffice.setVisible(b);
			jlbPay.setVisible(b);
			jlbMyDate.setVisible(b);
			jlbTime.setVisible(b);
			jlbLevel.setVisible(b);
			jlbWorkName.setVisible(b);
			jlbGrade.setVisible(b);
			jlbTeacher.setVisible(b);
			jlbTarget.setVisible(b);
			
			//JTextFiled���ɼ�
			jtfOffice.setVisible(b);
			jtfPay.setVisible(b);
			jtfMyDate.setVisible(b);
			jtfTime.setVisible(b);
			jtfLevel.setVisible(b);
			jtfWorkName.setVisible(b);
			jtfGrade.setVisible(b);
			jtfTeacher.setVisible(b);
			jtfTarget.setVisible(b);
		}
		
		public void employeeSetVeiw(boolean b){
			//JLabel���ɼ�
			jlbTime.setVisible(b);
			jlbLevel.setVisible(b);
			jlbWorkName.setVisible(b);
			jlbGrade.setVisible(b);
			jlbTeacher.setVisible(b);
			jlbTarget.setVisible(b);
			
			//JTextFiled���ɼ�
			jtfTime.setVisible(b);
			jtfLevel.setVisible(b);
			jtfWorkName.setVisible(b);
			jtfGrade.setVisible(b);
			jtfTeacher.setVisible(b);
			jtfTarget.setVisible(b);
		}
		
		public void facultySetVeiw(boolean b){
			//JLabel���ɼ�
			jlbWorkName.setVisible(b);
			jlbGrade.setVisible(b);
			jlbTeacher.setVisible(b);
			jlbTarget.setVisible(b);
			
			//JTextFiled���ɼ�
			jtfWorkName.setVisible(b);
			jtfGrade.setVisible(b);
			jtfTeacher.setVisible(b);
			jtfTarget.setVisible(b);
		}
		
		public void staffSetVeiw(boolean b){

			//JLabel���ɼ�
			jlbTime.setVisible(b);
			jlbLevel.setVisible(b);
			jlbGrade.setVisible(b);
			jlbTeacher.setVisible(b);
			jlbTarget.setVisible(b);
			
			//JTextFiled���ɼ�
			jtfTime.setVisible(b);
			jtfLevel.setVisible(b);
			jtfGrade.setVisible(b);
			jtfTeacher.setVisible(b);
			jtfTarget.setVisible(b);
		}
		
		public void studentSetVeiw(boolean b){
			//JLabel���ɼ�
			jlbOffice.setVisible(b);
			jlbPay.setVisible(b);
			jlbMyDate.setVisible(b);
			jlbTime.setVisible(b);
			jlbLevel.setVisible(b);
			jlbWorkName.setVisible(b);
			jlbTeacher.setVisible(b);
			jlbTarget.setVisible(b);
			
			//JTextFiled���ɼ�
			jtfOffice.setVisible(b);
			jtfPay.setVisible(b);
			jtfMyDate.setVisible(b);
			jtfTime.setVisible(b);
			jtfLevel.setVisible(b);
			jtfWorkName.setVisible(b);
			jtfTeacher.setVisible(b);
			jtfTarget.setVisible(b);
		}
		
		public void postSetVeiw(boolean b){
				//JLabel
				jlbOffice.setVisible(b);
				jlbPay.setVisible(b);
				jlbMyDate.setVisible(b);
				jlbTime.setVisible(b);
				jlbLevel.setVisible(b);
				jlbWorkName.setVisible(b);
				
				//JTextFiled
				jtfOffice.setVisible(b);
				jtfPay.setVisible(b);
				jtfMyDate.setVisible(b);
				jtfTime.setVisible(b);
				jtfLevel.setVisible(b);
				jtfWorkName.setVisible(b);
		}
		
		public void display(int n){							//����ҳ��
			
			jtfName.setText(p.get(n).getName());
			jtfAddress.setText(p.get(n).getAddress());
			jtfPhone.setText(p.get(n).getPhoneNumber());
			jtfEmail.setText(p.get(n).getEmail());
			
			personSetVeiw(true);
			
			if(p.get(n) instanceof PostGraduate){			//PostGraduate
			
				postSetVeiw(false);
				
				jtfTarget.setText(((PostGraduate)p.get(n)).getTarget());
				jtfTeacher.setText(((PostGraduate)p.get(n)).getTeacher());
				jtfGrade.setText(((PostGraduate)p.get(n)).getGrade());
				
			}
			
			else if(p.get(n) instanceof Student){			//Student
				
				studentSetVeiw(false);
				
				jtfGrade.setText(((Student)p.get(n)).getGrade());
			}
			
			else if(p.get(n) instanceof Faculty){			//Faculty
				
				facultySetVeiw(false);
				
				jtfMyDate.setText(((Faculty)p.get(n)).getDate().toString());
				
				jtfLevel.setText(((Faculty)p.get(n)).getLevel());
				jtfOffice.setText(((Faculty)p.get(n)).getOffice());
				jtfPay.setText(String.valueOf(((Faculty)p.get(n)).getPay()));
				jtfTime.setText(((Faculty)p.get(n)).getWorkTime());
			}
			
			else if(p.get(n) instanceof Staff){				//Staff
				
				staffSetVeiw(false);
				
				jtfMyDate.setText(((Staff)p.get(n)).getDate().toString());
				jtfTime.setText(((Staff)(p.get(n))).getDate().toString());
				jtfOffice.setText(((Staff)(p.get(n))).getOffice());
				jtfPay.setText(String.valueOf(((Staff)(p.get(n))).getPay()));
				jtfMyDate.setText(((Staff)(p.get(n))).getDate().toString());
				jtfWorkName.setText(((Staff)(p.get(n))).getWorkName());
			}
			
			else if(p.get(n) instanceof Employee){			//Employee
				
				employeeSetVeiw(false);
				
				jtfMyDate.setText(((Employee)p.get(n)).getDate().toString());
				jtfOffice.setText(((Employee)p.get(n)).getOffice());
				jtfPay.setText(String.valueOf
						(((Employee)(p.get(n))).getPay()));
			}
			
			else if(p.get(n) instanceof Person){			//Person
				
				personSetVeiw(false);
				
			}
		}
		
		private class jrbActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
					personSetVeiw(true);
				if(e.getSource() == jrbPerson){
					
					personSetVeiw(false);
				}
				else if(e.getSource() == jrbEmployee){

					employeeSetVeiw(false);
				}
				else if(e.getSource() == jrbFaculty){

					facultySetVeiw(false);
				}
				else if(e.getSource() == jrbStaff){

					staffSetVeiw(false);
				}
				else if(e.getSource() == jrbStudent){

					studentSetVeiw(false);
				}
				else if(e.getSource() == jrbPost){

					postSetVeiw(false);
				}
			}
		}
		
		private class jbtActionListener implements ActionListener{
			
			public void saveMessages(Person p){
				p.setName(jtfName.getText());
				p.setEmail(jtfEmail.getText());
				p.setAddress(jtfAddress.getText());
				p.setPhoneNumber(jtfPhone.getText());
				
				if(p instanceof PostGraduate){
					((PostGraduate)p).setTarget(jtfTarget.getText());
					((PostGraduate)p).setTeacher(jtfTeacher.getText());
				}
				else if(p instanceof Staff){
					((Staff)p).setOffice(jtfOffice.getText());
					
					try{
						((Staff)p).setPay
						(Double.parseDouble(jtfPay.getText()));

					}
					catch(NumberFormatException e1){
						jtfPay.setText("0");
						((Staff)p).setPay(0);
					}
					
					((Staff)p).setWorkName(jtfWorkName.getText());
				}
				else if(p instanceof Faculty){
					((Faculty)p).setLevel(jtfLevel.getText());
					((Faculty)p).setOffice(jtfOffice.getText());
					((Faculty)p).setWorkTime(jtfTime.getText());
					
					try{
						((Faculty)p).setPay
						(Double.parseDouble(jtfPay.getText()));

					}
					catch(NumberFormatException e1){
						jtfPay.setText("0");
						((Faculty)p).setPay(0);
					}
					
				}
				else if(p instanceof Employee){
					((Employee)p).setOffice(jtfOffice.getText());
					
					try{
						((Employee)p).setPay
						(Double.parseDouble(jtfPay.getText()));

					}
					catch(NumberFormatException e1){
						jtfPay.setText("0");
						((Employee)p).setPay(0);
					}
				}
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == jbtReturn){				//����
					f.remove(pMessage);
					f.repaint();
					f.add(pChoose);
					f.validate();
				}
				else if(e.getSource() == jbtAdd){			//����
					
					setEdit(true);
					jbtSA.setVisible(true);
					jrbGroupSetVeiw(true);
					

					jtfName.setText("����");				//Person
					jtfAddress.setText("��ַ");
					jtfPhone.setText("�绰");
					jtfEmail.setText("����");
					
					jtfOffice.setText("�칫��");				//Employee
					jtfPay.setText("����");
					
					jtfTime.setText("����ʱ��");				//Faculty
					jtfLevel.setText("�ȼ�");
					
					jtfWorkName.setText("��������");			//Staff
					
					jtfGrade.setText("�꼶");				//Student
					
					jtfTeacher.setText("ָ����ʦ");			//Post
					jtfTarget.setText("�о�����");
				}
				else if(e.getSource() == jbtSA){			//��������
					
					if(jtfName.getText().equals("")){
						jlbRemind.setText("������Ա��Ϣ��������Ϊ��");
					}
					else{
						
						jrbGroupSetVeiw(false);					//��ť���ɼ�
						setEdit(false);
						jbtSA.setVisible(false);
						
						if(jrbPerson.isSelected()){
							Person newPerson = new Person();
							
							saveMessages(newPerson);
							
							p.add(newPerson);
							PERSON.add(newPerson);
							jcName.addItem(newPerson.getName());
							
							//��������
							person = new Person[PERSON.size()];
							for(int i = 0;i < person.length;i++){
								person[i] = PERSON.get(i);
							}
							
							try {
								writePersonFile();
							} catch (ClassNotFoundException
									| IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(jrbEmployee.isSelected()){
							Employee newEmployee = new Employee();
							
							saveMessages(newEmployee);
							p.add(newEmployee);
							EMPLOYEE.add(newEmployee);
							jtfMyDate.setText(newEmployee.getDate()
									.toString());
							jcName.addItem(newEmployee.getName());

							//��������
							employee = new Employee[EMPLOYEE.size()];
							for(int i = 0;i < employee.length;i++){
								employee[i] = EMPLOYEE.get(i);
							}
							
							try {
								writeEmployeeFile();
							} catch (ClassNotFoundException
									| IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(jrbFaculty.isSelected()){
							Faculty newFaculty = new Faculty();
							
							saveMessages(newFaculty);
							
							p.add(newFaculty);
							FACULTY.add(newFaculty);
							jcName.addItem(newFaculty.getName());

							//��������
							faculty = new Faculty[FACULTY.size()];
							for(int i = 0;i < faculty.length;i++){
								faculty[i] = FACULTY.get(i);
							}
							
							try {
								writeFacultyFile();
							} catch (ClassNotFoundException
									| IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(jrbStaff.isSelected()){
							Staff newStaff = new Staff();

							saveMessages(newStaff);
							
							p.add(newStaff);
							STAFF.add(newStaff);
							
							//��������
							staff = new Staff[STAFF.size()];
							for(int i = 0;i < staff.length;i++){
								staff[i] = STAFF.get(i);
							}
							jcName.addItem(newStaff.getName());

							try {
								writeStaffFile();
							} catch (ClassNotFoundException
									| IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(jrbStudent.isSelected()){
							Student newStudent = new Student();

							saveMessages(newStudent);
							
							p.add(newStudent);
							STUDENT.add(newStudent);
							
							//��������
							student = new Student[STUDENT.size()];
							for(int i = 0;i < student.length;i++){
								student[i] = STUDENT.get(i);
							}

							jtfGrade.setText(newStudent.getGrade());
							jcName.addItem(newStudent.getName());

							try {
								writeStudentFile();
							} catch (ClassNotFoundException
									| IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(jrbPost.isSelected()){
							PostGraduate newPost = new PostGraduate();

							saveMessages(newPost);
							p.add(newPost);
							POST.add(newPost);
							jtfGrade.setText(newPost.getGrade());
							jcName.addItem(newPost.getName());

							post = new PostGraduate[POST.size()];
							for(int i = 0;i < post.length;i++){
								post[i] = POST.get(i);
							}

							try {
								writePostFile();
							} catch (ClassNotFoundException
									| IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
				}
				else if(e.getSource() == jbtDel){			//ɾ��
					int n = jcName.getSelectedIndex();
					
					if(p.get(n) instanceof PostGraduate){
						if(whichButton == "Person"){
							POST.remove(n - person.length - 2);
						}
						else if(whichButton == "Student"){
							POST.remove(n - person.length - 1);
						}
						else{
							POST.remove(n);
						}
						
						//�ж��Ƿ����ʣ�����
						post = new PostGraduate[POST.size()];
						for(int i = 0;i < post.length;i++){
							post[i] = POST.get(i);
						}
						try {
							writePostFile();
						} catch (ClassNotFoundException
								| IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(p.get(n) instanceof Student){	//Student
						if(whichButton == "Person"){
							STUDENT.remove(n - person.length - 1);
						}
						else{
							STUDENT.remove(n);
						}

						//�ж��Ƿ����ʣ�����
						student = new Student[STUDENT.size()];
						for(int i = 0;i < student.length;i++){
							student[i] = STUDENT.get(i);
						}
						try {
							writeStudentFile();
						} catch (ClassNotFoundException
								| IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(p.get(n) instanceof Staff){		//Staff
						if(whichButton == "Person"){
							STAFF.remove(n - person.length - 2);
						}
						else if(whichButton == "Employee"){
							STAFF.remove(n - person.length - 1);
						}
						else{
							STAFF.remove(n);
						}
						
						//�ж��Ƿ����ʣ�����
						staff = new Staff[STAFF.size()];
						for(int i = 0;i < staff.length;i++){
							staff[i] = STAFF.get(i);
						}
						
						try {
							writeStaffFile();
						} catch (ClassNotFoundException
								| IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(p.get(n) instanceof Faculty){		//Faculty
						if(whichButton == "Person"){
							FACULTY.remove(n - person.length - 2);
						}
						else if(whichButton == "Employee"){
							FACULTY.remove(n - person.length - 1);
						}
						else{
							FACULTY.remove(n);
						}
						
						//�ж��Ƿ����ʣ�����
						faculty = new Faculty[FACULTY.size()];
						for(int i = 0;i < faculty.length;i++){
							faculty[i] = FACULTY.get(i);
						}
						
						try {
							writeFacultyFile();
						} catch (ClassNotFoundException
								| IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(p.get(n) instanceof Employee){		//Employee
						if(whichButton == "Person"){
							EMPLOYEE.remove(n - person.length - 1);
						}
						else{
							EMPLOYEE.remove(n);
						}
						
						//�ж��Ƿ����ʣ�����
						employee = new Employee[EMPLOYEE.size()];
						for(int i = 0;i < employee.length;i++){
							employee[i] = EMPLOYEE.get(i);
						}
						
						try {
							writeEmployeeFile();
						} catch (ClassNotFoundException
								| IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(p.get(n) instanceof Person){		//Person
						PERSON.remove(n);
						
						//�ж��Ƿ����ʣ�����
						person = new Staff[PERSON.size()];
						for(int i = 0;i < person.length;i++){
							person[i] = PERSON.get(i);
						}
						
						try {
							writePersonFile();
						} catch (ClassNotFoundException
								| IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					p.remove(n);
					jcName.remove(n);
				}
				else if(e.getSource() == jbtChange){			//�޸�
					setEdit(true);
					jbtSave.setVisible(true);
				}
				else if(e.getSource() == jbtSave){				//����
					if(jtfName.getText().equals("")){
						
						jlbRemind.setText("�޸���Ա��Ϣ���ܽ�������Ϊ��");
					}
					else{
						
						setEdit(false);
						jbtSave.setVisible(false);
						
						int n = jcName.getSelectedIndex();
						saveMessages(p.get(n));
						
						jrbPerson.setVisible(false);		//���ð�ť���ɼ�
						jrbEmployee.setVisible(false);
						jrbFaculty.setVisible(false);
						jrbStaff.setVisible(false);
						jrbStudent.setVisible(false);
						jrbPost.setVisible(false);
						
						try {
							writeToFile();
						} catch (ClassNotFoundException
								| IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		}
		
		public void writePersonFile() throws FileNotFoundException,
				IOException, ClassNotFoundException{
			File f = new File("D:/Important/EXE/"
					+ "PersonMessage/txt/Person.dat");
			f.delete();
			try(
				ObjectOutputStream outputPerson = new ObjectOutputStream
						(new FileOutputStream(f));
			){
				outputPerson.writeObject(person);
			}
		}
		
		public void writeEmployeeFile() throws FileNotFoundException,
				IOException, ClassNotFoundException{
			File f = new File("D:/Important/EXE/"
					+ "PersonMessage/txt/Employee.dat");
			f.delete();
			try(
				ObjectOutputStream outputEmployee = new ObjectOutputStream
						(new FileOutputStream(f));
			){
				outputEmployee.writeObject(employee);
			}
		}
		
		public void writeFacultyFile() throws FileNotFoundException,
				IOException, ClassNotFoundException{
			File f = new File("D:/Important/EXE/"
					+ "PersonMessage/txt/Faculty.dat");
			f.delete();
			try(
				ObjectOutputStream outputFaculty = new ObjectOutputStream
						(new FileOutputStream(f));
			)
			{
				outputFaculty.writeObject(faculty);
			}
		}
		
		public void writeStaffFile() throws FileNotFoundException,
				IOException, ClassNotFoundException{
			File f = new File("D:/Important/EXE/"
					+ "PersonMessage/txt/Staff.dat");
			f.delete();
			try(
				ObjectOutputStream outputStaff = new ObjectOutputStream
						(new FileOutputStream(f));
			)
			{
				outputStaff.writeObject(staff);
				
			}
		}
		
		public void writeStudentFile() throws FileNotFoundException,
				IOException, ClassNotFoundException{
			File f = new File("D:/Important/EXE/"
					+ "PersonMessage/txt/Student.dat");
			f.delete();
			try(
				ObjectOutputStream outputStudent = new ObjectOutputStream
						(new FileOutputStream(f));
			){
				outputStudent.writeObject(student);
			}
			
		}
		
		public void writePostFile()throws FileNotFoundException,
				IOException, ClassNotFoundException{
			File f = new File("D:/Important/EXE/"
					+ "PersonMessage/txt/PostGraduate.dat");
			f.delete();
			try(
				ObjectOutputStream outputPost = new ObjectOutputStream
						(new FileOutputStream(f));
			){
				 outputPost.writeObject(post);
			}
		}
		
		public void writeToFile() throws FileNotFoundException,
				ClassNotFoundException, IOException{
			//д�ļ�
			int n = jcName.getSelectedIndex();
			
			if(p.get(n) instanceof PostGraduate){
				
				try {
					writePostFile();
				} catch (ClassNotFoundException
						| IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(p.get(n) instanceof Student){
				
				try {
					writeStudentFile();
				} catch (ClassNotFoundException
						| IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(p.get(n) instanceof Staff){
				
				try {
					writeStaffFile();
				} catch (ClassNotFoundException
						| IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(p.get(n) instanceof Faculty){
				
				try {
					writeFacultyFile();
				} catch (ClassNotFoundException
						| IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(p.get(n) instanceof Employee){
				
				try {
					writeEmployeeFile();
				} catch (ClassNotFoundException
						| IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(p.get(n) instanceof Person){
				
				try {
					writePersonFile();
				} catch (ClassNotFoundException
						| IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(new ImageIcon
				("D:/Important/EXE/PersonMessage/ͼ��/����.jpg").getImage(),
				0,0,null);
			//JLabel
			jlbName.setLocation(40,100);
			jlbAddress.setLocation(40,150);
			jlbPhone.setLocation(390,100);
			jlbEmail.setLocation(420,150);
			
			jlbOffice.setLocation(25,200);
			jlbPay.setLocation(40,250);
			jlbMyDate.setLocation(10,300);
			
			jlbTime.setLocation(10,350);
			jlbLevel.setLocation(10,400);
			
			jlbWorkName.setLocation(10,450);
			
			jlbGrade.setLocation(420,200);
			
			jlbTeacher.setLocation(390,250);
			jlbTarget.setLocation(390,300);
			
			//JTextFiled
			jtfName.setLocation(140,100);
			jtfAddress.setLocation(140,150);
			jtfPhone.setLocation(530,100);
			jtfEmail.setLocation(530,150);
			
			jtfOffice.setLocation(140,200);
			jtfPay.setLocation(140,250);
			jtfMyDate.setLocation(140,300);
			
			jtfTime.setLocation(140,350);
			jtfLevel.setLocation(140,400);
			
			jtfWorkName.setLocation(140,450);
			
			jtfGrade.setLocation(530,200);
			
			jtfTeacher.setLocation(530,250);
			jtfTarget.setLocation(530,300);
			
			jlbRemind.setLocation(0,535);
			
			jrbPerson.setLocation(450,350);
			jrbEmployee.setLocation(620,350);
			jrbFaculty.setLocation(630,400);
			jrbStaff.setLocation(650,450);
			jrbStudent.setLocation(450,400);
			jrbPost.setLocation(450,450);
		}
	}
	
	public static void main(String[] args){
		Test3 f = new Test3();
	}
}
/*�ڰ汾 2�Ļ��������ʵ��һ������ GUI�������Ա��Ϣ����ϵͳ
Ҫ��ʵ�ֻ�������Ա����ɾ���ġ���Ĺ���
��Ա��Ϣ�б�Ӧ���� JTable�����*/