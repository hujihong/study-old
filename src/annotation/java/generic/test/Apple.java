package generic.test;

public class Apple<T>{

  private T info;

  public Apple(T info) {
    this.info = info;
  }
  
  public T getInfo(){
    return this.info;
  }
  
  public void setInfo(T info){
    this.info = info;
  }
  
  public static void main(String[] args) {
    Apple<String>ap1 = new Apple<String>("小苹果");
    System.out.println(ap1.getInfo());
    Apple<Double>ap2 = new Apple<Double>(1.23);
    System.out.println(ap2.getInfo());
  }
  

}
