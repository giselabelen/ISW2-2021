package dataflow.abs;

import java.util.HashMap;
import java.util.Map;

public class ZeroAbstractSet {
	private final HashMap<String, ZeroAbstractValue> map;

	@Override
  	public boolean equals(Object o) {
    	if (this == o) return true;
    	if (!(o instanceof ZeroAbstractSet)) return false;
    	ZeroAbstractSet that = (ZeroAbstractSet) o;
    	return map.equals(that.map);
 	}

	public ZeroAbstractSet(){
		this.map = new HashMap<String, ZeroAbstractValue>();
	}

	public Boolean hasValue(String variable) {
		return this.map.containsKey(variable);
	}

	public ZeroAbstractValue getValue(String variable) {		
		return this.map.get(variable);
	}

	public void setValue(String variable, ZeroAbstractValue value) {
		
		System.out.println (variable);
		System.out.println (value);
		if (value != null) {
			this.map.put(variable, value);
		}
	}

	public ZeroAbstractSet union(ZeroAbstractSet another) {		

		ZeroAbstractSet output = new ZeroAbstractSet();
		ZeroAbstractValue mergeValue;

		output.putAll(another); // copio el contenido de another
        for (String currentKey : this.map.keySet()) { // itero cada clave en mi map

			if (output.hasValue(currentKey)){ // si la clave esta en output, mergeo
				mergeValue = output.getValue(currentKey).merge(this.getValue(currentKey));
				output.setValue(currentKey,mergeValue);
			}else{	// si no esta, la agrego
				output.setValue(currentKey,this.getValue(currentKey));
			}
        }
		return output;
	}

	public void clear() {
		this.map.clear();
	}

	public void putAll(ZeroAbstractSet another) {
		this.map.putAll(another.map);
	}
}
