package cine;

import list.ArrayList;
import anotacion.Programacion2;

@Programacion2(
        nombreAutor1 = "Pablo",
        apellidoAutor1 = "Beltran de Casso",
        emailUPMAutor1 = "p.beltran@alumnos.upm.es",
        nombreAutor2 = "Inigo",
        apellidoAutor2 = "Aranguren Redondo",
        emailUPMAutor2 = "i.aranguren@alumnos.upm.es"
)


public class Sala {

    private String pelicula;
    private ArrayList<Sesion> sesiones;
    private int filas;
    private int columnas;

    public Sala (String pelicula, String[] horasSesiones, int filas, int columnas){

        this.pelicula = pelicula;
        this.sesiones = new ArrayList<Sesion>();        
        for (int i = 0; i < horasSesiones.length; i++){
        	int posicion = 0;
            Sesion sesion = new Sesion(horasSesiones[i], filas, columnas);
            for (int j = 0; j < sesiones.size(); j++){
                if (sesiones.get(j).getHora().compareTo(horasSesiones[i]) < 0){
                    posicion++;
                }
            }
            sesiones.add(posicion, sesion);
        }
        this.filas = filas;
        this.columnas = columnas;

    }//Constructor Sala

    public void comprarEntrada (int sesion, int fila, int columna){ //Funcion para comprar una entrada
        sesiones.get(sesion).comprarEntrada(fila, columna);

    }//comprarEntrada

    public int getIdEntrada (int sesion, int fila, int columna){ //Funcion que devuelve el id de la entrada
        return sesiones.get(sesion).getIdEntrada(fila, columna);

    }//getIdEntrada

    public String[] getHorasDeSesionesDeSala (){ //Funcion que devuelve las horas de las sesiones en una sala
        String[] horasSesiones = new String[sesiones.size()];
        String hora;
        for (int i = 0; i < sesiones.size(); i++){
            hora = sesiones.get(i).getHora();
            horasSesiones[i] = hora;
        }

        return horasSesiones;

    }//getHorasDeSesionesDeSala

    public char[][] getEstadoSesion (int sesion){ //Funcion que devuelve una matriz que representa el estado de la sesion
        return sesiones.get(sesion).getEstadoSesion();

    }//getEstadoSesion

    public String getPelicula (){ //Funcion que devuelve las peliculas que hay en el cine
        return pelicula;

    }//getPelicula

    public String recogerEntradas (int id, int sesion){ //Funcion que devuelve la informacion de una entrada
        if (this.sesiones.get(sesion).recogerEntradas(id) == null){
            return null;

        }
        else{
            return this.pelicula + "@" + this.sesiones.get(sesion).recogerEntradas(id);
        }

    }//recogerEntradas

    public int getButacasDisponiblesSesion (int sesion){ //Funcion que devuelve el numero de butacas disponibles en una sesion
        return sesiones.get(sesion).getButacasDisponiblesSesion();

    }//getButacasDisponiblesSesion

    public ButacasContiguas recomendarButacasContiguas (int noButacas, int sesion){ //Funcion para recomendar butacas contiguas
        return sesiones.get(sesion).recomendarButacasContiguas(noButacas);

    }//recomendarButacasContiguas

    public void comprarEntradasRecomendadas (int sesion, ButacasContiguas butacas){ //Funcion para comprar entradas recomendadas por la funcion recomendarButacasContiguas
        sesiones.get(sesion).comprarEntradasRecomendadas(butacas);

    }//comprarEntradasRecomendadas

    public void incluirSesion (String horaSesion){ //Funcion para añadir una nueva sesion
        
        int posicion = 0;
        Sesion sesion = new Sesion(horaSesion, filas, columnas);
        for (int i = 0; i < sesiones.size(); i++){
            if (sesiones.get(i).getHora().compareTo(horaSesion) < 0){
                posicion++;
            }

        }
        sesiones.add(posicion, sesion);
    }//incluirSesion

    public void borrarSesion (String horaSesion){ //Funcion para eliminar una sesion
        int i = 0;
        while (i < sesiones.size()){
            if (sesiones.get(i).getHora().equals(horaSesion)){
                sesiones.removeElementAt(i);
            }
            i++;
        }
        
    }//borrarSesion
    
}//Class Sala
