package buffer;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
  private Queue<Object> data;
  private int capacity;
  int numPut = 0;
  int numGet = 0;

  /**
   * Constructor
   * 
   * @param bufferSize
   */
  public Buffer(int bsize) {
    capacity = bsize;
    data = new LinkedList<Object>();
  }

  public void put(Object element) throws RuntimeException {
    if (data.size() == capacity) {
      throw new RuntimeException("ERROR: Buffer lleno, no puede añadir elemento.");
    }
    data.add(element);
    numPut++;
  }

  public Object get() throws RuntimeException {
    if (data.isEmpty()) {
      throw new RuntimeException("ERROR: Buffer vacio, no puedo devolver elemento.");
    }
    Object value = data.remove();
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
    return numPut + numGet;
  }
}
