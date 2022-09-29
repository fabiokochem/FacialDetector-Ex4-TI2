package Project;
import java.io.File;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetection {
    private CascadeClassifier cascadeClassifier;
	
	public FaceDetection() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.cascadeClassifier = new CascadeClassifier(Constants.CASCADE_CLASSIFIER);
	}
	
	public void DetectaFace(File arq, PainelImagem imagem) {
		Mat img = Imgcodecs.imread(arq.getAbsolutePath(), Imgcodecs.IMREAD_COLOR);
		
		MatOfRect faceDetecta = new MatOfRect();
		cascadeClassifier.detectMultiScale(img, faceDetecta, 1.2, 3, 10, new Size(20,20), new Size(500,500));
		
		for(Rect rect : faceDetecta.toArray()) {
			Imgproc.rectangle(img, new org.opencv.core.Point(rect.x,rect.y), new org.opencv.core.Point(rect.x+rect.width,rect.y+rect.height), new Scalar(100,100,250), 10);
		}
		
		BufferedImage bi = convertMatToImage(img);
		imagem.updateImage(bi);
	}

	private BufferedImage convertMatToImage(Mat matrix) {
		
		int tipo = BufferedImage.TYPE_BYTE_GRAY;
		
		if(matrix.channels() > 1) {
			tipo = BufferedImage.TYPE_3BYTE_BGR;
		}
		
		int TamanhoBuffer = matrix.channels() * matrix.cols() * matrix.rows();
		byte[] bytes = new byte[TamanhoBuffer];
		matrix.get(0, 0, bytes);
		BufferedImage img = new BufferedImage(matrix.cols(), matrix.rows(), tipo);
		final byte[] pixelsAlvo = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		System.arraycopy(bytes, 0, pixelsAlvo, 0, bytes.length);
		
		return img;
	}
}
