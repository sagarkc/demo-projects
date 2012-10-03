package net.sf.tools4csv.app;

public class Tools4csv {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tools4CsvFrame().setVisible(true);
            }
        });
	}

}
