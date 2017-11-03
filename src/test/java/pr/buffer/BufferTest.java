package pr.buffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pr.buffer.Buffer;
import pr.buffer.BufferException;

/**
 * Unit test for simple App.
 */
public class BufferTest {
	
	private static final int CAPACITY_10 = 10;
	private static final int CAPACITY_0 = 0;
	private Buffer<Integer> vectorLong10 = null;
	private Buffer<Integer> vectorLong0 = null;
	private Integer element;
	
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
		
		element = 8;
		//Añadimos un elemnto y la capacidad no tiene porque cambiar.
		vectorLong10.put(element);
		assertEquals(CAPACITY_10, vectorLong10.getCapacity());
		//Sacamos un elemento y no debe de cambiar la capacidad.
		element = vectorLong10.get();
		assertEquals(CAPACITY_10, vectorLong10.getCapacity());
		
		//Añadimos un elemnto y lanzará una excepción ya que el buffer
		//es de longitud cero
		vectorLong0.put(element);

	}
	
	@Test (expected = BufferException.class)
	public void getNumberOfElementsTest() throws BufferException {
		assertEquals(0, vectorLong0.getNumberOfElements());
		assertEquals(0, vectorLong10.getNumberOfElements());
		
		element = 8;
		//Añadimos un elemnto y el número de elementos aumenta en uno, si es posible.
		vectorLong10.put(element);
		assertEquals(1, vectorLong10.getNumberOfElements());
		//Sacamos un elemento y el número de elemntos tiene que volver a cero.
		element = vectorLong10.get();
		assertEquals(0, vectorLong10.getNumberOfElements());
		
		//Añadimos un elemnto en un buffer de longitud cero tiene que dar
		//como número de elementos cero, aunque no llega porque lanza la excepcion
		vectorLong0.put(element);			
	  }
	
	@Test (expected = BufferException.class)
	public void getNumberOfElementsOfBufferFullTest() {
	  element = 8;
	  
	  for (int i=1; i<=vectorLong10.getCapacity(); i++) {
	    vectorLong10.put(element);
	    assertEquals(i, vectorLong10.getNumberOfElements());
	  }
	  
	  element = vectorLong10.get();
	  assertEquals(CAPACITY_10 - 1, vectorLong10.getNumberOfElements());
	  vectorLong10.put(element);
	  assertEquals(CAPACITY_10, vectorLong10.getNumberOfElements());
	  //Intento añadir uno más y me tiene que dar una excepcion
	  //por falta de espacio
	  vectorLong10.put(element);
	}
	
	@Test
	public void getNumberOfHolesTest() {
	  element = 8;
      
	  assertEquals(CAPACITY_10, vectorLong10.getNumberOfHoles());
      for (int i=1; i<=vectorLong10.getCapacity(); i++) {
        vectorLong10.put(element);
        assertEquals(CAPACITY_10 - i, vectorLong10.getNumberOfHoles());
      }
            
      element = vectorLong10.get();
      assertEquals(1, vectorLong10.getNumberOfHoles());
	}
	
	@Test (expected = BufferException.class)
	public void putBufferFullTest() {
	  element = 8;
	  
	  for (int i=1; i<=vectorLong10.getCapacity(); i++) {
        vectorLong10.put(element);
      }
	  vectorLong10.put(element);	  
	}
	
	@Test (expected = BufferException.class)
    public void getBufferEmptyTest() {
      element = vectorLong0.get();      
    }

    @Test
    public void getNumberOfOperationsTest() {
      element = 8;
      int contador = 0;
      
      assertEquals(contador, vectorLong10.getNumberOfOperations());
      //Lleno el buffer
      for(int i=1; i<=vectorLong10.getCapacity(); i++) {
        vectorLong10.put(element);
        contador++;
        assertEquals(contador, vectorLong10.getNumberOfOperations());
      }
      
      //Vaciar el buffer
      for(int j=contador; j>0; j--) {
        element = vectorLong10.get();
        contador++;
        assertEquals(contador, vectorLong10.getNumberOfOperations());
      }
    }
	
	@Test
	public void fillAndEmptyTheBuffer() {
	  
	}
	
}
