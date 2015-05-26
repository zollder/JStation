/*
 * DataGenerator.java
 * Created by zollder.
 */
package org.station;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// Generate data and put it into the blocking queue every 50 ms
// use spring scheduler to accomplish that

@Component
public class DataGenerator
{
	@Resource(name="dataQueue")
	private ConcurrentLinkedQueue<Number> dataQueue;

	@Scheduled(fixedRate=50)
	public void generate()
	{
		dataQueue.add(Math.random());
	}
}