package org.Application;

import java.util.LinkedList;
import java.util.Queue;
/**
 * A thread-safe message queue that allows producers to add messages
 * and consumers to retrieve messages.
 */
public class MessageQueue {

    private final Queue<String> queue = new LinkedList<>();
    private final int maxSize;

    /**
     * Constructor to initialize the queue with a maximum size.
     *
     * @param maxSize The maximum number of messages the queue can hold.
     */

    public MessageQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Adds a message to the queue. If the queue is full, the producer waits.
     *
     * @param message The message to be added.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */

    public synchronized void produce(String message) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        queue.add(message);
        notifyAll();
    }
    /**
     * Retrieves and removes a message from the queue. If the queue is empty, the consumer waits.
     *
     * @return The message from the queue.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public synchronized String consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        String message = queue.poll();
        notifyAll(); // Notify producers that space is available
        return message;
    }
    /**
     * Returns the current size of the queue.
     *
     * @return The number of messages in the queue.
     */
    public synchronized int size() {
        return queue.size();
    }
}
