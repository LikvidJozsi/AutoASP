package Controller;

import java.util.LinkedList;
import java.util.List;

public class Alternatives{
	List<String> alternatives = new LinkedList<>();

	public Alternatives() {}

	public Alternatives(String alternative) {
		alternatives.add(alternative.toLowerCase());
	}

	public void add(String alternative) {
		alternatives.add(alternative);
	}
	public void addAll(String [] alternativeArray) {
		for (String alternative : alternativeArray) {
			alternatives.add(alternative.toLowerCase());
		}
	}
	public boolean presentIn(String text) {
		for (String alternative : alternatives) {
			if(text.contains(alternative)) {
				return true;
			}
		}
		return false;
	}
}
