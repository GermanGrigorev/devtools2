package company;

import java.io.IOException;
// import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
	FileIni fileINI = new FileIni();
		try {
			fileINI.setInformation("C:\\maven2222\\2\\input.ini");
		} catch (IOException e) {
			return;
		}
		
		System.out.print(fileINI.toString());
    }
}
