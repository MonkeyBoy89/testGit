package home;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.omg.CORBA.Environment;

/**
 * 
 * @author MonkeyBoy
 *
 */
public class DNADescryptor 
{
	private static Map<String, String> mapKey = new HashMap<String, String>();
	private static Scanner sc = new Scanner(System.in);
	private static String EMPTY ="";
	
	static
	{
		mapKey.put("A", "T");
		mapKey.put("T", "A");
		mapKey.put("G", "C");
		mapKey.put("C", "G");
	}
	
	/**
	 * Point d'entrée
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String res = displayMenu();
		
		try 
		{
			run(res);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		finally 
		{
			System.out.println("Fin de l'exécution...");
		}
		
	}

	private static void run(String res) throws Exception, InterruptedException 
	{
		checkSequence(res);
		fakeWaiting();
		System.out.println(String.format("La séquence complémentaire est %s", res));
	}

	private static void fakeWaiting() throws InterruptedException 
	{
		System.out.println(EMPTY);
		System.out.println(EMPTY);
		System.out.println("Traitement en cours...");
		System.out.println(EMPTY);
		System.out.println(EMPTY);
		Thread.sleep(3000);
	}

	private static String displayMenu() 
	{
		System.out.println("===========DNA Decryptor 2000==========");
		System.out.println("===========******************==========");
		System.out.println("===========Developped by Gozean========");
		
		System.out.println(EMPTY);
		System.out.println(EMPTY);
		
		System.out.println("Veuillez entrez la sequence d'ADN à decrypter :");
		String res = decrypt(sc.next());
		return res;
	}
	
	/**
	 * Decrypt the dna
	 * @param seq
	 * @return
	 */
	private static String decrypt(String seq)
	{
		return seq.chars().mapToObj(c -> mapKey.get(String.valueOf((char)c))).collect(Collectors.joining());
	}
	
	
	private static void checkSequence(String seq) throws Exception
	{
		Pattern pattern = Pattern.compile("null");
		Matcher matcher = pattern.matcher(seq);
		
		if (matcher.find()) 
		{
			throw new Exception("La sequence n'est pas valide !");
		}
	}
	
	private static void doNothing()
	{
		//doNothing
	}
	
	/**
	 * The current date
	 * @return
	 */
	private static Date now()
	{
		return new Date();
	}

}
