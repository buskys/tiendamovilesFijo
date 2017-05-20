package clases;

import Interfaz.*;
import clases.*;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static java.util.Spliterators.iterator;

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

//    public void escribirDatos() throws FileNotFoundException, IOException {
//        try {
//            ObjectOutputStream ficheiro = new ObjectOutputStream(new FileOutputStream("usuarios.data"));
//            for (int i = 0; i < usuarios.size(); i++) {
//                Usuario usuario1 = usuarios.get(i);//Opcional??
//                ficheiro.writeObject(usuario1);
//            }
//            ficheiro.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("No se encuentra el fichero");
//
//        } catch (IOException e) {
//            System.out.println("Error!");
//        }
//    }
//
//    public void leerDatos() throws FileNotFoundException, IOException, ClassNotFoundException {
//        try {
//            ObjectInputStream ficheiro = new ObjectInputStream(new FileInputStream("usuarios.data"));
//            Usuario usuario2 = (Usuario) ficheiro.readObject();
//            while (usuario2 != null) {
//                usuarios.add(usuario2);//Usuarios.añadir??
//                usuario2 = (Usuario) ficheiro.readObject();
//            }
//            ficheiro.close();
//        } catch (EOFException e) {
//            System.out.println("Final fichero");
//        } catch (ClassNotFoundException e) {
//            System.out.println("No se encuentra fichero");
//        } catch (IOException e) {
//            System.out.println("Error");
//        }
//
//    }

}
