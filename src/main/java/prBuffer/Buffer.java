package prBuffer;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer<T> {
  private Queue<T> data;
  private int capacity;
  private int numPut = 0;
  private int numGet = 0;

  /**
   * Constructor
   * 
   * @param bufferSize
   */
  public Buffer(int bsize) {
    capacity = bsize;
    data = new LinkedList<T>();
  }

  public void put(T element) throws BufferException {
    if (data.size() == capacity) {
      throw new BufferException("ERROR: Buffer lleno, no puede añadir elemento.");
    }
    data.add(element);
    numPut++;
  }

  public T get() throws BufferException {
    if (data.isEmpty()) {
      throw new BufferException("ERROR: Buffer vacio, no puedo devolver elemento.");
    }
    T value = data.remove();
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
