package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * This class implements GIS_Layer functions.
 * @author Shimon Mimoun and Omer Paz
 *
 */


public class GIS_Layer_using extends HashSet<GIS_element> implements GIS_layer {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    Meta_Data_using mt = new Meta_Data_using();
    

	@Override
	public Meta_data get_Meta_data() {
		return  this.mt;
	}

	
}
