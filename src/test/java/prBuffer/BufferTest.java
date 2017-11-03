package prBuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple App.
 */
public class BufferTest {
	
	private static final int CAPACITY_10 = 10;
	private static final int CAPACITY_0 = 0;
	Buffer<Integer> vectorLong10 = null;
	Buffer<Integer> vectorLong0 = null;
	
	@Before
	public void setUp(){
		vectorLong0 = new Buffer<Integer>(CAPACITY_0);
		vectorLong10 = new Buffer<Integer>(CAPACITY_10);
	}
    
	@After
	public void tearDown(){
		vectorLong0 = null;
		vectorLong10 = null;
	}
	
	@Test
	public void existBuffer(){
		assertNotNull(vectorLong0);
		assertTrue(vectorLong0 instanceof Buffer<?>);
		assertNotNull(vectorLong10);
		assertTrue(vectorLong10 instanceof Buffer<?>);
	}
	
	
	
	@Test (expected = BufferException.class)
	public void getCapacityTest() throws BufferException{
		assertEquals(CAPACITY_0, vectorLong0.getCapacity());
		assertEquals(CAPACITY_10, vectorLong10.getCapacity());
		
		Integer element = 8;
		//Añadimos un elemnto y la capacidad no tiene porque cambiar.
		vectorLong10.put(element);
		assertEquals(CAPACITY_10, vectorLong10.getCapacity());
		//Sacamos un elemento y no debe de cambiar la capacidad.
		element = vectorLong10.get();
		assertEquals(CAPACITY_10, vectorLong10.getCapacity());
		
		//Añadimos un elemnto y la capacidad no tiene porque cambiar.
		vectorLong0.put(element);
		assertEquals(CAPACITY_0, vectorLong0.getCapacity());
		//Sacamos un elemento y no debe de cambiar la capacidad.
		element = vectorLong0.get();
		assertEquals(CAPACITY_0, vectorLong0.getCapacity());
	}
	
	@Test (expected = BufferException.class)
	public void getNumberOfElementsTest() throws BufferException {
		assertEquals(0, vectorLong0.getNumberOfElements());
		assertEquals(0, vectorLong10.getNumberOfElements());
		
		Integer element = 8;
		//Añadimos un elemnto y el número de elementos aumenta en uno, si es posible.
		vectorLong10.put(element);
		assertEquals(1, vectorLong10.getNumberOfElements());
		//Sacamos un elemento y el número de elemntos tiene que volver a cero.
		element = vectorLong10.get();
		assertEquals(0, vectorLong10.getNumberOfElements());
		
		//Añadimos un elemnto en un buffer de longitud cero tiene que dar
		//como número de elementos cero.
		vectorLong0.put(element);
		assertEquals(0, vectorLong0.getNumberOfElements());
		//Sacamos un elemento y el número de elemntos tiene que volver a cero.
		element = vectorLong0.get();
		assertEquals(0, vectorLong10.getNumberOfElements());
		
		//Llenamos el buffer y comprobamos su número de elementos
		for(int i=1; i<=CAPACITY_10; i++){
			vectorLong10.put(element);
			assertEquals(i, vectorLong10.getNumberOfElements());			
		}
	  }
}
