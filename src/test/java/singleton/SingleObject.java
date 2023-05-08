package singleton;

public class SingleObject {


    /*
    Singleton pattern is one of the simplest design patterns in Java. This type of design pattern comes
    under creation pattern as this pattern provides one of the best ways to create an object.
    This pattern involves a single class which is responsible to create an object while making sure that only single objects gets
    created. This class provides a way to access its only object which can be accessed directly without need to instantiate the object of the class.
   */

    private static SingleObject instance=new SingleObject();

    private SingleObject(){

    }

    public static SingleObject getInstance(){
        return  instance;
    }

    public void showMessage(){
        System.out.println("Hello world!");
    }
}
