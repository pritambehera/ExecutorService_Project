package com.executorservice.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*submit ll through any data   and the data ll come in form of future 
future is a object which stores data  received by submit */

public class Impl_Submit_With_Runnable {
public static void main(String[] args) throws InterruptedException, ExecutionException {
	ExecutorService es = Executors.newFixedThreadPool(2);
	System.out.println(new Date());
	List<Future<String>> futureList = new ArrayList<>();
	for (int i = 0; i < 10; i++) {
		futureList.add((Future<String>) es.submit(new ServiceForSubmit(i)));
		
	}
	es.shutdown();
	es.awaitTermination(10, TimeUnit.SECONDS);
	for (Future<String> future : futureList) {
		System.out.println(future.get());
	}
	System.out.println(new Date());
}
}
class ServiceForSubmit implements Runnable{
int i;

	public ServiceForSubmit(int i) {
	super();
	this.i = i;
}

	@Override
	public void run() {
		System.out.println("in Thread: " + i);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
