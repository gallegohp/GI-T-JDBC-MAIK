import java.util.List;
import java.util.Scanner;

import model.Usuarios;

import repository.UsuariosRepository;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UsuariosRepository usuariosRepository = new UsuariosRepository();
        boolean Ejecutando = true;

        while (Ejecutando){
            System.out.println("#####################################");
            System.out.println("1. Ingresar usuario.\n2. Listar usuarios.\n3. Buscar con nombre.\n4. Buscar con ID.\n5. Editar usuario.\n6. Eliminar por ID.\n7. salir");
            byte primeraDesicion = scanner.nextByte();
            System.out.println("#####################################");


            switch (primeraDesicion){
                case 1:
                    //insetar
                    System.out.println("Ingrese el nombre del usuario:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la edad del usuario:");
                    byte edad = scanner.nextByte();

                    Usuarios usuario = new Usuarios(nombre, edad);

                    usuariosRepository.insetarUsuario(usuario);
                    break;
                case 2:
                    System.out.println("==================================");
                    System.out.println("lista de usuarios");

                    for (Usuarios usuarios : usuariosRepository.listarUsuarios()) {
                        System.out.println("id: " + usuarios.getId() + "\n" +
                                "nombre: " + usuarios.getNombre() + "\n" +
                                "edad: " + usuarios.getEdad());
                        System.out.println("------------------------");

                    }
                    break;
                case 3:
                    System.out.println("==================================");
                    System.out.println("Ingrese el nombre a buscar:");
                    scanner.nextLine();
                    String textoBuscado = scanner.nextLine(); // Esto buscara nombres

                    // Ejecutar el mé
                    List<Usuarios> resultados = usuariosRepository.buscarPorNombre(textoBuscado);

                    // Mostrar resultados
                    if (resultados.isEmpty()) {
                        System.out.println("No se encontraron usuarios con ese nombre.");
                    } else {
                        for (Usuarios usuarioFiltrado : resultados) {
                            System.out.println("-----------------------------");
                            System.out.println("ID: " + usuarioFiltrado.getId() + ", Nombre: " + usuarioFiltrado.getNombre() + ", Edad: " + usuarioFiltrado.getEdad());
                        }
                    }
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("ingrese el id a buscar:");
                    long idBuscarUsuario = scanner.nextLong();
                    Usuarios resultadosBusquedaId = usuariosRepository.buscarPorId(idBuscarUsuario);

                    if (resultadosBusquedaId != null){
                        System.out.println("----------------------");

                        System.out.println("id: " + resultadosBusquedaId.getId() + "\n" +
                                "nombre: " + resultadosBusquedaId.getNombre() + "\n" +
                                "edad: " + resultadosBusquedaId.getEdad());
                        System.out.println("----------------------");

                    }
                    break;
                case 5:
                    System.out.println("Ingrese el id que va a cambiar");
                    long idEditar = scanner.nextLong();

                    scanner.nextLine();

                    System.out.println("Ingrese el nombre:");
                    String nombreEditar = scanner.nextLine();

                    Usuarios resultadoEdicionId = usuariosRepository.editarUsuarioNombre(idEditar,nombreEditar);

                    if (resultadoEdicionId != null){
                        System.out.println("----------------------");
                        System.out.println("id: " + resultadoEdicionId.getId() + "\n" +
                                "nombre: " + resultadoEdicionId.getNombre() + "\n" +
                                "edad: " + resultadoEdicionId.getEdad());
                        System.out.println("----------------------");

                    }
                    break;
                case 6:
                    System.out.println("Ingrese el id del usuario a ELIMINAR: ");
                    long idEliminar = scanner.nextLong();
                    usuariosRepository.eliminarUsuarios(idEliminar);
break;
                case 7:
                    Ejecutando = false;
                    break;
                default:
                    System.out.println("numero invalido");
            }
        }
    }
}



//mvn exec:java
//mvn clean install