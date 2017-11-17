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
	private Buffer<Integer> bufferLong10 = null;
	private Buffer<Integer> bufferLong0 = null;
	private Integer element;
	
	@Before
	public void setUp(){
		bufferLong0 = new Buffer<Integer>(CAPACITY_0);
		bufferLong10 = new Buffer<Integer>(CAPACITY_10);
	}
    
	@After
	public void tearDown(){
		bufferLong0 = null;
		bufferLong10 = null;
	}
	
	@Test
	public void existBuffer(){
		assertNotNull(bufferLong0);
		assertTrue(bufferLong0 instanceof Buffer<?>);
		assertEquals(CAPACITY_0, bufferLong0.getCapacity());
		assertEquals(CAPACITY_0, bufferLong0.getNumberOfHoles());
		assertEquals(0,bufferLong0.getNumberOfElements());
		assertEquals(0,bufferLong0.getNumberOfOperations());
		
		assertNotNull(bufferLong10);
		assertTrue(bufferLong10 instanceof Buffer<?>);
		assertEquals(CAPACITY_10, bufferLong10.getCapacity());
        assertEquals(CAPACITY_10, bufferLong10.getNumberOfHoles());
        assertEquals(0,bufferLong10.getNumberOfElements());
        assertEquals(10,bufferLong10.getNumberOfOperations());
	}
	
	
	
	@Test (expected = BufferException.class)
	public void getCapacityTest() throws BufferException{
		assertEquals(CAPACITY_0, bufferLong0.getCapacity());
		assertEquals(CAPACITY_10, bufferLong10.getCapacity());
		
		element = 8;
		//Añadimos un elemnto y la capacidad no tiene porque cambiar.
		bufferLong10.put(element);
		assertEquals(CAPACITY_10, bufferLong10.getCapacity());
		//Sacamos un elemento y no debe de cambiar la capacidad.
		element = bufferLong10.get();
		assertEquals(CAPACITY_10, bufferLong10.getCapacity());
		
		//Añadimos un elemnto y lanzará una excepción ya que el buffer
		//es de longitud cero
		bufferLong0.put(element);

	}
	
	@Test (expected = BufferException.class)
	public void getNumberOfElementsTest() throws BufferException {
		assertEquals(0, bufferLong0.getNumberOfElements());
		assertEquals(0, bufferLong10.getNumberOfElements());
		
		element = 8;
		//Añadimos un elemnto y el número de elementos aumenta en uno, si es posible.
		bufferLong10.put(element);
		assertEquals(1, bufferLong10.getNumberOfElements());
		//Sacamos un elemento y el número de elemntos tiene que volver a cero.
		element = bufferLong10.get();
		assertEquals(0, bufferLong10.getNumberOfElements());
		
		//Añadimos un elemnto en un buffer de longitud cero tiene que dar
		//como número de elementos cero, aunque no llega porque lanza la excepcion
		bufferLong0.put(element);			
	  }
	
	@Test (expected = BufferException.class)
	public void getNumberOfElementsOfBufferFullTest() {
	  element = 8;
	  
	  for (int i=1; i<=bufferLong10.getCapacity(); i++) {
	    bufferLong10.put(element);
	    assertEquals(i, bufferLong10.getNumberOfElements());
	  }
	  
	  element = bufferLong10.get();
	  assertEquals(CAPACITY_10 - 1, bufferLong10.getNumberOfElements());
	  bufferLong10.put(element);
	  assertEquals(CAPACITY_10, bufferLong10.getNumberOfElements());
	  //Intento añadir uno más y me tiene que dar una excepcion
	  //por falta de espacio
	  bufferLong10.put(element);
	}
	
	@Test
	public void getNumberOfHolesTest() {
	  element = 8;
      
	  assertEquals(CAPACITY_10, bufferLong10.getNumberOfHoles());
      for (int i=1; i<=bufferLong10.getCapacity(); i++) {
        bufferLong10.put(element);
        assertEquals(CAPACITY_10 - i, bufferLong10.getNumberOfHoles());
      }
            
      element = bufferLong10.get();
      assertEquals(1, bufferLong10.getNumberOfHoles());
	}
	
	@Test (expected = BufferException.class)
	public void putBufferFullTest() {
	  element = 8;
	  
	  for (int i=1; i<=bufferLong10.getCapacity(); i++) {
        bufferLong10.put(element);
      }
	  bufferLong10.put(element);	  
	}
	
	@Test (expected = BufferException.class)
    public void getBufferEmptyTest() {
      element = bufferLong0.get();      
    }

    @Test
    public void getNumberOfOperationsTest() {
      element = 8;
      int contador = 0;
      
      assertEquals(contador, bufferLong10.getNumberOfOperations());
      //Lleno el buffer
      for(int i=1; i<=bufferLong10.getCapacity(); i++) {
        bufferLong10.put(element);
        contador++;
        assertEquals(contador, bufferLong10.getNumberOfOperations());
      }
      
      //Vaciar el buffer
      for(int j=contador; j>0; j--) {
        element = bufferLong10.get();
        contador++;
        assertEquals(contador, bufferLong10.getNumberOfOperations());
      }
    }
		
	/*
	 * PRUEBAS QUE DEFINE EL PROFESOR
	 */
    @Test
    public void shouldAGetOnEmptyBufferMakeTheBufferContainOneElement() {
      int capacity = 2;
      Buffer<Double> buffer = new Buffer<>(capacity);
      
      buffer.put(4.0);
      int numberOfElements = buffer.getNumberOfElements();
      
      buffer.put(3.5);      
      assertEquals(numberOfElements + 1, buffer.getNumberOfElements());
      assertEquals(0, buffer.getNumberOfHoles());
    }
    
    @Test
    public void shouldAGetReturnTheValueOfTheLasPutt() {
      int capacity = 5;
      Integer valor = 8;
      Buffer<Integer> buffer = new Buffer<>(capacity);
      
      buffer.put(valor);
      assertEquals(valor, buffer.get());
    }
}
