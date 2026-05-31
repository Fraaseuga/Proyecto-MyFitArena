package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Funcionalidades.Colores;
import dao.ClubDAO;
import dao.UsuarioDAO;
import Funcionalidades.Usuario;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfCorreo;
    private JTextField tfTelefono;
    private JPasswordField pfContrasena;
    private JButton btnEliminar;
    private JButton btnConfirmar;

    public VentanaCuenta(){
    	//Metodo Closing
        addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			dispose();
    			VentanaPrincipal principal = new VentanaPrincipal();
    			principal.setVisible(true);
    		}
    	});
    	
    	//AJUSTES DE LA VENTANA
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDetallesClub.class.getResource("/Interfaces/logo.png")));
        setTitle("Datos de la Cuenta");
        setBounds(100, 100, 500, 450);
        setLocationRelativeTo(null);
        //PANEL PRINCIPAL
        contentPane = new JPanel();
        contentPane.setBackground(Colores.negro);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        //LABELS
        JLabel lblTitulo = new JLabel("Datos de la Cuenta");
        lblTitulo.setForeground(Colores.amarilloVivo);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblTitulo.setBounds(30, 36, 300, 40);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setForeground(Colores.amarilloTexto);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombre.setBounds(30, 117, 104, 25);
        contentPane.add(lblNombre);

        JLabel lblContraseña = new JLabel("Contraseña: ");
        lblContraseña.setForeground(Colores.amarilloTexto);
        lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContraseña.setBounds(30, 187, 104, 25);
        contentPane.add(lblContraseña);

        JLabel lblCorreo = new JLabel("Correo: ");
        lblCorreo.setForeground(Colores.amarilloTexto);
        lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCorreo.setBounds(30, 222, 104, 25);
        contentPane.add(lblCorreo);

        JLabel lblTelefono = new JLabel("Teléfono: ");
        lblTelefono.setForeground(Colores.amarilloTexto);
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTelefono.setBounds(30, 257, 104, 25);
        
        JLabel lblClub = new JLabel("Club actual: ");
        lblClub.setForeground(new Color(255, 250, 201));
        lblClub.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblClub.setBounds(30, 292, 400, 25);
        contentPane.add(lblClub);
        
        JLabel lblApellidos = new JLabel("Apellidos: ");
        lblApellidos.setForeground(new Color(255, 250, 201));
        lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblApellidos.setBounds(30, 152, 104, 25);
        contentPane.add(lblApellidos);
        
        JLabel lblClubUsuario = new JLabel((ClubDAO.getNombreClubPorDni(
        		VentanaIniciarSesion.usuarioActual.getDni()) == null ? "sin club" : 				ClubDAO.getNombreClubPorDni(VentanaIniciarSesion.usuarioActual.getDni())));
        lblClubUsuario.setForeground(new Color(255, 250, 201));
        lblClubUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblClubUsuario.setBounds(170, 295, 279, 18);
        contentPane.add(lblClubUsuario);
        
        JTextArea lblNombreUsuario = new JTextArea(VentanaIniciarSesion.usuarioActual.getNombre());
        lblNombreUsuario.setBackground(Colores.grisMedio);
        lblNombreUsuario.setForeground(new Color(255, 250, 201));
        lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombreUsuario.setBounds(170, 118, 279, 22);
        contentPane.add(lblNombreUsuario);
        
        JTextArea lblApellidosUsuario = new JTextArea(VentanaIniciarSesion.usuarioActual.getApellidos());
        lblApellidosUsuario.setBackground(Colores.grisMedio);
        lblApellidosUsuario.setForeground(new Color(255, 250, 201));
        lblApellidosUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblApellidosUsuario.setBounds(170, 153, 279, 22);
        contentPane.add(lblApellidosUsuario);
        
        JTextArea lblContraseñaUsuario = new JTextArea(VentanaIniciarSesion.usuarioActual.getContrasena());
        lblContraseñaUsuario.setBackground(Colores.grisMedio);
        lblContraseñaUsuario.setForeground(new Color(255, 250, 201));
        lblContraseñaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContraseñaUsuario.setBounds(170, 188, 279, 22);
        contentPane.add(lblContraseñaUsuario);
        
        JTextArea lblCorreoUsuario = new JTextArea(VentanaIniciarSesion.usuarioActual.getCorreoElectronico());
        lblCorreoUsuario.setBackground(Colores.grisMedio);
        lblCorreoUsuario.setForeground(new Color(255, 250, 201));
        lblCorreoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCorreoUsuario.setBounds(170, 223, 279, 22);
        contentPane.add(lblCorreoUsuario);
        
        JTextArea lblTelefonoUsuario = new JTextArea(""+VentanaIniciarSesion.usuarioActual.getTelefono());
        lblTelefonoUsuario.setBackground(Colores.grisMedio);
        lblTelefonoUsuario.setForeground(new Color(255, 250, 201));
        lblTelefonoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTelefonoUsuario.setBounds(170, 258, 279, 22);
        contentPane.add(lblTelefonoUsuario);
        
        //TEXTFIELD
        tfNombre = new JTextField(lblNombreUsuario.getText());
        tfNombre.setForeground(new Color(255, 255, 255));
        tfNombre.setBackground(Colores.grisMedio);
        tfNombre.setBounds(133, 117, 329, 25);
        contentPane.add(tfNombre);
        tfNombre.setColumns(10);
        contentPane.add(lblTelefono);
        tfNombre.setVisible(false);
        
        tfApellidos = new JTextField(lblApellidosUsuario.getText());
        tfApellidos.setForeground(new Color(255, 255, 255));
        tfApellidos.setBackground(Colores.grisMedio);
        tfApellidos.setColumns(10);
        tfApellidos.setBounds(133, 152, 329, 25);
        contentPane.add(tfApellidos);
        tfApellidos.setVisible(false);
        
        tfCorreo = new JTextField(lblCorreoUsuario.getText());
        tfCorreo.setForeground(new Color(255, 255, 255));
        tfCorreo.setBackground(Colores.grisMedio);
        tfCorreo.setColumns(10);
        tfCorreo.setBounds(133, 222, 329, 25);
        contentPane.add(tfCorreo);
        tfCorreo.setVisible(false);
        
        tfTelefono = new JTextField(lblTelefonoUsuario.getText());
        tfTelefono.setForeground(new Color(255, 255, 255));
        tfTelefono.setBackground(Colores.grisMedio);
        tfTelefono.setColumns(10);
        tfTelefono.setBounds(133, 257, 329, 25);
        contentPane.add(tfTelefono);
        tfTelefono.setVisible(false);
        //PASSWORDFILEDS
        pfContrasena = new JPasswordField(lblContraseñaUsuario.getText());
        pfContrasena.setForeground(new Color(255, 255, 255));
        pfContrasena.setBackground(Colores.grisMedio);
        pfContrasena.setEchoChar('*');
        pfContrasena.setBounds(133, 187, 329, 25);
        contentPane.add(pfContrasena);
        pfContrasena.setVisible(false);
        //BOTONES
        JButton btnEditar = new JButton("Editar cuenta");
        btnEditar.setBackground(Colores.grisMedio);
        btnEditar.setForeground(Colores.amarilloVivo);
        btnEditar.setBorder(null);
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnEditar.setBounds(29, 327, 200, 35);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Aparecen los componente para modifcar
            	tfNombre.setVisible(true);
            	tfApellidos.setVisible(true);
            	pfContrasena.setVisible(true);
            	tfCorreo.setVisible(true);
            	tfTelefono.setVisible(true);
            	
            	//Se intercambian los botnes Eliminar por Confirmar
            	btnEliminar.setVisible(false);
            	btnConfirmar.setVisible(true);
            	
            	//Se quitan de la vista los datos de la cuenta
            	lblNombreUsuario.setVisible(false);
            	lblApellidosUsuario.setVisible(false);
            	lblContraseñaUsuario.setVisible(false);
            	lblCorreoUsuario.setVisible(false);
            	lblTelefonoUsuario.setVisible(false);            	
            }
        });
        contentPane.add(btnEditar);
        
        btnEliminar = new JButton("EliminarCuenta");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int decision = JOptionPane.showConfirmDialog(contentPane, 
        				"¿Estás seguro de que quieres eliminar esta cuenta?",
        				"CONFIRMACIÓN",
        				JOptionPane.YES_NO_OPTION);
        		
        		if(decision == JOptionPane.OK_OPTION) {
        			UsuarioDAO.eliminarUsuario(VentanaPrincipal.propietario);

					JOptionPane.showMessageDialog(contentPane, 
							"Se ha eliminado el usuario correctamente");
					VentanaCuenta.this.dispose();
					VentanaPrincipal.propietario = null;
					VentanaPrincipal vp = new VentanaPrincipal();
					vp.setVisible(true);
        		}
        	}
        });
        btnEliminar.setForeground(new Color(255, 215, 0));
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnEliminar.setBorder(null);
        btnEliminar.setBackground(new Color(51, 51, 51));
        btnEliminar.setBounds(262, 327, 200, 35);
        contentPane.add(btnEliminar);
        
        btnConfirmar = new JButton("Confirmar cambios");
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nombre = tfNombre.getText().trim();
        		String apellidos = tfApellidos.getText().trim();
        		String telefonoTexto = tfTelefono.getText().trim();
        		String correo = tfCorreo.getText().trim();
        		String contrasena = new String(pfContrasena.getPassword()).trim();
        		
        		if(nombre.equalsIgnoreCase("")
						|| apellidos.equalsIgnoreCase("")
						|| telefonoTexto.equalsIgnoreCase("")
						|| correo.equalsIgnoreCase("")
						|| contrasena.equalsIgnoreCase("")) {	
					JOptionPane.showMessageDialog(contentPane, 
							"Debes rellenar todos los campos",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int telefono = Integer.parseInt(telefonoTexto);
						
						if(VentanaRegistrar.esEmailValido(correo)) {
							boolean correcto = UsuarioDAO.actualizarUsuario(
									VentanaIniciarSesion.usuarioActual.getDni(),
									nombre,
									apellidos,
									String.valueOf(telefono),
									correo,
									contrasena);
							
							if(correcto) {
								VentanaIniciarSesion.usuarioActual = UsuarioDAO.getUsuarioPorDni(
										VentanaIniciarSesion.usuarioActual.getDni());
								
								JOptionPane.showMessageDialog(contentPane, 
										"Se ha modificado el usuario correctamente");
								VentanaCuenta.this.dispose();
								VentanaCuenta vc = new VentanaCuenta();
								vc.setVisible(true);
							}							
						}else {
							JOptionPane.showMessageDialog(contentPane, 
									"El correo no es válido",
									"ERROR",
									JOptionPane.ERROR_MESSAGE);	
						}
						
					}catch(Exception er) {
						JOptionPane.showMessageDialog(contentPane, 
								"El teléfono debe ser un número",
								"ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
        	}
        });
        btnConfirmar.setForeground(new Color(255, 215, 0));
        btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(new Color(51, 51, 51));
        btnConfirmar.setBounds(262, 327, 200, 35);
        contentPane.add(btnConfirmar);
        btnConfirmar.setVisible(false);
    }
}


