package dataflow.abs;

public enum ZeroAbstractValue {

  BOTTOM("bottom"), NOT_ZERO("not-zero"), ZERO("zero"), MAYBE_ZERO("maybe-zero");

  private String name;

  @Override
  public String toString() {
    return this.name;
  }

  ZeroAbstractValue(String name) {
    this.name = name;
  }

  public ZeroAbstractValue add(ZeroAbstractValue another) {

    if (this == ZERO && another == ZERO){
      return ZERO;
    }else if (this == ZERO && another == NOT_ZERO){
      return NOT_ZERO;
    }else if (this == NOT_ZERO && another == ZERO){
      return NOT_ZERO;
    }else if (this == BOTTOM || another == BOTTOM){
      return BOTTOM;
    }else{
      return MAYBE_ZERO;
    }
    //throw new UnsupportedOperationException();
  }
  

  public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
    
    if (this == ZERO && another == NOT_ZERO){
      return ZERO;
    }else if (this == NOT_ZERO && another == NOT_ZERO){
      return MAYBE_ZERO; // PORQUE ES DIVISION ENTERA
    }else if (this == MAYBE_ZERO && another == NOT_ZERO){
      return MAYBE_ZERO;
    }else{
      return BOTTOM;
    }
    //throw new UnsupportedOperationException();
  }
  

  public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
    
    if (this == BOTTOM || another == BOTTOM){
      return BOTTOM;
    }else if (this == ZERO || another == ZERO){
      return ZERO;
    }else if(this == NOT_ZERO && another == NOT_ZERO){
      return NOT_ZERO;
    }else{
      return MAYBE_ZERO;
    }
    //throw new UnsupportedOperationException();
  }
  

  public ZeroAbstractValue substract(ZeroAbstractValue another) {
    
    if (this == ZERO && another == ZERO){
      return ZERO;
    }else if (this == ZERO && another == NOT_ZERO){
      return NOT_ZERO;
    }else if (this == NOT_ZERO && another == ZERO){
      return NOT_ZERO;
    }else if (this == BOTTOM || another == BOTTOM){
      return BOTTOM;
    }else{
      return MAYBE_ZERO;
    }
    //throw new UnsupportedOperationException();
  }
  

  public ZeroAbstractValue merge(ZeroAbstractValue another) {
    // mergea valores, ej: uno es notzero y el otro es zero, da maybezero
    if (this == another){
      return another;
    }else if (this == ZERO && another == NOT_ZERO){
      return MAYBE_ZERO;
    }else if (this == NOT_ZERO && another == ZERO){
      return MAYBE_ZERO;
    }else if (this == MAYBE_ZERO && another != BOTTOM){
      return MAYBE_ZERO;
    }else if (this != BOTTOM && another == MAYBE_ZERO){
      return MAYBE_ZERO;
    }else{ // FALTA VER QUE PASA CON BOTTOM
      return BOTTOM;
    }
    
    //throw new UnsupportedOperationException();
  }
  
}
