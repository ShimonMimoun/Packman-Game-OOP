package File_format;

import java.io.File;
import java.io.IOException;

public class Lunch {

	public static void main(String[] args) throws IOException {
		File file = CsvFileHelper.getResource("src/WigleWifi.csv");
		csv2kml.to_KML(csv2kml.readFile(file), "ouput.kml");
		
	}

}
