package com.ces.enigma.pluginsConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreatePluginsConfig {
	private String path;

	public CreatePluginsConfig(String path) {
		this.path = path;
	}

	@SuppressWarnings("unchecked")
	public void print() {
		List list = getFileList(path);
		if (list == null) {
			return;
		}

		int length = list.size();
		for (int i = 0; i < length; i++) {
			String result = "";
			String thePath = getFormatPath(getString(list.get(i)));
			File file = new File(thePath);
			if (file.isDirectory()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0) {
					continue;
				}
				String[] filenames = fileName.split("_");
				String filename1 = filenames[0];
				String filename2 = filenames[1];
				result = filename1 + "," + filename2 + ",file:/" + path + "\\"
						+ fileName + "\\,4,false";
				System.out.println(result);
			} else if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.indexOf("_") < 0) {
					continue;
				}
				String[] filenames = fileName.split("_");
				String filename1 = filenames[0];
				String filename2 = filenames[1].substring(0, filenames[1]
						.lastIndexOf("."));
				result = filename1 + "," + filename2 + ",file:/" + path + "\\"
						+ fileName + ",4,false";
				System.out.println(result);
			}

		}
	}

	@SuppressWarnings("unchecked")
	public List getFileList(String path) {
		path = getFormatPath(path);
		path = path + "/";
		File filePath = new File(path);
		if (!filePath.isDirectory()) {
			return null;
		}
		String[] filelist = filePath.list();
		List filelistFilter = new ArrayList();

		for (int i = 0; i < filelist.length; i++) {
			String tempfilename = getFormatPath(path + filelist[i]);
			filelistFilter.add(tempfilename);
		}
		return filelistFilter;
	}

	public String getString(Object object) {
		if (object == null) {
			return "";
		}
		return String.valueOf(object);
	}

	public String getFormatPath(String path) {
		path = path.replaceAll("\\\\", "/");
		path = path.replaceAll("//", "/");
		return path;
	}

	public static void main(String[] args) {
		new CreatePluginsConfig(
				"G:\\ApplyTools\\Genuitec\\MyEclipse-8.6\\dropins\\egit\\plugins")
				.print();
	}
}
