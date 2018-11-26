package File_format;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MultiCSV {
	
	public  static void  listDirectory(String dir) throws IOException {

		String Outpout=dir;
		dir=CsvFileHelper.getResourcePath(dir);

		ArrayList <String> listeFichiers = new ArrayList<String>();

		String []s = new File(dir).list(); 
		Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+\\.csv"); 
		if (s != null) {

			for (int i=0; i<s.length;i++) 
			{ 
				Matcher m = p.matcher(s[i]); 
				if ( m.matches()) 
				{
					listeFichiers.add(s[i]); 
				} 
			} 

		}

//test
		CreateKml(listeFichiers,Outpout);
	}    

	public static void CreateKml(ArrayList<String> Repertoire,String Destination) throws IOException
	{

		for (int i = 0; i < Repertoire.size(); i++) {

			File file = CsvFileHelper.getResource(Destination+Repertoire.get(i));
			csv2kml.to_KML(csv2kml.readFile(file), Destination+Repertoire.get(i).substring(0, Repertoire.get(i).length()-4)+".kml"); 

		}
		System.out.println("Conversion Complete of Folder");

	}

}