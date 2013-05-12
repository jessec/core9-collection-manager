package io.core9.collections.helpers.impl;



import java.util.concurrent.Callable;



class GetLengthTask implements Callable<Integer>
{
  private final String _string;
  
  public GetLengthTask(String string) {
	 //System.out.println("got run..");
    _string = string;
  }
  
  @Override
  public Integer call() throws Exception {
    return _string.length();
  }
}


