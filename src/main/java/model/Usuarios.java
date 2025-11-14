package model;

public class Usuarios {
    private long id;
    final private String nombre;
    final private byte edad;

    /*Contructor para crear un usuario */
    public Usuarios (String nombre, byte edad){
        this.nombre = nombre;
        this.edad = edad;
    }
    /*Contructor para traer un id */
    public Usuarios (long id,String nombre, byte edad){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    
    public byte getEdad() {
        return edad;

    }
}
