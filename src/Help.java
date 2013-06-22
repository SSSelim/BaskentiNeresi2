import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;


public class Help extends JPanel {

	private static final long serialVersionUID = 1L;

	public Help() {
		setLayout(null);
		
		JTextArea txtryeniOyun = new JTextArea();
		txtryeniOyun.setBackground(UIManager.getColor("Viewport.background"));
		txtryeniOyun.setTabSize(4);
		txtryeniOyun.setEditable(false);
		txtryeniOyun.setLineWrap(true);
		txtryeniOyun.setText("\n\n\n\n\n# \"Yeni Oyun\" menusunden bir bolge secerek kendinizi" +
				" sinayabilirsiniz.\n\n# \"Puan Tablosu\"ndan skorlardan en yuksek 10 tanesini gorebilirsiniz." +
				"\n\n# \"Yardim\" menusunun \"Nasil Calisir\" kismindasiniz.\n\n# \"Yardim/Hakkinda\" kismindan" +
				" programla  ilgili detaylara bakabilirsiniz.");
		txtryeniOyun.setBounds(12, 12, 477, 300);
		add(txtryeniOyun);

	}
}
