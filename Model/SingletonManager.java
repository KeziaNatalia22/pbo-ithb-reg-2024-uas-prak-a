package Model;


public class SingletonManager {
    private static SingletonManager instance;
    private Customer user;
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    SingletonManager(){

    }

    public static SingletonManager getInstance() {
        if (instance == null) {
            instance = new SingletonManager();
        }
        return instance;
    }

    public static void removeInstance (){
        instance = null;
    }
    public static void setInstance(SingletonManager instance) {
        SingletonManager.instance = instance;
    }
    public Customer getUser() {
        return user;
    }
    public void setUser(Customer user) {
        this.user = user;
    }
    
}
