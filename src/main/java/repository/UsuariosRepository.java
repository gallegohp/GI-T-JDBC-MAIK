package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Usuarios;

//sirve para las consultas a la base de datos

public class UsuariosRepository {

    //vamos a insertar un usuario de Tipo Usuario para meterlo a la base de datos

    public void insetarUsuario(Usuarios usuario) {

        String sql = "INSERT INTO USUARIOS (NOMBRE, EDAD) VALUES (?,?)";

        //en try va a intent hacer algo (la logic) si esto no sirve hace lo del catch

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Sirve para 1 parametros, preparar una consulta y accedemos
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setByte(2, usuario.getEdad());

            preparedStatement.executeUpdate();
            //Sirve para ejecutar la consulta
            System.out.println("Usuario insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuarios> listarUsuarios() {
        String sql = "SELECT * FROM USUARIOS";

        List<Usuarios> usuarios = null;// Instanciar
        try (Connection connection = Conexion.getConnection()) {
            usuarios = new ArrayList<>();

            Statement statement = connection.createStatement();
            //sirve para mostrar sin parametro. O sea crear un nuevo estado de la consulta

            ResultSet resultSet = statement.executeQuery(sql); //
            //guradamos el resultado en esa variable, de la cunsulta anterior

            while (resultSet.next()) {//recorremos nuestra linea, .next()
                usuarios.add(new Usuarios(
                        resultSet.getLong("id"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getByte("EDAD")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace(); // para cualquier error, se muestre en consola
        }
        return usuarios;
    }

    public List<Usuarios> buscarPorNombre(String texto) {
        String sql = "SELECT ID, NOMBRE, EDAD FROM USUARIOS WHERE NOMBRE LIKE ?"; // se hace el string de la consulta que vamos a hacer
        //Like va a hacer el filtrado por comodines % texto % soltando lo que buscamos
        List<Usuarios> usuarios = new ArrayList<>(); //se guardan los objetos que salgan de la consulta

        try (Connection connection = Conexion.getConnection()) {//se hace la conexion

            PreparedStatement preparedStatement = connection.prepareStatement(sql);//se hace la consulta a la base de datos

            preparedStatement.setString(1, "%" + texto + "%"); // % permite buscar coincidencias parciales
            //el PreparedStatemt va pasar el parametro necesario para llenar el campo de la consulta "?"
            //le pasamos al paremtro antes copiado un texto el cual es el paramtro que pasamos dentro del metodo
            //el index se refiere a que el nombre esta en la posicion 1 del modelo
            ResultSet resultSet = preparedStatement.executeQuery();//ejecutas la consulta ya con el parametro creado

            //resultSet esta guardando los datos que devuelva la consulta

            while (resultSet.next()) {  //este va a ser true, mientras los resultados de result siga encontrando valores, apenas deje de contrar cambia a false..
                usuarios.add/*agregando al array los objetos que cumplan*/(new Usuarios(
                        resultSet.getLong("ID"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getByte("EDAD")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Los tipados genericos son List<> son que podemos retornar cualquier valor
    public Usuarios buscarPorId(long idBuscar) {
        String sql = "SELECT * FROM USUARIOS WHERE ID LIKE ?";//consulta que vamos a hacer
        Usuarios usuario = null; //Donde se guardan los datos que la consulta tome.

        try (Connection connection = Conexion.getConnection()) {//conexion con la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(sql); //pasamos la consulta a la base de datos

            preparedStatement.setString(1, "%" + idBuscar + "%");//Pasamos la ejecucion del query y reemplazamos el ? de la consulta

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutamos el Query, y guardamos los datos en result

            if (resultSet.next()) {
                usuario = new Usuarios( //agregamos los objetos de la consulta
                        resultSet.getLong("ID"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getByte("EDAD")
                );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuarios editarUsuarioNombre(long id,String nombre) {
        String sql = "UPDATE USUARIOS SET NOMBRE = ? WHERE ID = ?";
        Usuarios usuario = null;

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement updateStatement = connection.prepareStatement(sql);

            updateStatement.setString(1, nombre);
            updateStatement.setLong(2, id);

            int filasAfectadas = updateStatement.executeUpdate();

            System.out.println("Cambio exitoso");

        }catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    public void eliminarUsuarios (long id){
        String sql = "DELETE FROM USUARIOS WHERE ID = ? ";
        Usuarios usuario = null;

            try(Connection connection = Conexion.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();

                System.out.println("Cambio Exitoso");
            }catch (Exception e){
                e.printStackTrace();
            }

    }

}
