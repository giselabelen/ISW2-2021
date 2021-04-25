public class ZeroAnalysisTest5 
{
   public static int test5(int y) {
    int x = y;
    y = 1;
    while (x!=1) {
      y=x*y;
      x=x-1;
    }
    return y; // IN(x)=MZ, IN(y)=MZ
  }
}
