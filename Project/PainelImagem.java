package Project;
import java.io.File;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelImagem extends JPanel {
    private static final long serialVersionUID = 1L;
	private JLabel imageLabel;
	private ImageIcon transformedImageIcon;
	
	public PainelImagem() {
		this.imageLabel = new JLabel();
	
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(Constants.BORDA_IMAGEM, Constants.BORDA_IMAGEM, Constants.BORDA_IMAGEM, Constants.BORDA_IMAGEM));
		
		add(imageLabel, BorderLayout.CENTER);
	}
	
	public void updateImage(final Image img) {
		imageLabel.setIcon(new ImageIcon(scaleImage(img)));
	}
	
	private Image scaleImage(Image img) {
		return img.getScaledInstance(700, 500, img.SCALE_SMOOTH);
	}
	
	public void loadImage(File arq) {
		this.transformedImageIcon = new ImageIcon(arq.getAbsolutePath());
		Image img = transformedImageIcon.getImage();
		
		updateImage(img);
	}
}
