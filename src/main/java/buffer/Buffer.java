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

  public void put(Object element) {
    if (data.size() == capacity)
      throw new RuntimeException("ERROR: Buffer lleno, no puede añadir elemento.");
      //System.exit(-1);
      //System.out.println("Element inserted");

    data.add(element);
    numPut++;
  }

  public Object get() throws Exception {
    if (data.isEmpty())
      throw new Exception();

    Object value = data.remove();
    System.out.println("Element extracted");

    numGet++;
    return value;
  }

  public int GetNumberOfElements() {
    return data.size();
  }

  public int get_number_of_holes() {
    return capacity - data.size();
  }

  public int gc() {
    return capacity;
  }

  public int getNumberOfOperations() {
    return numPut + numGet;
  }
}
