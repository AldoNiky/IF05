package taquin;

import java.util.*;

public class comparerList implements Comparator<String> {

	@Override
	public int compare(String arg0, String arg1) {
		return arg0.length()-arg1.length();
	}
	
}
