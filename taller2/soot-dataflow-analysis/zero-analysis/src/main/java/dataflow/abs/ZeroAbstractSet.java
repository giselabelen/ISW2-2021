package dataflow.abs;

import java.util.HashMap;
import java.util.Map;

public class ZeroAbstractSet {
	private final HashMap<String, ZeroAbstractValue> map;

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
		if (value != null) {
			this.map.put(variable, value);
		}
	}

	public ZeroAbstractSet union(ZeroAbstractSet another) {
		
		// for (Map.Entry me : another.entrySet()) {	// itero cada elemento en another
		// 	if (this.hasValue(me.getKey())){ // si esta clave esta en mi map
		// 		mergeValue = this.getValue(me.getKey()).merge(me.getValue());
		// 		this.setValue(me.getKey(),mergeValue);
		// 	}else{	// si no esta, la agrego
		// 		this.setValue(me.getKey(),me.getValue());
		// 	}
  //       }		

		ZeroAbstractSet output = new ZeroAbstractSet();
		ZeroAbstractValue mergeValue;

		output.putAll(another);
        for (String currentKey : this.map.keySet()) {	// itero cada clave en mi map
        	//currentKey = me.getKey();

			if (output.hasValue(currentKey)){ // si esta clave esta en output
				mergeValue = output.getValue(currentKey).merge(this.getValue(currentKey));
				output.setValue(currentKey,mergeValue);
			}else{	// si no esta, la agrego
				output.setValue(currentKey,this.getValue(currentKey));
			}
        }
		//throw new UnsupportedOperationException();
		return output;
	}

	public void clear() {
		this.map.clear();
	}

	public void putAll(ZeroAbstractSet another) {
		this.map.putAll(another.map);
	}
}
