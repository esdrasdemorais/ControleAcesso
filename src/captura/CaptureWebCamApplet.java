package captura;

/**
*
* @author Leandro
*/
import de.humatic.dsj.CaptureDeviceControls;
import de.humatic.dsj.DSCapture;
import de.humatic.dsj.DSFilterInfo;
import de.humatic.dsj.DSFiltergraph;
import de.humatic.dsj.DSGraph;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
* This program demonstrates the basics of creating a frame
* user interface with a menubar. It also shows how to
* add a menubar and dropdown menus to the applet, which wasn't
* possible in the basic AWT heavyweight component.
**/
public class CaptureWebCamApplet extends JApplet implements PropertyChangeListener {

	private DSGraph netGraph = null;
	private DSFilterInfo dSFilterInfo = null;
	private DSCapture graph = null;
	private JComboBox comCameras = new JComboBox();
	private JDesktopPane desPreview = new JDesktopPane();
	private JInternalFrame frame = new JInternalFrame();
	private List<DSFilterInfo> filterInfoList = null;
	private HashMap<String, DSFilterInfo> hashCams = new HashMap<String, DSFilterInfo>();
	/** Build an applet interface with a menubar. A
	* a drop down menu includes Open/Close items
	* for opening and closing an instance of ParticleFrame.
	**/
	
		@Override
		public void init() {
			 System.setSecurityManager(null);  
		} // init	   
    
        public void start() {  
              // Aqui você deve fazer as suas chamadas  
        	chargeCams();
    		comCameras.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				comCamerasActionPerformed(evt);
    			}
    		});	
    		add(java.awt.BorderLayout.NORTH, comCameras);
    		addDesktopPane();        
        }  
    
        public void stop() {  
        }  

	public void addDesktopPane() {
		desPreview = new JDesktopPane();
		desPreview.setBackground(new java.awt.Color(240, 240, 240));
		desPreview.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		this.add(desPreview);
		add(java.awt.BorderLayout.CENTER, desPreview);
	}
	
	public List<DSFilterInfo> getVideoDevices() {
		DSFilterInfo[][] lista = null;
		try {
			lista = DSCapture.queryDevices();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return Arrays.asList(lista[0]);
	}
	
	public void chargeCams() {
		try {
			filterInfoList = getVideoDevices();
			String[] camNames = new String[filterInfoList.size()];
			camNames[0] = "";
			
			for (int i = 0; i < filterInfoList.size(); i++) {
				if (!filterInfoList.get(i).getName().equalsIgnoreCase("none")) {
					camNames[i + 1] = filterInfoList.get(i).getName();
					hashCams.put(filterInfoList.get(i).getName(),
					filterInfoList.get(i));
				}
			}
			comCameras.setModel(
			new javax.swing.DefaultComboBoxModel(camNames));
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void comCamerasActionPerformed(java.awt.event.ActionEvent evt) {
		String selectedCam = comCameras.getSelectedItem().toString();
		if ((selectedCam != null) && (!selectedCam.trim().equals(""))) {
			for (JInternalFrame f : desPreview.getAllFrames()) {
					if (f.getTitle().equalsIgnoreCase(selectedCam)) {
						return;
					}
			}
	
			dSFilterInfo = hashCams.get(selectedCam);
			
			graph = new DSCapture(DSFiltergraph.RENDER_NATIVE, dSFilterInfo,
			false, DSFilterInfo.doNotRender(), this);
			
			frame.addInternalFrameListener(new InternalFrameAdapter() {
			
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					finalizeFrame();
				}	
			});	
			
			frame.setTitle(hashCams.get(selectedCam).getName());
			frame.setClosable(true);
			frame.setSize(150,205);  
			frame.setResizable(false);
			frame.add(java.awt.BorderLayout.CENTER, graph.asComponent());
			frame.setVisible(true);
			desPreview.add(frame);
		//	JPanel jf = new JPanel();
		//	jf.setLayout(new java.awt.GridLayout(0, 1));
			CaptureDeviceControls controls = ((DSCapture)
			graph).getActiveVideoDevice().getControls();
			
	/*		if (controls != null) {
				for (int i = CaptureDeviceControls.BRIGHTNESS; i <
					CaptureDeviceControls.FOCUS; i++) {
					
					try {
						jf.add(controls.getController(i, 0, true));
					} catch (Exception e) {
						System.out.println("Erro ao inserir controle: " +
						controls.getController(i, 0, true));
					}
				}
			}*/

	//		frame.add(java.awt.BorderLayout.WEST, jf);
	//		jf.setVisible(true);
			JButton jb = new JButton("Capturar");
			jb.addActionListener(new java.awt.event.ActionListener() {
		
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					Object image = graph.getImage();
					/*JFileChooser jf = new JFileChooser();
					jf.setMultiSelectionEnabled(false);
					jf.showSaveDialog(desPreview);
					if (jf.getSelectedFile() != null) {
						ImageIO.write((BufferedImage) image, "jpg",
						jf.getSelectedFile());
					}*/
					
					/* Temporário: Criar Atributo Estático numa classe final Config.java */
				//	String nomeFoto = "C:/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
					String nomeFoto = "//172.16.80.181/C$/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
				//	String nomeFoto = "<img src = "http://172.16.80.181:8090/ControleAcesso/ControleAcessoImagem/${funcionario.foto}-00.bmp" width="100" height = "120" />"
					System.out.println(nomeFoto);
					ImageIO.write((BufferedImage) image, "jpg", new File(nomeFoto));
		
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});					
		
			frame.add(java.awt.BorderLayout.SOUTH, jb);
		}
	}
	public void finalizeFrame() {
		frame.dispose();
		graph.dispose();
		graph = null;
		desPreview.remove(frame);
	}

	public void propertyChange(PropertyChangeEvent evt) {
	}
} // class FrameApplet
		
