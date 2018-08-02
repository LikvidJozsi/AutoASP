package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.management.InstanceAlreadyExistsException;

import Model.KontirSor;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class KontirSorManager {
	private static final String saveFilePath = "kontirSorok.ser";
	private ArrayList<KontirSor> kontirSorok;
	private static final KontirSorManager instance = new KontirSorManager();
	
	private KontirSorManager() {
		File saveFile = new File(saveFilePath);
		if(saveFile.isFile()) {
			try {
		         ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveFile));
		         kontirSorok = (ArrayList<KontirSor>) in.readObject();
		         in.close();
		      } catch (Exception e) {
		    	  initEmptyArray();
		      }
		}else {
			initEmptyArray();
		}
		
	}
	
	private void initEmptyArray() {
		kontirSorok = new ArrayList<>();
	}
	
	public static KontirSorManager getInstance() {
		return instance;
	}
	
	
	public ArrayList<KontirSor> getKontirSorok(){
		return new ArrayList<KontirSor>(kontirSorok);
	}
	
	
	public void notifyContentChanged(){
		try {
	         ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFilePath));
	         out.writeObject(kontirSorok);
	         out.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public void add(KontirSor kontirSor) {
		kontirSorok.add(kontirSor);
		notifyContentChanged();
	}
	
}
