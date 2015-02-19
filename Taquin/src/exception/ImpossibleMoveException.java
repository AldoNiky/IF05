package exception;

public class ImpossibleMoveException extends Exception {
	private static final long serialVersionUID = 1L;

	public ImpossibleMoveException() {
		super("Mouvement non permis");
	}
}
