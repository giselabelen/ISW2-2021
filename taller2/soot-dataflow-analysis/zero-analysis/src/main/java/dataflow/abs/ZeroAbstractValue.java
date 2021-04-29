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

    if (this == ZERO && another == ZERO){ // cero + cero da cero
      return ZERO;
    }else if (this == ZERO && another == NOT_ZERO){ // cero + no_cero da no_cero
      return NOT_ZERO;
    }else if (this == NOT_ZERO && another == ZERO){ // no_cero + cero da no_cero
      return NOT_ZERO;
    }else if (this == BOTTOM || another == BOTTOM){ // algun sumando indef da indef
      return BOTTOM;
    }else{ // los demas casos dan maybe_cero
      return MAYBE_ZERO;
    }
  }
  

  public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
    
    if (this == BOTTOM || another == BOTTOM){ // algun operando undef da undef
      return BOTTOM;
    }else if (another == ZERO){ // divisor cero da undef (caso dividendo undef se considera arriba)
      return BOTTOM
    }else if (this == ZERO){ // dividendo cero da cero (caso divisor cero se considera arriba)
      return ZERO;
    }else{ // los demas casos dan maybe_cero (combinaciones de MZ y NZ)
      return MAYBE_ZERO;
    }
  }
  

  public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
    
    if (this == BOTTOM || another == BOTTOM){ // algun factor undef da undef
      return BOTTOM;
    }else if (this == ZERO || another == ZERO){ // algun factor cero da cero
      return ZERO;
    }else if(this == NOT_ZERO && another == NOT_ZERO){ // ambos factores no_cero da no_cero
      return NOT_ZERO;
    }else{ // los demas casos dan maybe_cero
      return MAYBE_ZERO;
    }
  }
  

  public ZeroAbstractValue substract(ZeroAbstractValue another) {
    
    if (this == ZERO && another == ZERO){ // cero - cero da cero
      return ZERO;
    }else if (this == ZERO && another == NOT_ZERO){ // cero - no_cero da no_cero
      return NOT_ZERO;
    }else if (this == NOT_ZERO && another == ZERO){ // no_cero - cero da no_cero
      return NOT_ZERO;
    }else if (this == BOTTOM || another == BOTTOM){ // algun operando indef da indef
      return BOTTOM;
    }else{ // los demas casos dan maybe_cero
      return MAYBE_ZERO;
    }
  }
  

  public ZeroAbstractValue merge(ZeroAbstractValue another) {
    // operacion de merge de tuplas con la misma variable
    if (this == another){
      return another;
    }else if (this == ZERO && another == NOT_ZERO){ // tuplas cero y no_cero da maybe_cero
      return MAYBE_ZERO;
    }else if (this == NOT_ZERO && another == ZERO){ // tuplas no_cero y cero da maybe_cero
      return MAYBE_ZERO;
    }else if (this == MAYBE_ZERO && another != BOTTOM){ // tupla maybe_cero con otra cosa da maybe_cero
      return MAYBE_ZERO;
    }else if (this != BOTTOM && another == MAYBE_ZERO){ // tupla maybe_cero con otra cosa da maybe_cero
      return MAYBE_ZERO;
    }else if (this != BOTTOM && another == BOTTOM){ // FALTA VER QUE PASA CON BOTTOM
      return this;
    }else if (this == BOTTOM && another != BOTTOM){
      return another;
    }else{
      return BOTTOM;
    }
    
    //throw new UnsupportedOperationException();
  }
  
}
