package com.executorservice.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*submit ll through  Future object and future stores object from submit*/

public class Impl_Submit_With_Callable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(2);
		System.out.println(new Date());
		List<Future<String>> futList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			futList.add(es.submit(new ServiceForSubmit1(i)));
		}
		es.shutdown();
		es.awaitTermination(10, TimeUnit.SECONDS);
		for (Future<String> future : futList) {
			System.out.println(future.get()); //return data 
		}
		System.out.println(new Date());
	}
}

class ServiceForSubmit1 implements Callable<String> {
	int i;

	public ServiceForSubmit1(int i) {
		this.i = i;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		System.out.println("in Thread :" + i);
		return "From Thread :" + i;
	}

}
