import dao.CategoriaDAO;
import dto.CategoriaDTO;
import java.util.List;
import java.util.Scanner;

public class Categoria {

    private static final Scanner sc = new Scanner(System.in);
    private static final CategoriaDAO CategoriaDAO = new CategoriaDAO();

    public static void main(String[] args) throws Exception {


        int opcion = 0;

        while (opcion != 6) {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> listarCategorias();
                case 2 -> buscarCategoriaPorId();
                case 3 -> crearCategoria();
                case 4 -> actualizarCategoria();
                case 5 -> borrarCategoria();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Categoria ---");
        System.out.println("1. Encontrar todas las categorias");
        System.out.println("2. Buscar categoria por ID");
        System.out.println("3. Crear categoria");
        System.out.println("4. Actualizar categoria");
        System.out.println("5. Borrar categoria");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void listarCategorias() {
        List<CategoriaDTO> categorias = CategoriaDAO.findAll();
        for(CategoriaDTO categoria : categorias){
            System.out.println(categoria);
        }
    }

    private static void buscarCategoriaPorId() {
        System.out.print("Introduce ID de la categoria: ");
        int id = sc.nextInt();
        sc.nextLine();
        CategoriaDTO categoria = CategoriaDAO.findById(id);
        if (categoria != null) {
            System.out.println(categoria);
        } else {
            System.out.println("Actor no encontrado.");
        }
    }

    private static void crearCategoria() {
        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();
        CategoriaDTO nuevo = new CategoriaDTO();
        nuevo.setName(nombre);
        CategoriaDAO.create(nuevo);
        System.out.println("Categoria creada con ID: " + nuevo.getCategory_id());
    }

    private static void actualizarCategoria() {
        System.out.print("Introduce ID de la categoria a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        CategoriaDTO categoria = CategoriaDAO.findById(id);
        if (categoria != null) {
            System.out.print("Nuevo nombre (" + categoria.getName() + "): ");
            String nombre = sc.nextLine();
            categoria.setName(nombre.isEmpty() ? categoria.getName() : nombre);
            CategoriaDAO.update(categoria);
            System.out.println("Categoria actualizada.");
        } else {
            System.out.println("Categoria no encontrada.");
        }
    }

    private static void borrarCategoria() {
        System.out.print("Introduce ID de la categoria a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();
        CategoriaDAO.delete(id);
        System.out.println("Categoria borrada si existía.");
    }
}

/* 
 -> Facer o CRUD de category
 -> Engadir film_category para o borrado
 -> Facelo nunha sola transaccion
 */