package console;

public abstract class Command {

	public Command() {
	}
	
	public abstract String name();
	
	public abstract boolean validField(String args0);
	
	public abstract void action();
	
	public abstract boolean forceClose();

}
