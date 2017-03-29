package Observer;

public interface Observable {
	

    public void addObserver(Observer o);
    /**
     * Unregister an observer.
     * @param o the observer
     */
    public void removeObserver(Observer o);
    
    
    public void notifyObservers();
} 