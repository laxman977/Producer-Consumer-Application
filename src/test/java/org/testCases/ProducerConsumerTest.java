package org.testCases;

import org.Application.Consumer;
import org.Application.MessageQueue;
import org.Application.Producer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the producer-consumer application.
 */
public class ProducerConsumerTest {
  @Test
  public void testSuccessfulMessageProcessing() throws InterruptedException {
    MessageQueue messageQueue = new MessageQueue(10);
    Producer producer = new Producer(messageQueue, 5);
    Consumer consumer = new Consumer(messageQueue);

    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);

    producerThread.start();
    consumerThread.start();

    producerThread.join();
    consumerThread.interrupt();

    assertEquals(5, producer.getProducedMessages());
    assertTrue(consumer.getSuccessfulMessages() > 0);
  }

  @Test
  public void testErrorHandling() throws InterruptedException {
    MessageQueue messageQueue = new MessageQueue(10);
    Producer producer = new Producer(messageQueue, 10);
    Consumer consumer = new Consumer(messageQueue);

    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);

    producerThread.start();
    consumerThread.start();

    producerThread.join();
    consumerThread.interrupt();

    assertTrue(consumer.getErrorMessages() > 0);
  }


}
