
package com.rinit.gui.model.viewModel;


import java.util.List;
import java.util.Stack;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.dev.FilePanelConfig;
import com.rinit.gui.event.IEventContext;

public class FilesListViewModel implements IEventContext{

	private int selectedIndex;
	private int maxSelectionIndex;
	
	private Stack<String> paths = new Stack<String>();

	public FilesListViewModel() {
		String partsPath = "";
		String[] basePathParts = FilePanelConfig.BASE_PATH.split("/");
		for (String part : basePathParts) {
			partsPath = partsPath + part + "/";
			this.paths.push(partsPath);
		}
		System.out.println(this.paths);
	}
	
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	private List<FileDTO> files;

	
	public void setFiles(List<FileDTO> files) {
		this.files = files;
		this.maxSelectionIndex = this.files.size();
	}
	
	public List<FileDTO> getFiles() {
		return this.files;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	public FileDTO getSelectedFile() {
		return this.files.get(selectedIndex);
	}

	public void pushToPathStack(String path) {
		this.paths.add(path);
	}
	
	public String popFromPathStack() {
		return this.paths.pop();
	}
	
	public String peekFromPathStack() {
		return this.paths.peek();
	}

	public void increment() {
		if (this.selectedIndex != this.maxSelectionIndex - 1)
			this.selectedIndex++;
	}
	
	public void decrement() {
		if (this.selectedIndex != 0)
			this.selectedIndex--;
	}
	
	public int indexOf(FileDTO dto) {
		for (FileDTO file : this.files) {
			if (dto.getName().equals(file.getName()))
				return this.files.indexOf(file);
		}
		return -1;
	}
	
	public String getCurrentPath() {
		StringBuilder builder = new StringBuilder();
		for (String dir : this.paths) {
			builder.append(dir);
		}
		return builder.toString();
	}
	
}
