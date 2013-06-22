import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class About extends JPanel {

	private static final long serialVersionUID = 1L;

	public About() {
		setLayout(null);
		
		JTextArea txtrBuProgram = new JTextArea();
		txtrBuProgram.setTabSize(4);
		txtrBuProgram.setText("\t\n\n\n\t# Bu program dili ögrenmek icin yapilan bir proje calismadir.\n\n\t# Mutlaka " +
				"karsilasacaginiz hatalar olacaktir.\n\n\t# Geri bildirim yaparsaniz uzerinde beraber calisip duzeltebiliriz." +
				"\n\t\n\t# Fikir ve onerilerinizi paylasabilirsiniz : selimssevgi@gmail.com\n\n\t# Proje ile ilgili " +
				"detaya selimssevgi.com adresinden ulasabilirsiniz.\n\n\t# Projenin kodlarini Github/SSSelim adresinde " +
				"bulabilirsiniz.");
		txtrBuProgram.setEditable(false);
		txtrBuProgram.setBackground(UIManager.getColor("Viewport.background"));
		txtrBuProgram.setBounds(12, 12, 476, 297);
		add(txtrBuProgram);

	}

}
