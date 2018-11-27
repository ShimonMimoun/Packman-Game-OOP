package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Layer implements GIS_layer {
    Set<GIS_element> element_List = new HashSet<GIS_element>(); 
	MetaData mt = new MetaData();
	
	
	@Override
	public boolean add(GIS_element e) {	
		return element_List.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		return element_List.addAll(c);
	}

	@Override
	public void clear() {
		element_List.clear();
	}

	@Override
	public boolean contains(Object o) {
		return element_List.contains(o);

	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return element_List.containsAll(c) ;
	}

	@Override
	public boolean isEmpty() {
		return element_List.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {

		return element_List.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return element_List.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return element_List.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return element_List.retainAll(c);
	}

	@Override
	public int size() {
		return element_List.size();
	}

	@Override
	public Object[] toArray() {
		return element_List.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return element_List.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {
		return  this.mt;
	}
	
	@Override
	public String toString() {
		return element_List.toString();
	}
	
	
}
