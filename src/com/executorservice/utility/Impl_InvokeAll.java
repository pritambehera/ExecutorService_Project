package com.executorservice.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*invole all recevis the data from all the callable objects*/
public class Impl_InvokeAll {
public static void main(String[] args) throws InterruptedException, ExecutionException {
	ExecutorService es = Executors.newFixedThreadPool(2);
	
	System.out.println(new Date());
	
	List<Callable<String>> callableList = new ArrayList<>();
			callableList.add(new Service1(1));
			callableList.add(new Service1(2));
			callableList.add(new Service1(3));
			callableList.add(new Service1(4));
			callableList.add(new Service1(5));
			callableList.add(new Service1(6));
			
			List<Future<String>>futList = es.invokeAll(callableList);
			
//			es.shutdown();
			es.awaitTermination(10, TimeUnit.SECONDS);
			
			System.out.println("=====================");
			for (Future<String> future : futList) {
				System.out.println(future.get());
			}
			System.out.println(new Date());
}
}
class Service1 implements Callable<String>{
int i;

	public Service1(int i) {
	super();
	this.i = i;
}
	
	@Override
	public String call() throws Exception {
	Thread.sleep(1000);
	System.out.println("in thread :" + i);
		return "from thread: " + i;
	}
	
}
