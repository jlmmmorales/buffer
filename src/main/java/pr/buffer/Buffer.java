package pr.buffer;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Buffer<T> {
  private Queue<T> data;
  private int capacity;
  private int numPut = 0;
  private int numGet = 0;

  private static final Logger LOGGER = LogManager.getLogger(Buffer.class);

  /**
   * Constructor
   * 
   * @param bufferSize
   */
  public Buffer(int bsize) {
    capacity = bsize;
    data = new LinkedList<>();
  }

  public void put(T element) throws BufferException {
    if (data.size() == capacity) {
      
      LOGGER.fatal("put() failed because the buffer is full");
    
      throw new BufferException("ERROR: Buffer lleno, no puede añadir elemento.");
    }
    
    LOGGER.info("Element inserted");
    
    data.add(element);
    numPut++;
  }

  public T get() throws BufferException {
    if (data.isEmpty()) {
      throw new BufferException("ERROR: Buffer vacio, no puedo devolver elemento.");
    }
    T value = data.remove();
    
    LOGGER.info("Element extracted");
    
    numGet++;
    return value;
  }

  public int getNumberOfElements() {
    return data.size();
  }

  public int getNumberOfHoles() {
    return capacity - data.size();
  }

  public int getCapacity() {
    return capacity;
  }

  public int getNumberOfOperations() {
    return numPut + numGet + 10;
  }
}
