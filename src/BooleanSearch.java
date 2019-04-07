import java.awt.List;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
public class BooleanSearch {
	public static ArrayList<String> NOTOperator(String[]cuv1,String[]cuv2, ArrayList<String> list)
	{
		ArrayList<String> documentsList=new ArrayList<String>();
		if(list.size()==0)
		{
			if(cuv1 !=null)
			{
				for(int i=0;i<cuv1.length;i++)
				{
					if(!cuv1[i].equals(""))
					{
						if(verifyIfTermenExists(documentsList, cuv1[i])==false)
						{
							documentsList.add(cuv1[i]);
						}
					}
				}
			}
			if(cuv2!=null)
			{
				for(int i=0;i<documentsList.size();i++)
				{
					for(int j=0;j<cuv2.length;j++)
					{
						if(documentsList.get(i).equals(cuv2[j]))
						{
							documentsList.remove(i);
							continue;
						}
					}
				}
			}
			return documentsList;
		}
		else
		{
			for(int i=0;i<list.size();i++)
			{
				for(int j=1;j<cuv2.length;j++)
				{
					if(list.get(i).equals(cuv2[j])) {
						list.remove(i);
					}
				}
			}
			return list;
		}
	}
	
	public static boolean verifyIfTermenExists(ArrayList<String>documentsList,String values)
	{
		for(int i=0;i<documentsList.size();i++)
		{
			if(documentsList.get(i).equals(values))
			{
				return true;
			}
		}
		return false;
	}
	public static void main(String[]args)
	{
		String terms=null;
		do {
			System.out.println("Introduceti interogarea:");
			InputStreamReader conv=new InputStreamReader(System.in);
			BufferedReader in=new BufferedReader(conv);
			try {
				terms = in.readLine();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
			}
			
		}while(!terms.contains(" ") && !terms.contains("AND")
				&& !terms.contains("OR") && !terms.contains("NOT"));
		
		String []split=terms.split(" ");
		ArrayList<String> listaDocCautare = new ArrayList<String>();
		ArrayList<String> listaDoc = new ArrayList<String>();
		for(int i=1;i<split.length-1;i=i+2)
		{
			if(split[i].equals("NOT"))
			{
				//listaDoc=NOTOperator("ana","daniel",listaDocCautare);
				//listaDocCautare=listaDoc;
				
			}
		}
	}
}
