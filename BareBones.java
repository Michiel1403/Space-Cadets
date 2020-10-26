import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class BareBones{
	Scanner sc;
	String str = "";
	ArrayList<String> variableNames = new ArrayList<String>();
	ArrayList<Integer> variableValues = new ArrayList<Integer>();

	public static void main(String[] args) throws FileNotFoundException{
		BareBones bb = new BareBones();
		bb.getTextFile("input.txt");
		bb.storeText();
		bb.compile();
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public void getTextFile(String pathway){
		try{
			this.sc = new Scanner(new File(pathway));
		}
		catch(FileNotFoundException fnfe){
			System.out.println("The required file does not exist!");
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public void storeText(){
		while(sc.hasNextLine()){
			str = str + sc.nextLine() + "\n";
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------

	
	public void compile(){
		String line = "";
		String command = "", newStr = "", tempLine;
		Integer pos = 0;
		boolean stop = false;
		while(pos < str.length()){
			tempLine = str.substring(pos, str.indexOf("\n", pos) + 1);
			newStr = newStr + tempLine.trim() + "\n";
			pos = str.indexOf("\n", pos) + 1;
		}
		str = newStr;
		while(str != null && str.indexOf("\n") > -1){
			line = str.substring(0, str.indexOf(";") + 1);
			if(line.indexOf(" ") < 0){
				command = line.substring(0,line.indexOf(";"));
			}
			else{
				command = line.substring(0,line.indexOf(" "));
			}
			String variable = "";
			switch(command){
				case "clear" :
					variable = findVariable(line, command);
					clear(variable);
					break;
				case "incr" :
					variable = findVariable(line, command);
					increase(variable);
					//increase(variable);
					break;
				case "decr" :
					variable = findVariable(line, command);
					decrease(variable);
					//decrease(variable);
					break;
				case "while" :
					str = str.substring(whileFunc(str));
					break;
				case "end" :
					break;
				default:
					System.out.println(command + " is not a function wihtin this programming language");
					System.exit(0);
					break;
			}
			str = str.substring(str.indexOf("\n") + 1);
		}
		System.out.println("Final variable Values:");
		for(Integer x = 0; x < variableNames.size(); x++){
			System.out.print(variableNames.get(x) + " = ");
			System.out.println(variableValues.get(x));
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------

	public String findVariable(String line, String command){
		String variable = line.substring(line.indexOf(command) + command.length() + 1, line.indexOf(";"));
		return variable;
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public void decrease(String variable){
		if(variableNames.contains(variable)){
			variableValues.set(variableNames.indexOf(variable), variableValues.get(variableNames.indexOf(variable)) - 1);
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public void increase(String variable){
		if(variableNames.contains(variable)){
			variableValues.set(variableNames.indexOf(variable), variableValues.get(variableNames.indexOf(variable)) + 1);
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public void clear(String variable){
		if(!variableNames.contains(variable)){
			variableNames.add(variable);
			variableValues.add(0);
		}
		else{
			variableValues.set(variableNames.indexOf(variable), 0);
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public Integer whileFunc(String block){
		Integer counter = 1, pos = block.indexOf(";") + 2;
		String command = "", line = "", newBlock;
		ArrayList<Integer> instr = new ArrayList<Integer>();
		ArrayList<String> vars = new ArrayList<String>();
		ArrayList<Integer> whilePos = new ArrayList<Integer>();
		Integer index = variableNames.indexOf(findWhileVar("while", block.substring(0, pos -1))), condition = findWhileNum(block.substring(0, pos -1));
		while(counter > 0){
			if(block.indexOf(";", pos) > -1){
				line = block.substring(pos, block.indexOf(";", pos) + 1);
				command = getCommand(line);
			}
			switch(command){
				case "incr" :
					instr.add(0);
					vars.add(findVariable(line,command));
					break;
				case "decr" :
					instr.add(1);
					vars.add(findVariable(line,command));
					break;
				case "clear" :
					instr.add(2);
					vars.add(findVariable(line,command));
					break;
				case "while" :
					counter ++;
					instr.add(3);
					vars.add("");
					whilePos.add(block.indexOf(command, pos));
					pos = block.indexOf("end", pos) - 2;
					break;
				case "end" :
					counter --;
					break;
			}
			if(pos != block.length()){
				pos = block.indexOf(";", pos) + 2;
			}
		}
		while(variableValues.get(index) != condition){
			counter =0;
			for(Integer x = 0; x < instr.size(); x++){
				switch(instr.get(x)){
					case 0 :
						increase(vars.get(x));
						break;
					case 1 :
						decrease(vars.get(x));
						break;
					case 2 :
						clear(vars.get(x));
						break;
					case 3 :
						newBlock = block.substring(whilePos.get(counter), block.indexOf("end;", whilePos.get(counter)) + 4);
						whileFunc(newBlock);
						counter ++;
						break;
				}
			}
		}
		return pos - 2;
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public String findWhileVar(String command, String line){
		Integer startPos = line.indexOf(command) + command.length() + 1;
		return line.substring(startPos, line.indexOf(" ", startPos));
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public Integer findWhileNum(String line){
		Integer startPos = line.indexOf("not") + 4;
		return Integer.parseInt(line.substring(startPos, line.indexOf(" ", startPos)));
	} 
	
	// -----------------------------------------------------------------------------------------------------------------------------------

	public String getCommand(String line){
		String command;
		if(line.indexOf(" ") < 0){
			command = line.substring(0,line.indexOf(";"));
		}
		else{
			command = line.substring(0,line.indexOf(" "));
		}
		return command;
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------

}