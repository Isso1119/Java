import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Panel extends JButton {
	public boolean Bomb ;//trueのとき爆弾
	public boolean clicked=false;
	public Panel() {
		super();
	}
	public Panel(ImageIcon icon) {
		super(icon);
	}
}

