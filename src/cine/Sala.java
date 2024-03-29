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

    public Sala (String pelicula, String[] horasSesiones, int filas, int columnas){//Constructor de la clase sesion
    	//Inicializacion del recurso
        this.pelicula = pelicula;
        this.sesiones = new ArrayList<Sesion>();        
        for (int i = 0; i < horasSesiones.length; i++){//Bucle que ordena las sesiones de la mas temprana a la mas tardia
        	int posicion = 0;
            Sesion sesion = new Sesion(horasSesiones[i], filas, columnas);
            for (int j = 0; sesiones.size() > j && j < sesiones.size(); j++){
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
    	if(sesiones.size() > sesion - 1){
    		sesiones.get(sesion - 1).comprarEntrada(fila, columna);
    	}
    }//comprarEntrada

    public int getIdEntrada (int sesion, int fila, int columna){ //Funcion que devuelve el id de la entrada seleccionada
    	if(sesiones.size() > sesion - 1){
    		return sesiones.get(sesion - 1).getIdEntrada(fila, columna);
    	}else{
    		return 0;
    	}
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
        return sesiones.get(sesion - 1).getEstadoSesion();

    }//getEstadoSesion

    public String getPelicula (){ //Funcion que devuelve la pelicula que se proyecta en la sala
        return pelicula;

    }//getPelicula

    public String recogerEntradas (int id, int sesion){ //Funcion que devuelve la informacion de una entrada para un id dado
        if (this.sesiones.get(sesion - 1).recogerEntradas(id) == null){
            return null;

        }
        else{
            return this.pelicula + "@" + this.sesiones.get(sesion - 1).recogerEntradas(id);
        }

    }//recogerEntradas

    public int getButacasDisponiblesSesion (int sesion){ //Funcion que devuelve el numero de butacas disponibles en una sesion
        return sesiones.get(sesion - 1).getButacasDisponiblesSesion();

    }//getButacasDisponiblesSesion

    public ButacasContiguas recomendarButacasContiguas (int noButacas, int sesion){ //Funcion para recomendar butacas contiguas
        return sesiones.get(sesion - 1).recomendarButacasContiguas(noButacas);

    }//recomendarButacasContiguas

    public void comprarEntradasRecomendadas (int sesion, ButacasContiguas butacas){ //Funcion para comprar entradas recomendadas por la funcion recomendarButacasContiguas
        sesiones.get(sesion - 1).comprarEntradasRecomendadas(butacas);

    }//comprarEntradasRecomendadas

    public void incluirSesion (String horaSesion){ //Funcion para anadir una nueva sesion
        
        int posicion = 0;
        Sesion sesion = new Sesion(horaSesion, filas, columnas);
        for (int i = 0; i < sesiones.size(); i++){
            if (sesiones.size() == 0 || sesiones.get(i).getHora().compareTo(horaSesion) >= 0){
            	i = sesiones.size();//Forzamos la salida del bucle
            }else{
            	posicion++;
            }

        }
        sesiones.add(posicion, sesion);
    }//incluirSesion

    public void borrarSesion (String horaSesion){ //Funcion para eliminar una sesion
        int i = 0;
        while (i < sesiones.size()){
        	if(sesiones.size() == 0){
        		i = sesiones.size() + 1;//Forzamos salida
        	}else if (sesiones.get(i).getHora().equals(horaSesion)){
                sesiones.removeElementAt(i);
            }
            i++;
        }
        
    }//borrarSesion
    
}//Class Sala
