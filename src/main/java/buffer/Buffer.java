package buffer;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
  private Queue<Object> buffer;
  private int capacity;
  int np = 0;
  int ng = 0;

  /**
   * Constructor
   * 
   * @param bufferSize
   */
  public Buffer(int bsize) {
    capacity = bsize;
    buffer = new LinkedList<Object>();
  }

  public void put(Object element) {
    if (buffer.size() == capacity)
      System.exit(-1);;

    System.out.println("Element inserted");

    buffer.add(element);
    np++;
  }

  public Object get() throws Exception {
    if (buffer.isEmpty())
      throw new Exception();

    Object value = buffer.remove();
    System.out.println("Element extracted");

    ng++;
    return value;
  }

  public int GetNumberOfElements() {
    return buffer.size();
  }

  public int get_number_of_holes() {
    return capacity - buffer.size();
  }

  public int gc() {
    return capacity;
  }

  public double getNumberOfOperations() {
    return np + ng;
  }
}
