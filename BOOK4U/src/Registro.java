import java.util.ArrayList;
import java.util.List;


public class Registro {
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