package cine;


public class Cine {

    private String nombre;
    private Sala[] sala;

    public Cine(String nombre, Sala[] sala){

        this.nombre = nombre;
        this.sala =  sala;

    }//Constructor Cine

    public void comprarEntrada (int sala, int sesion, int fila, int columna){ //Funcion para comprar una entrada
    	this.sala[sala-1].comprarEntrada(sesion-1, fila, columna);
    }//comprarEntrada

    public int getIdEntrada (int sala, int sesion, int fila, int columna){ //Funcion que devuelve el id de la entrada
    	return this.sala[sala-1].getIdEntrada(sesion-1, fila, columna);
    }//getIdEntrada

    public char[][] getEstadoSesion (int sala, int sesion){ //Funcion que devuelve una matriz que representa el estado de la sesion
        return this.sala[sala-1].getEstadoSesion(sesion-1);

    }//getEstadoSesion

    public String[] getPeliculas (){ //Funcion que devuelve las peliculas que hay en el cine
        String[] Peliculas = new String[sala.length];
        for (int i = 0; i < sala.length; i++){
            Peliculas[i] = sala[i].getPelicula();
        }
        return Peliculas;

    }//getPeliculas

    public String[] getHorasDeSesionesDeSala (int sala){ //Funcion que devuelve las horas de las sesiones en una sala
    	return this.sala[sala-1].getHorasDeSesionesDeSala();
    }//getHorasDeSesionesDeSala

    public String recogerEntradas (int id, int sala, int sesion){ //Funcion que devuelve la informacion de una entrada
        if (this.sala[sala-1].recogerEntradas(id, sesion-1) == null){
            return null;
        }
        else {
            return this.nombre + "@" + this.sala[sala - 1].recogerEntradas(id, sesion - 1);
        }
    }//recogerEntradas

    public int getButacasDisponiblesSesion (int sala, int sesion){ //Funcion que devuelve el numero de butacas disponibles en una sesion
    	return this.sala[sala-1].getButacasDisponiblesSesion(sesion-1) ;
    }//getButacasDisponiblesSesion

    public ButacasContiguas recomendarButacasContiguas (int noButacas, int sala, int sesion){ //Funcion para recomendar butacas contiguas
    	return this.sala[sala-1].recomendarButacasContiguas(noButacas, sesion-1);

    }//recomendarButacasContiguas

    public void comprarEntradasRecomendadas (int sala, int sesion, ButacasContiguas butacas){ //Funcion para comprar entradas recomendadas por la funcion recomendarButacasContiguas
    	this.sala[sala-1].comprarEntradasRecomendadas(sesion-1, butacas);
    }//comprarEntradasRecomendadas

    public void incluirSesion (int sala, String horaSesion){ //Funcion para añadir una nueva sesion
    	this.sala[sala-1].incluirSesion(horaSesion);
    }//incluirSesion

    public void borrarSesion (int sala, String horaSesion){ //Funcion para eliminar una sesion 
        this.sala[sala-1].borrarSesion(horaSesion);
    }//borrarSesion
}//Class Cine
