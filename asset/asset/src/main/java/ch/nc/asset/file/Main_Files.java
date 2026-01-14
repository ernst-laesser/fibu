package ch.nc.asset.file;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main_Files {

	public static void main(String[] args) {
		 Path path = Paths.get("C:\\Users\\ernst\\SecureSafe\\Privater Safe\\Finanzen\\Fibu_2025\\ZKB\\9-4700-00553564 Anlageportfolio\\Transaktionen"); // Oder "/pfad/zum/ordner"

	        try (Stream<Path> paths = Files.list(path)) {
	            System.out.println("Inhalt von: " + path.toAbsolutePath());
	            paths.forEach(p -> {
	                if (Files.isDirectory(p)) {
	                    System.out.println("  [Ordner] " + p.getFileName());
	                } else {
	                    System.out.println("  [Datei]  " + p.getFileName());
	                }
	            });
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}
