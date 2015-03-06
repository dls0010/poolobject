/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author alumno
 *
 */
public class ReusablePoolTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		//Creaci�n de dos objetos del tipo ReusablePool
		ReusablePool pool;
		ReusablePool pool2;
		
		//Instanciaci�n a traves del m�todo getInstance que define el patron Singleton.
		pool = ReusablePool.getInstance();
		pool2 = ReusablePool.getInstance();
		
		//Comprobacion de que haya una unica instancia
		assertTrue(pool==pool2);	
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		
		//Instanciamos un objeto de tipo ReusablePool
		ReusablePool pool;
		pool = ReusablePool.getInstance();
		
		//Guardamos en una cadena el contenido del pool
		String texto1 = pool.toString();
		String texto2 = null ;
		try {
			//Llamamos al metodo acquireReusable que elimina un elemento del pool
			pool.acquireReusable();
			//Guardamos en una cadena el contenido del pool
			texto2 = pool.toString();
		} catch (NotFreeInstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Comprobamos las dos cadenas para verificar que el ultimo elemento ha sido borrado
		assertTrue(texto1!=texto2);
		
		
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		//Creamos un objeto de  tipo ReusablePool y uno de tipo Reusable
		Reusable elemento = new Reusable();
		ReusablePool pool;
        //Obtenemos una instancia de pool
		pool=ReusablePool.getInstance();
		//Obtenemos el toString() del pool antes de añadirle un nuevo elemento
		String texto1 = pool.toString();
		String texto2 = null ;
		//Llamamos al metodo releaseReusable pasandole un elemento reusable para que lo añada
		//si no lo contiene el pool
		pool.releaseReusable(elemento);
		//Obtenemos el toString() después de haberlo añadido
		texto2=pool.toString();
		//Comprobamos que ambos son diferentes
		assertTrue(texto1!=texto2);
		
	}

}
