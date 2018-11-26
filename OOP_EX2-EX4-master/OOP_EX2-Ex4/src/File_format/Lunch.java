package File_format;

import java.io.File;
import java.io.IOException;

public class Lunch {
	

	public static void main(String[] args) throws IOException {
		File file = CsvFileHelper.getResource("OOP_EX2-Ex4/data/WigleWifi_20171203085618.csv");
		csv2kml.to_KML(csv2kml.readFile(file), "OOP_EX2-Ex4/data/Omer2.kml");

		
	}

}