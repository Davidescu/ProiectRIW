import java.awt.List;
import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import org.jsoup.Jsoup;



public class Parsare {
	private static final String FILENAME = "F:\\Anul4\\New folder\\output.txt";
	private static final String FILENAME1 = "F:\\Anul4\\New folder\\outputHref.txt";
	private static final String FILENAME2 = "F:\\Anul4\\New folder\\outputP2.txt";
	private static final String FILENAME3 = "F:\\Anul4\\New folder\\outputParseDoc.txt";
	public static Map<String, Integer> map = new HashMap<String,Integer>();
	Set<String> keySet=map.keySet();
	public static Scanner in;
	
	
	public static void ParseTitle()
	{
		File input;
		Document doc;
		
		input = new File("file.html");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			doc = Jsoup.parse(input, "UTF-8");
			String title=doc.title();
			bw.write("Titlul este: "+title);
			bw.newLine();
			System.out.println("Done title");
			doc = Jsoup.parse(input, "UTF-8");
			doc = Jsoup.parse(input, "UTF-8");
			String description =doc.select("meta[name=description]").get(0).attr("content");
			String keywords = doc.select("meta[name=keywords]").first().attr("content");

	
			bw.write("Meta Description: "+description);
			bw.newLine();
			bw.write("Meta Keyword: "+keywords);
			bw.newLine();
			
			System.out.println("Done Meta");

		} catch (IOException e) {

			e.printStackTrace();

		}


		
		//Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
		
	}
	
	public static void ParseMeta() 
	{
		File input;
		Document doc;
		input = new File("file.html");
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			doc = Jsoup.parse(input, "UTF-8");
			doc = Jsoup.parse(input, "UTF-8");
			String description =doc.select("meta[name=description]").get(0).attr("content");
			String keywords = doc.select("meta[name=keywords]").first().attr("content");

	
			bw.write("Meta Description: "+description);
			//bw.newLine();
			bw.write("Meta Keyword: "+keywords);
			//bw.newLine();
			
			System.out.println("Done Meta");

		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}
	
	public static void ParseHref() 
	{
		File input;
		Document doc;
		input=new File("file.html");
		/*try {
			doc = Jsoup.parse(input, "UTF-8");
			Elements selections = doc.select("a[href]");
		    for (Element element : selections) {
		        System.out.println( "HREF:"+element.attr("href"));
		        
		    }
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		*/
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME1))) {
			doc = Jsoup.parse(input, "UTF-8");
			Elements selections = doc.select("a[href]");
		    for (Element element : selections) {
		        bw.write("HREF:"+element.attr("href"));
		        bw.newLine();
		        
		    }
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void ParseDoc(File input)
	{
		//File input;
		Document doc;
		//input=new File("file.html");
		 try (BufferedWriter bw = new BufferedWriter(new FileWriter(input))) {
			 doc = Jsoup.parse(input, "UTF-8");
			 Element link = doc.select("a").first();         
		    bw.write("Doc: " + doc.body().text());
		 
		   
		    //Textsplit(input);


		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	
	public static void TextP(File file) 
	{
		try {
			in=new Scanner(file);
			while(in.hasNextLine())
			{
				String line=in.nextLine();
				String str=line.toLowerCase();
				StringBuilder parts=new StringBuilder();
				for(int i=0;i<str.length();++i)
				{
					if(Character.isLetter(str.charAt(i)))
					{
						parts.append(str.charAt(i));
					}
					else
					{
						if(parts.length()>2)
						{
							if(!map.containsKey(parts.toString())) {
								map.put(parts.toString(), 1);
							}
							else
							{
								map.put(parts.toString(), map.get(parts.toString())+1);
							}
							
						}
						parts.setLength(0);
					}
				}
			}
			System.out.println(map);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static boolean CheckIfTxt(File file)
	{
		boolean exist=false;
		if(file==null)
		{
			return false;
		}
		String name=file.getName();
		int i=name.lastIndexOf('.');
		String ext=i>0? name.substring(i+1) :" ";
		if(ext.equals("txt"))
		{
			exist=true;
		}
		else
			exist=false;
		return exist;
	}
	//LABORATOR 2
	public static Queue<File> getFile(File path)
	{
		
		
		 Queue<File>fisiere=new LinkedList<File>();
		 Queue<File> directory=new LinkedList<>();
		 directory.add(path);
		 
		 while(!directory.isEmpty()) {
			 File current=directory.poll();
			 File[] fileDirList=current.listFiles();
	
			 if(fileDirList!=null)
			 {
				 for(File file:fileDirList)
				 {
					 if(file.isDirectory())
					 {
						 directory.add(file);
						
						 
					 }
					 else if(CheckIfTxt(file))
					 {
						
						 fisiere.add(file);
						
					 }
				 }
			 }
			
		 }
		 return fisiere;
		 
		
	}
	
	public static boolean CheckIfException(String words)
	{
		String[] exceptii= {"it", "been", "but","are","below"};
		for(String i:exceptii)
		{
			if(words.equals(i))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean StopWords(String words) 
	{
		
		
		ArrayList<String> listaCuvinte=new ArrayList<String>();
	    try {
	    	FileReader in = new FileReader("stopwords.txt");
		    BufferedReader br = new BufferedReader(in);
	    	  String line = br.readLine();
	  	    while (line!=null) {
	  	       // System.out.println(line);
	  	    	listaCuvinte.add(line);
	  	        line = br.readLine();
	  	    }
	    }catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
	   
		   for( String i : listaCuvinte)
		    {
			   if(CheckIfException(i))
			   {
				   return true;
			   }
		    
		    	if(words.equals(i))
		    	{
		    		return false;
		    	}
		    	
		    }
	   
	   
	   
	    in.close();
		return true;
	}
	
	public static Map<String, Integer> Textsplit(File filePath) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		FileInputStream inputStream = null;
		Porter porter=new Porter();
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(filePath);
			sc = new Scanner(inputStream, "UTF-8");
			StringBuilder word = new StringBuilder();

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				for (int i = 0; i < line.length(); ++i) {
					if (Character.isLetter(line.charAt(i))) {
						word.append(line.charAt(i));

					} else {
						if (((word.length() > 2))&&(StopWords(word.toString()))) {
							if (!map.containsKey(porter.stripAffixes(word.toString()))) {
								map.put(porter.stripAffixes(word.toString()), 1);
							} else {
								map.put(porter.stripAffixes(word.toString()), map.get(porter.stripAffixes(word.toString())) + 1);
							}
						}
						word.setLength(0);
					}

					if (i == (line.length() - 1) && word.length() > 0) {
						if (!map.containsKey(word.toString())) {
							map.put(porter.stripAffixes(word.toString()), 1);
						} else {
							map.put(porter.stripAffixes(word.toString()), map.get(porter.stripAffixes(word.toString())) + 1);
						}
						word.setLength(0);
					}
				}
			}
			//System.out.println(map);
			inputStream.close();
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	//LABORATOR 3
	public static Map<String, Map<String,Integer>> inverseMap = new HashMap<>();
	public static MongoSetup mongoClient=new MongoSetup();
	 

	
	
		public static void AddIndexFiles(File folder) throws IOException
		{
			

			 HashSet<String>hash_set=new HashSet<String>();
			 Queue<File> fisiere=new LinkedList<File>();
			 FileWriter out;
			 FileWriter allFileMap=new FileWriter("allFileMap.txt");
			 FileWriter indexInvers=new FileWriter("inversIndex.txt");
			 fisiere=getFile(folder);
			 
			 Map<String, Map<String,Integer>> directMap = new HashMap<>();
			
			
			
			 mongoClient.setDatabaseName("RIW");
			 
			// MongoClient mongoClient = new MongoClient();
			// DB database = mongoClient.getDB("RIW");
			 DBCollection collection1 = mongoClient.getCollection("directIndex");
			 collection1.drop();
			 
				for(File f: fisiere)
				{
					String dir=f.getParent();
					File path=new File(dir+"\\index.html");
					if(hash_set.contains(dir)==true)
					{
						out=new FileWriter(path,true);
					}
					else
					{
						out=new FileWriter(path);
						hash_set.add(dir);
					}
					HashMap<String,Integer>printHash=(HashMap<String, Integer>)Textsplit(f);
					
					directMap.put(f.getAbsolutePath(), printHash);
					
					
					
					
				//MONGODB directIndex
					
					
					java.util.List<BasicDBObject> temps = new ArrayList<>();
					for(Entry<String, Integer> entry:printHash.entrySet()) {
						
						  
						  temps.add(new BasicDBObject("t",entry.getKey()).append("c", entry.getValue()));
					
					
						
					}
					 DBObject directIndex = new BasicDBObject("docs",f.getAbsolutePath())
	                            .append("temps",temps);
	                            
						 collection1.insert(directIndex);
					
					
					
					out.write(directMap.toString());
					out.write(System.lineSeparator());
					allFileMap.write(f.getAbsolutePath()+",");
					allFileMap.write(path.getAbsolutePath());
					allFileMap.write(System.lineSeparator());
					out.close();
					
					
					for(String key1: directMap.keySet())
					{
						for(String key2:directMap.get(key1).keySet())
						{
							int count=directMap.get(key1).get(key2);
							if(inverseMap.containsKey(key2))
								{
									inverseMap.get(key2).put(key1, count);
								}
								else
								{
									 HashMap<String, Integer>x=new HashMap<>();
									x.put(key1, count);
									inverseMap.put(key2, x);
									
								}
						}
					
						
					}
					directMap.clear();
					
					
					
				}
			
				
				
				//java.util.List<BasicDBObject> docs = new ArrayList<>();
				for(Entry<String, Map<String, Integer>> entry:inverseMap.entrySet()) {
					
					indexInvers.write((entry.getKey()+" = "+entry.getValue()));
					indexInvers.write(System.lineSeparator());	
					
				
					
					
					
				}
				
				DBCollection collection = mongoClient.getCollection("inversIndex");
				
				collection.drop();
				
				for(Entry<String, Map<String, Integer>> entry:inverseMap.entrySet()) {
					//Invers_Index(entry.getValue(),entry.getKey());
					
					
					java.util.List<BasicDBObject> inversIndex = new ArrayList<BasicDBObject>();
					 for(Entry<String, Integer> entry1 : entry.getValue().entrySet())
					    {
						 inversIndex.add(new BasicDBObject("d",entry1.getKey()).append("c", entry1.getValue()));
					    }
					
					    DBObject invers = new BasicDBObject("term",entry.getKey()).append("docs", inversIndex);
					    collection.insert(invers);
				}
				
			
			
			
				indexInvers.close();
				allFileMap.close();
			
		
	}
		
		//Cautarea in Mongo
	/*	public static void find()
		{
			mongoClient.setDatabaseName("RIW");
			BasicDBObject whereQuery = new BasicDBObject();
			
			System.out.println("introduceti 1 cuv:");
			Scanner sc1=new Scanner(System.in);
			String cuv1=sc1.nextLine();
			
			System.out.println("introduceti 2 cuv:");
			Scanner sc2=new Scanner(System.in);
			String cuv2=sc2.nextLine();
			
			whereQuery.put("$or", new BasicDBObject("term",cuv1).append("term", cuv2));
			DBCollection collection=mongoClient.getCollection("inversIndex");
			DBCursor cursor = collection.find("$or", new BasicDBObject("term",cuv1).append("term", cuv2));
			while(cursor.hasNext()) {
			    System.out.println(cursor.next());
			}
		}*/
		
	
		//LABORATOR 4 Cautarea booleana
		public static boolean verifyIfTermenExists(String values)
		{
			
	
			//ArrayList<String> listaCuvinte=new ArrayList<String>();
		
			
			for(Entry<String, Map<String, Integer>> entry:inverseMap.entrySet()) {
				if(values.equals(entry.getKey()))
				{
					return true;
				}
			
			}
	
			return false;
		}
		
		public static LinkedList<String> ORoperator(String cuv1,String cuv2)
		{
			LinkedList<String> documentsList=new LinkedList();
			LinkedList<String> documentsList2=new LinkedList();
		
			
			
				if((cuv1 !=null)&&(cuv2!=null))
				{
					if(verifyIfTermenExists(cuv1)&&verifyIfTermenExists(cuv2))
					{
						
							for(String key1 : inverseMap.keySet())
							{
								
								if(key1.equals(cuv1))
								{
									
									documentsList.add(inverseMap.get(key1).keySet().toString());
									
									
								}
							}

							for(String key2 : inverseMap.keySet())
							{
								if(key2.equals(cuv2))
								{
									
							
											
											documentsList.add(inverseMap.get(key2).keySet().toString());
											
										
								}
									
									
								
							}
							
					}
							
				}
				//System.out.println(documentsList);
				//documentsList.stream().distinct().collect(Collectors.toList());
			
			
				for(String i : documentsList)
				{
					if(!documentsList2.contains(i))
					{
						System.out.println("fsfs");
						documentsList2.add(i);
					}
				}
				
		
				System.out.println(documentsList2.toString());
				return documentsList2;
				
				
				
				//System.out.println(documentsList);
				//return documentsList;
				
				
		}
		@SuppressWarnings("unlikely-arg-type")
		public static ArrayList<String> ANDoperator(String cuv1,String cuv2)
		{
			ArrayList<String> documentsList=new ArrayList<String>();
			ArrayList<String> documentsList1=new ArrayList<String>();
			ArrayList<String> documentsList2=new ArrayList<String>();
			
				if(cuv1 !=null)
				{
					if(verifyIfTermenExists(cuv1))
					{
						
							for(String key1 : inverseMap.keySet())
							{
								if(key1.equals(cuv1))
								{
									documentsList.add(inverseMap.get(key1).keySet().toString());
								}
							}
						
					}
							
				}
				if(cuv2!=null)
				{
					if(verifyIfTermenExists(cuv2))
					{
						
			
						for(String key1 : inverseMap.keySet())
						{
							if(key1.equals(cuv2))
							{
									
									documentsList.add(inverseMap.get(key1).keySet().toString());
								
							}
								
						}
					}
				}
				for (int i = 0; i < documentsList.size(); i++) {
				   System.out.println(documentsList.get(i).toString());
				}
				return documentsList;
			
				
				//System.out.println(documentsList);
			//	return documentsList1;
				
				
		}
		
		
	
		
			
		
		
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("text1.txt");
		File  file1=new File("F:\\Anul4\\New folder\\Lab1RIW");
		File p = new File("F:\\\\Anul4\\\\New folder\\\\Lab1RIW\\\\Dir1");
		ParseTitle();
		//ParseMeta();
		ParseHref();
		//ParseDoc();
		
		System.out.println("Problema 2:");
		
		//TextP(file);
		
		System.out.println("Laborator2:");
		//printFiles();
		//Textsplit(file);
		Queue<File> fisiere=new LinkedList<File>();
		fisiere=getFile(p);
		for(File f : fisiere)
			
		{
			System.out.println(f);
			Textsplit(f);

		}
		
		
		System.out.println("Laborator 3:");
		AddIndexFiles(p);
		
		/*System.out.println("Laborator 4:");
		System.out.println("introduceti 1 cuv:");
		Scanner sc1=new Scanner(System.in);
		String cuv1=sc1.nextLine();
		System.out.println("introduceti al 2-lea cuv:");
		/*Scanner sc2=new Scanner(System.in);
		String cuv2=sc2.nextLine();
		if((verifyIfTermenExists(cuv1)&&verifyIfTermenExists(cuv2))==true)
		{
			System.out.println("Cuvantul se afla in fisier");
		}
		else
		{
			System.out.println("Cuv nu se afla in fisier");
		}
		System.out.println("Reuniune:");
		LinkedList<String>docList=new LinkedList<>();
		docList=ORoperator(cuv1, cuv2);
		System.out.println(docList);*/
		
		/*System.out.println("Intersectie:");
		docList=ANDoperator(cuv1, cuv2);
		System.out.println(docList);
		*/
		//find();
		
	}

}
