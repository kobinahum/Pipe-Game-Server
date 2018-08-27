package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Algo.Action;
import Algo.Solution;

public class MyCacheManager implements CacheManager {
	
	private HashMap<Integer, Solution> mySolves;
	
	public MyCacheManager() {
		mySolves=new HashMap<Integer,Solution>();
		//final File folder = new File("C:/newFolder");
		
	}
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}
	public Solution searchLevel(Integer level) {
		if(mySolves.get(level)!=null)
			return mySolves.get(level);
		else {
			File f=new File("C:/newFolder/"+level.toString()+".txt");
			FileReader fr;
			try {
				fr = new FileReader(f);
				BufferedReader reader =new BufferedReader(fr);
				String str;
				List<Action> list=new LinkedList<Action>();
				Solution sol=new Solution(list);
				while((str=reader.readLine())!=null){
					list.add(new Action(str));
				}
				sol=new Solution(list);
				fr.close();
				reader.close();
				return sol;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return null;
			}
			
		}
	}
	public boolean saveALevel(Integer newLevel,Solution s) {
		if(!mySolves.containsKey(newLevel)) {
			mySolves.put(newLevel, s);
		File f1=new File("C:/newFolder/"+newLevel.toString()+".txt");
		try {
			FileOutputStream fos=new FileOutputStream(f1);
			try {
				OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF8");
				BufferedWriter writer=new BufferedWriter(osw);
				for(int i=0;i<s.getSolution().size();i++) {
					Action a=s.getSolution().get(i);
					String str=new String(a.toString());
					writer.write(str);
					writer.newLine();
					writer.flush();}
				osw.close();
				writer.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return false;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
			return true;
		}
		return false;
	}

	public HashMap<Integer, Solution> getMySolves() {
		return mySolves;
	}

	

	
	
	
}
