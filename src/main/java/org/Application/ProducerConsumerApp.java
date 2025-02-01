package org.Application;

public class ProducerConsumerApp {
    /**
     * The main application class that simulates a producer-consumer scenario.
     */
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue(10); // Queue with a max size of 10
        int totalMessages = 20;

        Producer producer = new Producer(messageQueue, totalMessages);
        Consumer consumer = new Consumer(messageQueue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();  // Wait for producer to finish
        consumerThread.interrupt();   // Stop consumer after producer finishes

        System.out.println("Total messages produced: " + producer.getProducedMessages());
        System.out.println("Total messages processed successfully: " + consumer.getSuccessfulMessages());
        System.out.println("Total errors encountered: " + consumer.getErrorMessages());
    }
}
