package unb.userIterface;
import java.util.Scanner;
import java.util.Locale;

import unb.banco.cliente.Cliente;
import unb.banco.conta.Conta;
import unb.banco.conta.ContaBonificada;

public class UserInterface
{
	private static Scanner sc = new Scanner(System.in);
	private static Menu mainMenu;
	
	private static void init(){
		sc.useLocale(Locale.US);
		mainMenu = Menu.obterInstancia();	
	}
	
	public static void main(String[] args)
		{
			init();
			int option;
			while(true)
				{
					mainMenu.cleanScreen();
					do{
						display(mainMenu);
						display("\n\nFavor escolher uma opção: ");
						option = getOption();
						mainMenu.operation(option);
					}while(option != mainMenu.exit());
				}//end loop while
			
		}//end function main

 	static	public void display(Menu mainMenu)
		{
			for(int i = 1; i <= mainMenu.getNoptions(); i++)
				{
					System.out.println(i + "- " + mainMenu.option(i));
					
				}//end for
		}//end function display
 	
 	public static void report(String message){
 		System.out.println(message);
 	}

 	public static void ask(String message){
 		System.out.println(message);
 	}
 	
	public static void display(String message){
 	 	System.out.println(message);
 	}

	public static int getOption(int start, int end, int defaultOp){
		int option = sc.nextInt();
		sc.nextLine();
		
		if(option >= start && option <= end)
			return option;
		else
			return defaultOp;
 	}
	
	public static int getOption() {
		int option = sc.nextInt();
		//pula o ENTER digitado
		//usar isso toda vez que pegar dado com next
		sc.nextLine();

		return option;
	}
	
	static public String getName(String type){
		 display("Entre com o nome do " + type +"(a):");
		 String target = sc.nextLine();
		 return target;
	 }

	 static public String getName(String type, String Complemento){
		 display("Entre com o nome do " + type +"(a) "+ Complemento + " :");
		 String target = sc.nextLine();
		 return target;
	 }

	 static public String getCpf(String type){
			 display("Entre com o Cpf do " + type +"(a):");
			 String target = sc.nextLine();
			 return target;
		 }

	static public String getCpf(String type, String Complemento){
			 display("Entre com o CPF do " + type +"(a) "+ Complemento + " :");
			 String target = sc.nextLine();
			 return target;
		 }
	 
	 static public String getPhone(){
		 display("Entre com o telefone");
		 String target = sc.nextLine();
		 return target;
	 }

	 static public String getCNumber(){
		 display("Entre com o número da conta");
		 String target = sc.nextLine();
		 return target;
	 }
	 
	static public String getCNumber(String Complemento){
		 display("Entre com número da conta " + Complemento + " :");
		 String target = sc.nextLine();
		 return target;
	 }

	 
	 static public String getAdress(){
		 display("Entre com o endereço do aluno(a)");
		 String target = sc.nextLine();
		 return target;
	 }
	 
	 static public int getAge(){
		 display("Entre com a idade do aluno(a)");
		 int target = sc.nextInt();
		 sc.nextLine();
		 return target;
	 }

	 static public double getValue(String complemento){
		 display("Entre com o valor a "+ complemento);
		 double target = sc.nextDouble();
		 sc.nextLine();
		 return target;
	 }

	 
	 static public String getSNOption(){
		 String target = sc.nextLine();
		 
		 if(target.equalsIgnoreCase("s") || target.equalsIgnoreCase("sim") ||
				 target.equalsIgnoreCase("y") || target.equalsIgnoreCase("yes"))
		 	return "sim";
		 else
			 return "não";
	 }
	 
	 static public void print(Cliente target, int option){
		 //option controla o número de parâmtros a imprimir
		 // na ordem: nome, cpf
		 switch(option)
		 	{
		 	case 1:
		 		display("Nome:" + target.getName() );
		 		break;
		 	case 2:
		 	default:
		 		display("Nome:" + target.getName() );
		 		display("cpf:" + target.getCpf());
		 		break;
		 	}
		 }
	 static public void print(Conta target, int option){
		 //option controla o número de parâmtros a imprimir
		 switch(option)
		 	{
		 	case 1:
		 		display("Número:" + target.getNumber());
		 		break;
		 	case 2:
		 		display("Número:" + target.getNumber());
		 		display("dono:" + target.getDono().getName());
		 		break;
		 	case 3:	
		 		display("Número:" + target.getNumber());
		 		display("dono:" + target.getDono().getName());
		 		display("saldo:" + target.getSaldo());
		 		if(target instanceof ContaBonificada)
		 			display("bônus:" + ((ContaBonificada)target).getBonus());
		 		break;
		 	case 4:	
		 	default:
		 		display("Número:" + target.getNumber());
		 		display("dono:" + target.getDono().getName());
		 		display("cpf do dono:" + target.getDono().getCpf());
		 		display("saldo:" + target.getSaldo());
		 		break;
		 	}
		 }
	 
	 public static void waitEnter()
		{
			display("ENTER para continuar");
				sc.nextLine();	
		}	



}//end class UserInterface