package cine;

import anotacion.Programacion2;
import list.ArrayList;

@Programacion2(
        nombreAutor1 = "Pablo",
        apellidoAutor1 = "Beltrán de Casso",
        emailUPMAutor1 = "p.beltran@alumnos.upm.es",
        nombreAutor2 = "Iñigo",
        apellidoAutor2 = "Aranguren Redondo",
        emailUPMAutor2 = "i.aranguren@alumnos.upm.es"
)

/**
 * Created by Nacho on 5/4/17.
 */
public class Sala {

    private String pelicula;
    private ArrayList<Sesion> sesiones;
    private int filas;
    private int columnas;

    public Sala (String pelicula, String[] horasSesiones, int filas, int columnas){

        this.pelicula = pelicula;
        this.sesiones = new ArrayList();
        int pos = 0;
        for (int i = 0; i < horasSesiones.length; i++){
            Sesion sesion = new Sesion(horasSesiones[i], filas, columnas);
            for (int j = 0; j < sesiones.size(); j++){
                if (sesiones.get(j).getHora().compareTo(horasSesiones[i]) < 0){
                    pos++;
                }
            }
            sesiones.add(pos, sesion);
        }
        this.filas = filas;
        this.columnas = columnas;

    }//De constructor

    public void comprarEntrada (int sesion, int fila, int columna){ //Método para comprar entradas en una sesión dada
        sesiones.get(sesion).comprarEntrada(fila, columna);

    }//De comprarEntrada

    public int getIdEntrada (int sesion, int fila, int columna){ //Devuelve el id de venta asociado a una sesión, fila y  columna
        return sesiones.get(sesion).getIdEntrada(fila, columna);

    }//De getIdEntrada

    public String[] getHorasDeSesionesDeSala (){ //Método que devuelve las horas de sesiones asociadas a una sala
        String[] horasSesiones = new String[sesiones.size()];
        String aux;
        for (int i = 0; i < sesiones.size(); i++){
            aux = sesiones.get(i).getHora();
            horasSesiones[i] = aux;
        }

        return horasSesiones;

    }//De getHorasDeSesionesDeSala

    public char[][] getEstadoSesion (int sesion){ //Devuelve la matriz asociada al estado de una sesión donde un asiento vacío está representado por "O" y un asiento ocupado por "#"
        return sesiones.get(sesion).getEstadoSesion();

    }//De getEstadoSesion

    public String getPelicula (){ //Devuelve la película asociada a una sala
        return pelicula;

    }//De getPelicula

    public String recogerEntradas (int id, int sesion){ //Dado un id de venta y una sesión, devuelve un String con la película, la hora de la sesión y los asientos asociados a ese id
        if (this.sesiones.get(sesion).recogerEntradas(id) == null){
            return null;

        }
        else{
            return this.pelicula + "@" + this.sesiones.get(sesion).recogerEntradas(id);
        }

    }//De recogerEntradas

    public int getButacasDisponiblesSesion (int sesion){ //Devuelve el número de butacas disponibles para una sesión
        return sesiones.get(sesion).getButacasDisponiblesSesion();

    }//De getButacasDisponiblesSesion

    public ButacasContiguas recomendarButacasContiguas (int noButacas, int sesion){ //Método recomendar butacas para una sesión dada
        return sesiones.get(sesion).recomendarButacasContiguas(noButacas);

    }//De recomendarButacasContiguas

    public void comprarEntradasRecomendadas (int sesion, ButacasContiguas butacas){ //Compra las entradas recomendadas por el método recomendarButacasContiguas
        sesiones.get(sesion).comprarEntradasRecomendadas(butacas);

    }//De comprarEntradasRecomendadas

    public void incluirSesion (String horaSesion){ //Añade una sesión a una sala
        boolean done = false;
        int pos = 0;
        Sesion sesion = new Sesion(horaSesion, filas, columnas);
        for (int i = 0; i < sesiones.size(); i++){
            if (sesiones.get(i).getHora().compareTo(horaSesion) < 0){
                pos++;
            }

        }
        sesiones.add(pos, sesion);
    }//De incluirSesion

    public void borrarSesion (String horaSesion){ //Elimina una sesión de una sala
        int i = 0;
        while (i < sesiones.size()){
            if (sesiones.get(i).getHora().equals(horaSesion)){
                sesiones.removeElementAt(i);
            }
            i++;
        }
    }//De borrarSesion
}//De Sala
