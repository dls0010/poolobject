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
		//Obtenemos el hashCode perteneciente al pool
		int hashCode1 = pool.hashCode();
		pool2 = ReusablePool.getInstance();
		//Obtenemos el hashCode perteneciente al pool2
		int hashCode2 = pool2.hashCode();
		
		//Comprobacion de que haya una unica instancia
		assertTrue(pool==pool2);
		//Comprobacion de que los hashCode son identicos, ya que pertenecen a mismo objeto
		assertTrue(hashCode1==hashCode2);
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		//Creamos un objeto de tipo Throwable
		Throwable ex = null;
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
			pool.acquireReusable();
			pool.acquireReusable();
			//volvemos a llamar al metodo para que lance una exception
			pool.acquireReusable();
		} catch (NotFreeInstanceException e) {
			//recogemos la exception y la guardamos en el objeto creado
			ex= e;
		}
		//Comprobamos las dos cadenas para verificar que el ultimo elemento ha sido borrado
		assertTrue(texto1!=texto2);	
		//Comprobamos si la exception lanzada arriba es de tipo NotFreeInstanceException
		assertTrue(ex instanceof NotFreeInstanceException);
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
		//Intentamos volver a meter el mismo elemento
		pool.releaseReusable(elemento);
		//Obtenemos el toString() después de haberlo añadido
		texto2=pool.toString();
		//Comprobamos que ambos son diferentes
		assertTrue(texto1!=texto2);
		
	}

}
