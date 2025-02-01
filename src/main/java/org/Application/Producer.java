package org.Application;

/**
 * A producer that generates messages and adds them to the message queue.
 */
public class Producer implements Runnable {
    private final MessageQueue messageQueue;
    private final int totalMessages;
    private int producedMessages = 0;


    /**
     * Constructor to initialize the producer.
     *
     * @param messageQueue The shared message queue.
     * @param totalMessages The total number of messages to produce.
     */
    public Producer(MessageQueue messageQueue, int totalMessages) {
        this.messageQueue = messageQueue;
        this.totalMessages = totalMessages;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= totalMessages; i++) {
                String message = "Message-" + i;
                messageQueue.produce(message);
                producedMessages++;
                System.out.println("Produced: " + message);
                Thread.sleep(100); // Simulate time taken to produce a message
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Returns the total number of messages produced.
     *
     * @return The number of messages produced.
     */
    public int getProducedMessages() {
        return producedMessages;
    }
}
