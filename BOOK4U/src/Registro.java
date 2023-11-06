import java.util.ArrayList;
import java.util.List;




import javax.swing.*;

public class Registro {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pantalla de Registro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(20);
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField(20);

        JLabel telefonoLabel = new JLabel("Telefono:");
        JTextField telefonoField = new JTextField(20);
        
        JLabel correoLabel = new JLabel("Correo:");
        JTextField correoField = new JTextField(20);

        JLabel contraseņaLabel = new JLabel("Contraseņa:");
        JPasswordField contraseņaField = new JPasswordField(20);
        
        JLabel contraseņa2Label = new JLabel("Contraseņa2:");
        JTextField contraseņa2Field = new JTextField(20);

        JButton registrarButton = new JButton("Registrar");

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(apellidoLabel);
        panel.add(apellidoField);
        panel.add(telefonoLabel);
        panel.add(telefonoField);
        panel.add(correoLabel);
        panel.add(correoField);
        panel.add(contraseņaLabel);
        panel.add(contraseņaField);
        panel.add(contraseņa2Label);
        panel.add(contraseņa2Field);
        panel.add(registrarButton);

        frame.setVisible(true);
    }

    private List<Usuario> listaUsuarios;

    public Registro() {
        listaUsuarios = new ArrayList<>();
    }

    public void agregarUsuario(String dni, String nombre, String apellidos, int telefono, String correo) {
        Usuario usuario = new Usuario( dni,  nombre,  apellidos,  telefono,  correo);
        listaUsuarios.add(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return listaUsuarios;
    }
}