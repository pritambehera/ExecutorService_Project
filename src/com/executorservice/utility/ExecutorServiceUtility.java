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

/*execute()
shutdown()
awaitTermination()
submit(new Runnable())-->Runnabel always give null data
submit(new Callable())
invokeAny()
invokeAll()*/

public class ExecutorServiceUtility {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(5);
		System.out.println(new Date());
		for (int i = 0; i < 10; i++) {
			es.execute(new Service(i));

		}
		es.shutdown();// not wait for more time so partially working
		es.awaitTermination(10, TimeUnit.SECONDS); // wait till end of termination of all thread

		System.out.println(new Date());
	}
}

class Service implements Runnable {
	
	int i;
	public Service(int i) {
		super();
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("Thread of " + i);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}