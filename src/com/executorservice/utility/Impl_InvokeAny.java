package com.executorservice.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*InvokeAny  invokes all the callables and return the data from any of Callables*/

public class Impl_InvokeAny {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(2);
		System.out.println(new Date());
		List<Callable<String>> calList = new ArrayList<>();
		calList.add((Callable<String>) new Service4(1));
		calList.add((Callable<String>) new Service4(2));
		calList.add((Callable<String>) new Service4(3));
		calList.add((Callable<String>) new Service4(4));
		calList.add((Callable<String>) new Service4(5));
		String str = es.invokeAny(calList); //it return only one  future data
		es.shutdown();
		es.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("=================");
		
		System.out.println(str);
//		for (Future<String> future : fuList) {
//		}
		System.out.println(new Date());
	}

}

class Service4 implements Callable<String> {
	int i;

	public Service4(int i) {
		super();
		this.i = i;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		System.out.println("in Thread :" + i);
		return "From  Thread :" + i;
	}

}
