package rw.itcg.usecase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logging {
	public static void logs(String errorMsg, String classN) throws IOException {
		File file = new File(
				"D:/FAB/AUCA/JAVA/New Project Eclipse/Vehicles Monitoring System/WebContent/logs/logFile.txt");
		try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
			pw.println(new Date() + " : " + classN + " : " + errorMsg);
			pw.flush();
		}
	}
}
