package JSMatching.JSpackage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MatchDriver {
	private static JSdata js[];
	private static VGdata vg[];
 private static int numJS;
	private static int numVG;
	static Scanner in;
	static Scanner fin;

	
	public static void main(String[] args) {
		
		js = new JSdata[300];
		vg = new VGdata[500];
		
		in = new Scanner(System.in);
		System.out.println("Welcome to Jobsite Matching");
		System.out.println("Please input the file for Jobsite data");
		File file = new File(in.nextLine());
		inputJS(file);
		System.out.println("Please input the file for Volunteer group data");		
		file = new File(in.nextLine());
		inputVG(file);
		match();
		
	}
	/*
	 * In put jobsite data
	 */
	public static void inputJS(File f){
		String input;
		String temp;
		int i;
		int tID;
		int tNumP;
		boolean tT;
		boolean done = false;
		//file = in.nextLine();
		while(!done){
			System.out.println("Now loading " + f);
			try{
				fin = new Scanner(f);
				done = true;
			}
			catch(Exception e){
				System.out.println("File " + f + " not found please try again");
				System.out.println("Please input the file for Jobsite data");
				File file = new File(in.nextLine());
				inputJS(file);
			}
		}
		i = 0;
		while(fin.hasNextLine()){
			input = fin.nextLine();
			System.out.println(input);
			i = 0;
			temp = "";
			while(input.charAt(i) != ','){
				temp = temp + input.charAt(i);
				i++;
			}
			i++;
			tID = Integer.parseInt(temp);			
			temp = "";
			while(input.charAt(i) != ','){
				temp = temp + input.charAt(i);
				i++;
			}
			tNumP = Integer.parseInt(temp);
			i++;
			if("1".equals(input.charAt(i))){
				tT = true;
			}else{
				tT = false;
			}
			js[numJS] = new JSdata(tID,tNumP,tT);
			numJS++;
		}
		viewJS();
		sortJS();
		viewJS();
		
	}
	/*
	 * Input volunteer groups 
	 */
	public static void inputVG(File f){
		String input;
		String temp;
		int i;
		int tID;
		int tNumP;
		boolean tT;
		boolean done = false;
		//file = in.nextLine();
		while(!done){
			System.out.println("Now loading " + f);
			try{
				fin = new Scanner(f);
				done = true;
			}
			catch(Exception e){
				System.out.println("File " + f + " not found please try again");
				System.out.println("Please input the file for Jobsite data");
				File file = new File(in.nextLine());
				inputVG(file);
			}
		}
		i = 0;
		while(fin.hasNextLine()){
			input = fin.nextLine();
			System.out.println(input);
			i = 0;
			temp = "";
			while(input.charAt(i) != ','){
				temp = temp + input.charAt(i);
				i++;
			}
			i++;
			tID = Integer.parseInt(temp);			
			temp = "";
			while(input.charAt(i) != ','){
				temp = temp + input.charAt(i);
				i++;
			}
			tNumP = Integer.parseInt(temp);
			i++;
			if("1".equals(input.charAt(i))){
				tT = true;
			}else{
				tT = false;
			}
			vg[numVG] = new VGdata(tID,tNumP,tT);
			numVG++;
		}
		viewVG();
		sortVG();
		viewVG();
	}
	
	public static void switchJS(int a, int b){
		JSdata tJS;
		tJS = new JSdata(js[a].getID(),js[a].getPep(),js[a].getTruck());
		js[a].setID(js[b].getID());
		js[a].setPep(js[b].getPep());
		js[a].setTruck(js[b].getTruck());
		js[b].setID(tJS.getID());
		js[b].setPep(tJS.getPep());
		js[b].setTruck(tJS.getTruck());
		
	}
	public static void sortJS(){
		for(int j = 0; j < numJS; j++){
			for(int i = 0; i < numJS; i++){
				if(js[i].getPep() < js[j].getPep()){
					switchJS(i,j);
				}
			}
		}
	}
	public static void viewJS(){
		for(int i = 0; i < numJS; i++){
			System.out.println(js[i].getID() + " needs " + js[i].getPep());
		}
	}
	public static void switchVG(int a, int b){
		VGdata tVG;
		tVG = new VGdata(vg[a].getID(),vg[a].getPep(),vg[a].getTruck());
		vg[a].setID(vg[b].getID());
		vg[a].setPep(vg[b].getPep());
		vg[a].setTruck(vg[b].getTruck());
		vg[b].setID(tVG.getID());
		vg[b].setPep(tVG.getPep());
		vg[b].setTruck(tVG.getTruck());
		
	}
	public static void sortVG(){
		for(int j = 0; j < numVG; j++){
			for(int i = 0; i < numVG; i++){
				if(vg[i].getPep() < vg[j].getPep()){
					switchVG(i,j);
				}
			}
		}
		System.out.println("Volunteer Groups are sorted");
	}
	public static void viewVG(){
		System.out.println();
		System.out.println("Viewing Volunteer Groups");
		for(int i = 0; i < numVG; i++){
			System.out.println(vg[i].getID() + " needs " + vg[i].getPep());
		}
	}
	public static void match(){
		int i = 0;
		try{
			PrintWriter writer = new PrintWriter("MatchedJobsites.txt", "UTF-8");
			writer.println("Matched Jobsites and Groups with Trucks");
			while(i < numJS){
				int j = 0;
				while(j < numVG && js[i].getMatched() == false){
					if(js[i].getPep() <= vg[j].getPep() && js[i].getPep()*1.25 >= vg[j].getPep()
							&& js[i].getTruck() == vg[j].getTruck() && vg[j].getMatched() == false){
						writer.println("Jobsite: " + js[i].getID() +" matched"
								+ " with group: " + vg[j].getID());
						writer.println("     Jobsite peps: " + js[i].getPep() +
								" group peps: " + vg[j].getPep());
						js[i].setMatched(true);
						vg[j].setMatched(true);
					}else if(js[i].getPep() == vg[j].getPep() && js[i].getTruck() == vg[j].getTruck()
							&& vg[j].getMatched() == false){
						writer.println("Jobsite: " + js[i].getID() +" matched"
								+ " with group: " + vg[j].getID());
						writer.println("     Jobsite peps: " + js[i].getPep() +
								" group peps: " + vg[j].getPep());
						js[i].setMatched(true);
						vg[j].setMatched(true);
					}
					j++;
				}
				i++;
			}
			i = 0;
			writer.println("\nMatched Jobsites disregarding Trucks");
			while(i < numJS){
				int j = 0;
				while(j < numVG && js[i].getMatched() == false){
					if(js[i].getPep() >= vg[j].getPep() && js[i].getPep()*1.2 <= vg[j].getPep()
							&& vg[j].getMatched() == false){
						writer.println("Jobsite: " + js[i].getID() +" matched"
								+ " with group: " + vg[j].getID());
						writer.println("     Jobsite peps: " + js[i].getPep() +
								" group peps: " + vg[j].getPep());
						js[i].setMatched(true);
						vg[j].setMatched(true);
					}else if(js[i].getPep() == vg[j].getPep()&& vg[j].getMatched() == false){
						writer.println("Jobsite: " + js[i].getID() +" matched"
								+ " with group: " + vg[j].getID());
						writer.println("     Jobsite peps: " + js[i].getPep() +
								" group peps: " + vg[j].getPep());
						js[i].setMatched(true);
						vg[j].setMatched(true);
					}
					j++;
				}
				i++;
			}
			int notMatched = 0;
			i = 0;
			writer.println("\n Not matched JS");
			while(i < numJS){
				if(js[i].getMatched() == false){
					writer.println("JS: " + js[i].getID() + "People: "+ js[i].getPep());
					notMatched++;
				}
				i++;
			}
			writer.println(notMatched + " jobsites were not matched");
			writer.println("\n Not matched Volunter Groups");
			notMatched = 0;
			i = 0;
			while(i < numVG){
				if(vg[i].getMatched() == false){
					writer.println("Group: " + vg[i].getID() + "People: "+ vg[i].getPep());
					notMatched++;
				}
				i++;
			}
			writer.println(notMatched + " volunteer groups were not matched");
			writer.println("Num JS: " + numJS + " Num VG: " + numVG);
			writer.close();
		}catch(Exception e){
			System.out.println("File not found, if seeing this "
					+ "code is wrong in public static void match()");
		}
		
	}
}

