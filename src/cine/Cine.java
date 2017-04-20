package cine;

import anotacion.Programacion2;

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
public class Cine {

    private String nombre;
    private Sala[] sala;

    public Cine(String nombre, Sala[] sala){

        this.nombre = nombre;
        this.sala =  sala;

    }//De constructor

    public void comprarEntrada (int sala, int sesion, int fila, int columna){ //Método para comprar una entrada en una sala y una sesión dada
    	this.sala[sala-1].comprarEntrada(sesion-1, fila, columna);
    }//De comprarEntrada

    public int getIdEntrada (int sala, int sesion, int fila, int columna){ //Devuelve el id de venta asociado a una sala,  sesión, fila y  columna
    	return this.sala[sala-1].getIdEntrada(sesion-1, fila, columna);
    }//De getIdEntrada

    public char[][] getEstadoSesion (int sala, int sesion){ //Devuelve la matriz asociada al estado de una sesión donde un asiento vacío está representado por "O" y un asiento ocupado por "#"
        return this.sala[sala-1].getEstadoSesion(sesion-1);

    }//De getEstadoSesion

    public String[] getPeliculas (){ //Devuelve las películas proyectadas en un cine
        String[] Peliculas = new String[sala.length];
        for (int i = 0; i < sala.length; i++){
            Peliculas[i] = sala[i].getPelicula();
        }
        return Peliculas;

    }//De getPeliculas

    public String[] getHorasDeSesionesDeSala (int sala){ //Método que devuelve las horas de sesiones asociadas a una sala
    	return this.sala[sala-1].getHorasDeSesionesDeSala();
    }//De getHorasDeSesionesDeSala

    public String recogerEntradas (int id, int sala, int sesion){ //Dado un id de venta, una sala y una sesión, devuelve un String con el cine, la película, la hora de la sesión y los asientos asociados a ese id
        if (this.sala[sala-1].recogerEntradas(id, sesion-1) == null){
            return null;
        }
        else {
            return this.nombre + "@" + this.sala[sala - 1].recogerEntradas(id, sesion - 1);
        }
    }//De recogerEntradas

    public int getButacasDisponiblesSesion (int sala, int sesion){ //Devuelve el número de butacas disponibles para una sesión en una sala
    	return this.sala[sala-1].getButacasDisponiblesSesion(sesion-1) ;
    }//De getButacasDisponiblesSesion

    public ButacasContiguas recomendarButacasContiguas (int noButacas, int sala, int sesion){ //Método recomendar butacas para una sala y sesión dadas
    	return this.sala[sala-1].recomendarButacasContiguas(noButacas, sesion-1);

    }//De recomendarButacasContiguas

    public void comprarEntradasRecomendadas (int sala, int sesion, ButacasContiguas butacas){ //Compra las entradas recomendadas por el método recomendarButacasContiguas
    	this.sala[sala-1].comprarEntradasRecomendadas(sesion-1, butacas);
    }//De comprarEntradasRecomendadas

    public void incluirSesion (int sala, String horaSesion){ //Añade una sesión a una sala
    	this.sala[sala-1].incluirSesion(horaSesion);
    }//De incluirSesion

    public void borrarSesion (int sala, String horaSesion){ //Elimina una sesión de una sala
        this.sala[sala-1].borrarSesion(horaSesion);
    }//De borrarSesion
}//De Cine
