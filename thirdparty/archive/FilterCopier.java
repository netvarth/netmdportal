

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class aids the build process by copying over the template files to the files (filename minus the
 * <code>.template</code> extension), and at the same time substitue the tokens from the build-time config. file.
 * <p/>
 * The build-time config is expected to be the first argument, and the copy path is expected to be the second argument.
 * 
 * 
 */
public class FilterCopier {
	private static final String TEMPLATE_EXT = ".template";
	private static final String COMMENT = "#";
	private static final String TOKEN_START = "${";
	private static final String TOKEN_END = "}";
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private String configFile;
	private String path;

	public FilterCopier(String configFile, String path) {
		this.configFile = configFile;
		this.path = path;
	}

	private void doCopy() throws IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(configFile));

		File searchPath = new File(path);
		if (!searchPath.isDirectory()) {
			System.err.println(path + " is not a valid path!");
			return;
		}

		List templateFiles = getTemplateFiles(searchPath);
		if (templateFiles == null || templateFiles.isEmpty()) {
			System.out.println("No template files found in " + path);
			return;
		}

		for (int i = 0; i < templateFiles.size(); i++) {
			File templateFile = (File) templateFiles.get(i);
			String templateFullPath = templateFile.getAbsolutePath();
			String newFileName = templateFullPath.substring(0, templateFullPath.indexOf(TEMPLATE_EXT));
			writeTemplateTo(templateFile, newFileName, prop);
		}
	}

	private List<File> getTemplateFiles(File searchPath) {
		File[] fileObjects = searchPath.listFiles();
		List<File> templateList = new ArrayList<File>();

		for (int i = 0; i < fileObjects.length; i++) {
			File fileObject = fileObjects[i];
			if (fileObject.isFile() && fileObject.getName().endsWith(TEMPLATE_EXT)) {
				templateList.add(fileObject);
				continue;
			}

			if (fileObject.isDirectory()) {
				templateList.addAll(getTemplateFiles(fileObject));
				continue;
			}
		}

		return templateList;
	}

	private void writeTemplateTo(File templateFile, String newFileName, Properties prop) throws IOException {
		BufferedReader templateReader = null;
		FileWriter newFileWriter = null;
		try {
			templateReader = new BufferedReader(new FileReader(templateFile));
			newFileWriter = new FileWriter(newFileName);

			String oneLine = templateReader.readLine();
			while (oneLine != null) {
				newFileWriter.write(substitute(oneLine, prop) + LINE_SEPARATOR);
				oneLine = templateReader.readLine();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (templateReader != null) { try { templateReader.close(); } catch (IOException e) {} }
			if (newFileWriter != null) { try { newFileWriter.close(); } catch (IOException e) {} }
		}
	}

	private String substitute(String text, Properties prop) {
		if (text.startsWith(COMMENT)) { return text; }

		int startIndex = text.indexOf(TOKEN_START);
		if (startIndex == -1) { return text; }

		int endIndex = text.indexOf(TOKEN_END, startIndex);
		if (endIndex == -1) { return text; }

		String token = text.substring(startIndex + TOKEN_START.length(), endIndex);

		String value = prop.getProperty(token);
		if (value == null) { return text.substring(0, startIndex) + text.substring(endIndex + TOKEN_END.length()); }

		return text.substring(0, startIndex) + value + text.substring(endIndex + TOKEN_END.length());
	}

	public static void main(String[] args) {
		if (sanityCheck(args)) {
			try {
				FilterCopier copier = new FilterCopier(args[0], args[1]);
				copier.doCopy();
			} catch (IOException e) {
				System.err.println("operation failed!");
				e.printStackTrace(System.err);
			}
		}
	}

	private static boolean sanityCheck(String[] args) {
		if (args == null || args.length != 2) {
			System.err.println("Expected two arguments:");
			System.err.println("1) the full path of the substitution file,");
			System.err.println("2) the full path to perform the copy-over operation.");
			System.err.println("Exiting...");
			return false;
		} else {
			return true;
		}
	}

}
