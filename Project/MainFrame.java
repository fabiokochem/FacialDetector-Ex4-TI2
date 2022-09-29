package Project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
	private PainelImagem painelImagem;
	private JFileChooser fileChooser;
	private FaceDetection faceDetection;
	private File arq;
	
	public MainFrame() {
		super(Constants.NOME_DE_APLICACAO);
		
		setJMenuBar(CriarMenuBar());
		
		this.painelImagem = new PainelImagem();
		this.fileChooser = new JFileChooser();
		this.faceDetection = new FaceDetection();
		
		add(painelImagem, BorderLayout.CENTER);
		
		setSize(Constants.TAMANHO_FRAME, Constants.ALTURA_FRAME);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
	}
	
	public JMenuBar CriarMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu ArquivoMenu = new JMenu("Imagem");
		JMenuItem CarregarItemMenu = new JMenuItem("Carregar imagem");
		JMenuItem DetectarFaceMenu = new JMenuItem("Detectar faces");
		JMenuItem SairMenu = new JMenuItem("Sair");
		ArquivoMenu.add(CarregarItemMenu);
		
		CarregarItemMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					MainFrame.this.arq = fileChooser.getSelectedFile();
					System.out.println(MainFrame.this.arq);
					MainFrame.this.painelImagem.loadImage(MainFrame.this.arq);;
				}	
			}
		});
		
		DetectarFaceMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.faceDetection.DetectaFace(MainFrame.this.arq, MainFrame.this.painelImagem);
			}
		});
		
		JMenu sobreMenu = new JMenu("Sobre");
		JMenu AjudaMenu = new JMenu("Ajuda");
		
		menuBar.add(ArquivoMenu);
		menuBar.add(sobreMenu);
		menuBar.add(AjudaMenu);
		
		SairMenu.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				int acao = JOptionPane.showConfirmDialog(MainFrame.this, Constants.AVISO_SAIDA);
			
				if(acao == JOptionPane.OK_OPTION) {
					System.gc();
					System.exit(0);
				}
			}
		});
		
		return menuBar;
	}
}
