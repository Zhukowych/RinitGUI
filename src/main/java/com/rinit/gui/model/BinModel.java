package com.rinit.gui.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rinit.debugger.server.client.interfaces.ILibraryServiceClient;
import com.rinit.debugger.server.file.library.LibraryClassNotFoundException;
import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.debugger.server.file.library.LibraryNotFoundException;
import com.rinit.debugger.server.services.interfaces.IBinService;
import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.binlist.BinListBin;
import com.rinit.gui.clibin.copy.CopyCliBin;
import com.rinit.gui.clibin.edfile.EditFileCliBin;
import com.rinit.gui.clibin.mkdir.MkDirBin;
import com.rinit.gui.clibin.newfile.NewFileBin;
import com.rinit.gui.clibin.read.ReadBin;
import com.rinit.gui.clibin.renmove.RenMoveCliBin;
import com.rinit.gui.clibin.upload.UploadBin;
import com.rinit.gui.dev.core.DevCliBins;
import com.rinit.gui.event.IEventHandler;

public class BinModel extends AbstractModel{
	
	private ModelFacade modelFacade;
	private TabsModel tabsModel;
	private Map<String, Class<? extends AbstractCliBin>> bins = new HashMap<String, Class<? extends AbstractCliBin>>();
	
	public BinModel(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler);
		this.modelFacade = modelFacade;
		this.tabsModel = this.modelFacade.getTabsModel();
		this.addDefaultBin();
	}

	public void execute(String command) {
		AbstractCliBin bin = this.getBin(this.getBinName(command), this.getParameters(command));
		if (bin.visible())
			this.tabsModel.addTab(bin);
		else
			bin.run();
	}

	public Map<String, Class<? extends AbstractCliBin>> getBins() {
		return this.bins;
	}
	
	private AbstractCliBin getBin(String binName, String[] params) {
		Class<? extends AbstractCliBin> bin = this.bins.get(binName);
		System.out.println(bin);
		try {
			Constructor<? extends AbstractCliBin> cons  = bin.getConstructor(String[].class, ModelFacade.class);
			Object params_ = (Object)params;
			Object modelFacade = (Object)this.modelFacade;
			Object fd = cons.newInstance(params_, modelFacade);
			return (AbstractCliBin) fd;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void addDefaultBin() {
		DevCliBins devCliBins = new DevCliBins();
		this.bins.putAll(devCliBins.getDevBins());
		this.bins.putAll(getRemoteBins());
		this.bins.put(UploadBin.NAME, UploadBin.class);
		this.bins.put(MkDirBin.NAME, MkDirBin.class);
		this.bins.put(ReadBin.NAME, ReadBin.class);
		this.bins.put(NewFileBin.NAME, NewFileBin.class);
		this.bins.put(EditFileCliBin.NAME, EditFileCliBin.class);
		this.bins.put(CopyCliBin.NAME, CopyCliBin.class);
		this.bins.put(RenMoveCliBin.NAME, RenMoveCliBin.class);
		this.bins.put(BinListBin.NAME, BinListBin.class);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Class<? extends AbstractCliBin>> getRemoteBins(){
		Map<String, Class<? extends AbstractCliBin>> remoteBins = new HashMap<String, Class<? extends AbstractCliBin>>();
		IBinService binServiceClient = this.modelFacade.getRinitClientModel().getClient().getBinService();
		ILibraryServiceClient libraryServiceClient = this.modelFacade.getRinitClientModel().getClient().getLibraryServiceClient();
		List<String> binNames = binServiceClient.getAwailableBinsNames();
		for(String binName : binNames) {
			LibraryDriver binLibrary = null;
			try {
				binLibrary = libraryServiceClient.convertRemoteLibraryToLocal(libraryServiceClient.getLibraryByPathName("/lib/bin/", binName));
				binLibrary.loadClasses();
			} catch (Exception e) {e.printStackTrace();}
			Class<? extends AbstractCliBin> cliBinClass = null;
			if (binLibrary != null) {
				try {
					cliBinClass = (Class<? extends AbstractCliBin>) binLibrary.getClassWithName(binName);
				} catch (LibraryClassNotFoundException e) {e.printStackTrace();}
				remoteBins.put(this.createInstance(cliBinClass).getName(), cliBinClass);
			}
		}
		return remoteBins;
	}
	
	public void reloadRemoveBins() {
		this.modelFacade.getRinitClientModel().getClient().getLibraryServiceClient().autodiscover();
		this.modelFacade.getRinitClientModel().getClient().getBinService().autodiscoverBins();
		
		this.bins.putAll(this.getRemoteBins());
	}
	
	public AbstractCliBin createInstance(Class<? extends AbstractCliBin> binClass) {
		try {
			return binClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getBinName(String command) {
		return command.split("\\s+")[0];
	}
	
	private String[] getParameters(String command) {
		String[] parts = command.split("\\s+");
		return Arrays.copyOfRange(parts, 1, parts.length);
	}
}
