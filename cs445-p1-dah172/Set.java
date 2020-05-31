// David James Hinton II (dah172)
// thanks for your help!!!!

import java.util.Arrays;

public class Set<E> implements SetInterface<E> {
  private E[] _data;
  private int _size;
  private E[] _temp;

//------------------------------------------------------------------------------
//these are the constructors
  public Set(int capacity) throws IllegalArgumentException{
    if(capacity < 1)
      throw new IllegalArgumentException("Bad Capacity");
    else{
      _data = (E[]) new Object[capacity];
    }
  }

  public Set(){
    this(10);
  }

  public Set(E[] newData) throws SetFullException{
    this(newData.length);
    for(E item:newData){
      add(item);
    }
  }

//------------------------------------------------------------------------------
//returns size of the Set
@Override
  public int getSize(){
    return _size;
  }

//------------------------------------------------------------------------------
//returns true if the Set is empty
@Override
  public boolean isEmpty(){
    if(_size == 0)
      return true;
    else
      return false;
  }

//------------------------------------------------------------------------------
//adds a new entry to the Set
@Override
  public boolean add(E newEntry) throws NullPointerException{
    if(newEntry != null)
      if(!contains(newEntry))
        if(_data.length != _size){
          _data[_size] = newEntry;
          _size++;
          return true;
        }
        else{
          sizeUp();
          _data[_size] = newEntry;
          _size++;
          return true;
        }
      else
        return false;
    else
      throw new NullPointerException("Attempted Entry Was NULL");
  }

//------------------------------------------------------------------------------
//removes specific entry from the Set
@Override
  public E remove(E entry) throws NullPointerException{
    if(entry == null)
      throw new NullPointerException("Entry Was Null");
    else
      if(!isEmpty()){//if there are things in the set
        int index = getIndexOf(entry);
        if(index > 0)
          return removeAtIndex(index);
        else
          return null;
      }
      else
        return null;
  }

//------------------------------------------------------------------------------
//removes a random entry from the Set
@Override
  public E remove(){
    if(_size == 0)
      return null;
    else
      return removeAtIndex(0);
  }

//------------------------------------------------------------------------------
//removes all items from the Set
@Override
  public void clear(){
    if(_size == 0)
      return;
    else{
      for(int i = 0; i<_size; i++){
        _data[i] = null;
      }
      _size = 0;
    }
  }

//------------------------------------------------------------------------------
//checks to see if the Set contains the entry
@Override
  public boolean contains(E entry) throws NullPointerException{
    if(entry == null)
      throw new NullPointerException("Entry Was Null");
    else{
      for(E value:_data)
        if(value == entry)
          return true;
      return false;
    }
  }

//------------------------------------------------------------------------------
//gets the elements in the Set and copies them into a new array
@Override
  public Object[] toArray(){
    return Arrays.copyOf(_data, _size);
  }

//------------------------------------------------------------------------------
//PRIVATE METHOD; returns the index of an entry (for removal purposes)
  private int getIndexOf(E entry){
    for(int i = 0; i < _size; i++){
      if(_data[i] == entry)
        return i;
    }
    return -1;
  }

//------------------------------------------------------------------------------
//PRIVATE METHOD; removes the entry (after locating the index)
//we have already checked that the given index is valid and that the Set is not empty, so it is unnecessary to check again in this method
  private E removeAtIndex(int index){
    E temp = _data[index];
    _data[index] = _data[_size-1];
    _data[_size-1] = null;
    _size--;
    return temp;
  }

//------------------------------------------------------------------------------
//makes the array larger when needed
  private void sizeUp() {
		int newLength = _data.length * 2;
		_data = Arrays.copyOf(_data, newLength);
	}
}
