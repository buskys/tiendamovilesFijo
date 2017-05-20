package clases;

import java.util.Objects;

/**
 * @author Adrián Bermúdez Vázquez
 */
public class Usuario implements Comparable<Object> {
    private String nombre, apellido1, apellido2, usuario, contraseña;

    public Usuario(String nombre, String apellido1, String apellido2, String usuario, String contraseña) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean checkPassword(String contraseña){
        return this.getContraseña().equals(contraseña);
    }

    public String toFileString(){
        return this.getNombre() + ";" + this.getApellido1() + ";" + this.getApellido2() + ";" + this.getUsuario() + ";" + this.getContraseña() + ";\n";
    }

    public static Usuario fromFileString(String str){
        String[] partes = str.split(";");
        return new Usuario(partes[0],partes[1],partes[2],partes[3],partes[4]);
    }
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nApellidos: " + apellido1 + " " + apellido2 + "\nUsuario: " + usuario;
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual = false;
        Usuario temp = (Usuario) obj;

        if (obj == null) {// objeto no inicializado
            igual = false;
        } else if (this.getClass() != obj.getClass()) {
            igual = false;
        } else if (temp.getUsuario().equals(this.getUsuario())) {
            igual = true;
        }
        return igual;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        final int ANTES = -1;
        final int IGUAL = 0;
        final int DESPUES = 1;
        Usuario usuario = (Usuario) o;
        if (this.getUsuario().compareToIgnoreCase(usuario.getUsuario()) == 0) {
            return IGUAL;
        } else if (this.getUsuario().compareToIgnoreCase(usuario.getUsuario()) < 0) {
            return ANTES;
        } else {
            return DESPUES;
        }

    }

}
