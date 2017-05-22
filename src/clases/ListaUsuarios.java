package clases;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


/**
 * @author Adrián Bermúdez Vázquez
 */
public class ListaUsuarios {

    private final static String FILE_LOC = "usuario.txt";
    static private ArrayList<Usuario> usuarios;
    static private HashMap<String, Usuario> usuarioHashMap;

    public ListaUsuarios() {
        usuarios = new ArrayList<Usuario>();
    }

    public void añadir(Usuario usuario) {
        if (!existsUsername(usuario.getUsuario())) {
            addUsuarioToFile(usuario);
            usuarios.add(usuario);

        } else {
            System.out.println("No se puede añadir");
        }
    }


    public Usuario findUsuario(String username) {
        ListIterator<Usuario> iterList = usuarios.listIterator();
        while (iterList.hasNext()) {
            Usuario u = iterList.next();
            if (u.getUsuario().equals(username)) {
                return u;
            }
        }
        return null;
        //En caso de HashMap
        //return usuarioHashMap.get(username);
    }

    public boolean existsUsername(String username) {
        ListIterator<Usuario> iterList = usuarios.listIterator();
        while (iterList.hasNext()) {
            Usuario u = iterList.next();
            if (u.getUsuario().equals(username)) {
                return true;
            }
        }
        return false;
        // Se a lista fora un hashMap<String, Usuario> donde o String key e o username
        //return usuarioHashMap.containsKey(username);
    }

    public void initializeList() {
        //leer fichero
        File file = new File(FILE_LOC);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                usuarios.add(Usuario.fromFileString(line));
            }
        } catch(Exception e){
            //manejar excepcion
        }
        //por cada linea leida

    }

    public void addUsuarioToFile(Usuario u) {
        try {
            Files.write(Paths.get(FILE_LOC), u.toFileString().getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public List<Usuario> listaUsuario() {
        return usuarios;
    }
}
