# callisto
Cowboy Bebop meets Minecraft.

Coding convention mostly follows stuff on GeoSoft (google it):
2-space indent, no tabs.
```java
public class Test
{
  // constants are all capital with _
  public static final int SOME_CONSTANT = 24;
  public int test;
  
  // non-public instance fields should be prefixed with _
  protected int _test;
  private int _test;
  private int _quantity;
  
  // Braces should generally be on the next line
  public void someMethod()
  {
    if (this.isBad())
    {
      //...
    } else {
      //...
    }
    
    // Lambdas have brace on same line
    Thread thread = new Thread(() -> { 
      // etc
    });
    
    if (this.quantity() > 0)
      // one-liner should have no brace
  }
  
  // Getter
  public int quantity()
  {
    // all object fields and methods should start with "this."
    return this._quantity;
  }
  
  public boolean isBad()
  {
    return true;
  }
}
```
