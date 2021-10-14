
package com.rinit.gui.model.viewModel;

import java.util.List;
import java.util.Stack;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.event.IEventContext;

public class FilesListViewModel implements IEventContext{

	private int selectedIndex;
	private int maxSelectionIndex;
	
	private Stack<String> paths = new Stack<String>();

	public FilesListViewModel() {
		this.paths.push("/");
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

	
}
