public class ZeroAnalysisTest4 {
  public static int test4(int m, int n) {
    int x = 0;
    if (m != 0){
      x = m;
    }else{
      x = 1;
    }
    int j = n / x;
    return j;
  }
}
